/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

// ----> imports de OpenID <-------
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.server.RealmVerifier;

// ----> imports de Java <-------
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase de autentificación
 *
 * Las instanciaciones de la misma se encargarán de permitir la autentificación de los usuarios
 *
 * @author Cristian Pacheco
 */
public class OpenID implements IOpenID{

    private ConsumerManager manager;
    
    /**
     *
     * @throws org.openid4java.consumer.ConsumerException
     */
    public OpenID() throws ConsumerException
    {
        // instantiate a ConsumerManager object
        manager = new ConsumerManager();

        RealmVerifier rv = new RealmVerifier();
        rv.setEnforceRpId(false);
        manager.setRealmVerifier(rv);
        manager.setConnectTimeout(4999);

    }

    // --- placing the authentication request ---
    private void authRequest(String userSuppliedString,
                              HttpServletRequest httpReq,
                              HttpServletResponse httpResp)
            throws IOException, DiscoveryException
    {
        try
        {
            System.out.println("------>"+httpReq.getCookies());
            // configure the return_to URL where your application will receive
            // the authentication responses from the OpenID provider
            String returnToUrl = "http://localhost:8080/g2p-tracker-sinSpring/";
            String dominioAplicacion = "http://localhost:8080/g2p-tracker-sinSpring/";

            // --- Forward proxy setup (only if needed) ---
            // ProxyProperties proxyProps = new ProxyProperties();
            // proxyProps.setProxyName("proxy.example.com");
            // proxyProps.setProxyPort(8080);
            // HttpClientFactory.setProxyProperties(proxyProps);

            manager.setMaxRedirects(3);

            // perform discovery on the user-supplied identifier
            List discoveries = manager.discover(userSuppliedString);

            // attempt to associate with the OpenID provider
            // and retrieve one service endpoint for authentication
            DiscoveryInformation discovered = manager.associate(discoveries);

            // store the discovery information in the user's session
            httpReq.getSession().setAttribute("openid-disc", discovered);


            // obtain a AuthRequest message to be sent to the OpenID provider
            AuthRequest authReq;
                authReq = manager.authenticate(discovered, returnToUrl, dominioAplicacion);


            authReq.addExtension(crearSolicitudDatos());


            httpResp.sendRedirect(authReq.getDestinationUrl(true));
        }
        catch (MessageException ex) {
                Logger.getLogger(OpenID.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConsumerException ex) {
                Logger.getLogger(OpenID.class.getName()).log(Level.SEVERE, null, ex);
            }
        catch (OpenIDException e)
        {
            // present error to the user
            System.out.println("Ocurrio un error en authRequest");

            e.printStackTrace();
        }

    }


    private FetchRequest crearSolicitudDatos(){
         // Attribute Exchange example: fetching the 'email' attribute
        FetchRequest fetch = FetchRequest.createFetchRequest();
        try {

            fetch.addAttribute("FirstName", "http://schema.openid.net/namePerson/first", true);
            fetch.addAttribute("LastName", "http://schema.openid.net/namePerson/last", true);
            fetch.addAttribute("Email", "http://schema.openid.net/contact/email", true);




        } catch (MessageException ex) {
            Logger.getLogger(OpenID.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fetch;
    }

    private void setDatos(FetchResponse fetch,Usuario user){
        List emails = fetch.getAttributeValues("Email");
        String nombre = fetch.getAttributeValue("FirstName");
        String apellido = fetch.getAttributeValue("LasttName");

        String email = (String) emails.get(0);

        System.out.println("Email: " + email);

        user = new Usuario();

        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmails(new Vector(emails).elements());
    }

    // --- processing the authentication response ---
    //public Identifier verifyResponse(HttpServletRequest httpReq)
    private IUsuario verifyResponse(HttpServletRequest httpReq) throws NoAutentificadoException
    {
        try
        {
            // extract the parameters from the authentication response
            // (which comes in as a HTTP request from the OpenID provider)
            ParameterList response =
                    new ParameterList(httpReq.getParameterMap());

            // retrieve the previously stored discovery information
            DiscoveryInformation discovered = (DiscoveryInformation)
                    httpReq.getSession().getAttribute("openid-disc");

            // extract the receiving URL from the HTTP request
            StringBuffer receivingURL = httpReq.getRequestURL();
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() > 0)
                receivingURL.append("?").append(httpReq.getQueryString());

            // verify the response; ConsumerManager needs to be the same
            // (static) instance used to place the authentication request
            VerificationResult verification = manager.verify(
                    receivingURL.toString(),
                    response, discovered);

            // examine the verification result and extract the verified identifier
            Identifier verified = verification.getVerifiedId();

            Usuario usuario = new Usuario();

            usuario.setID(verified.getIdentifier());
            usuario.setSession(httpReq.getSession());
            if (verified != null)
            {
                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();



                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
                {
                    FetchResponse fetchResp = (FetchResponse) authSuccess
                            .getExtension(AxMessage.OPENID_NS_AX);


                    setDatos(fetchResp,usuario);
                }

                //return verified;  // success
                return usuario;  // success
            }
            else {
                throw new NoAutentificadoException("No se ha podido identificar");
            }
        }
        catch (OpenIDException e)
        {
            // present error to the user
            System.out.println("Ocurrio una excepcion en verifyResponse");
        }

        return null;
    }

    /**
     * Registra al usuario como online en el sistema
     *
     * @param httpReq objeto de solicitud
     * @return los datos del usuario
     * @throws modelo.NoAutentificadoException
     */
    public IUsuario login(HttpServletRequest httpReq) throws NoAutentificadoException
    {
        return verifyResponse(httpReq);
    }

    /**
     * Envía al usuario al sitio de autentificación del proveedor
     *
     * @param userSuppliedString url del proveedor
     * @param httpReq objeto de solicitud
     * @param httpResp objeto de respuesta
     * @throws java.io.IOException
     * @throws org.openid4java.discovery.DiscoveryException
     */
    public void solicitarAutentificacion(String userSuppliedString,
                              HttpServletRequest httpReq,
                              HttpServletResponse httpResp)
            throws IOException, DiscoveryException
    {
        authRequest(userSuppliedString, httpReq, httpResp);
    }
}
