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
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.openid4java.util.HttpClientFactory;
import org.openid4java.util.ProxyProperties;

/**
 * Clase de autentificación
 *
 * Las instanciaciones de la misma se encargarán de permitir la autentificación de los usuarios
 *
 * @author Cristian Pacheco
 */
public class OpenID implements IOpenID, Constants {

    private ConsumerManager manager;
    private Properties properties;

    /**
     *
     * @throws org.openid4java.consumer.ConsumerException
     */
    public OpenID() throws ConsumerException {
        // instantiate a ConsumerManager object
        manager = new ConsumerManager();

        RealmVerifier rv = new RealmVerifier();
        rv.setEnforceRpId(false);
        manager.setRealmVerifier(rv);
        manager.setConnectTimeout(4999);
    }

    // --- placing the authentication request ---
    private String authRequest(String userSuppliedString,
            HttpServletRequest httpReq,
            HttpServletResponse httpResp)
            throws IOException, DiscoveryException {
        try {

            InputStream is = (httpReq.getSession().getServletContext().getResourceAsStream("/WEB-INF/openid.properties"));


            properties = new Properties();
            properties.load(is);

            // configure the return_to URL where your application will receive
            // the authentication responses from the OpenID provider
            String returnToUrl = properties.getProperty("return_to_url");


            // --- Forward proxy setup (only if needed) ---
            if (Boolean.parseBoolean(properties.getProperty("is_proxy"))) {
                ProxyProperties proxyProps = new ProxyProperties();
                proxyProps.setProxyHostName("192.168.0.16");
                proxyProps.setProxyPort(80);
                HttpClientFactory.setProxyProperties(proxyProps);
            }

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
            authReq = manager.authenticate(discovered, returnToUrl);


            authReq.addExtension(crearSolicitudDatos());

            return authReq.getDestinationUrl(true);

        } catch (MessageException ex) {
            System.out.println("authRequest-MessageException: " + ex.getMessage());
        } catch (ConsumerException ex) {
            System.out.println("authRequest-ConsumerException: " + ex.getMessage());
        } catch (OpenIDException ex) {
            // present error to the user
            System.out.println("authRequest-OpenIDException: " + ex.getMessage());

            ex.printStackTrace();
        }
        return null;

    }

    private FetchRequest crearSolicitudDatos() {
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

    private void setDatos(FetchResponse fetch, WebsiteUsersEntity user) {
        List emails = fetch.getAttributeValues("Email");
        String nombre = fetch.getAttributeValue("FirstName");
        String apellido = fetch.getAttributeValue("LasttName");

        String email = (String) emails.get(0);

        System.out.println("Email: " + email);

        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEmail((String) emails.get(0));
    }

    // --- processing the authentication response ---
    //public Identifier verifyResponse(HttpServletRequest httpReq)
    private WebsiteUsersEntity verifyResponse(HttpServletRequest httpReq) throws NoAutentificadoException {
        try {
            // extract the parameters from the authentication response
            // (which comes in as a HTTP request from the OpenID provider)
            ParameterList response =
                    new ParameterList(httpReq.getParameterMap());

            // retrieve the previously stored discovery information
            DiscoveryInformation discovered = (DiscoveryInformation) httpReq.getSession().getAttribute("openid-disc");

            // extract the receiving URL from the HTTP request
            StringBuffer receivingURL = httpReq.getRequestURL();
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                receivingURL.append("?").append(httpReq.getQueryString());
            }

            // verify the response; ConsumerManager needs to be the same
            // (static) instance used to place the authentication request
            VerificationResult verification = manager.verify(
                    receivingURL.toString(),
                    response, discovered);

            // examine the verification result and extract the verified identifier
            Identifier verified = verification.getVerifiedId();

            Hashtable parameters = new Hashtable();
            parameters.put("claimedId", verified.getIdentifier());

            List users = BaseModel.findEntities("WebsiteUsersEntity.findByClaimedId", parameters);

            WebsiteUsersEntity usuario = null;
            if (users.size() != 0) {
                usuario = (WebsiteUsersEntity) BaseModel.findEntities("WebsiteUsersEntity.findByClaimedId", parameters).get(0);
                httpReq.getSession().removeAttribute(PROVEEDOR_SSO_ID);
            } else {
                httpReq.getSession().setAttribute(CLAIMED_ID, verified.getIdentifier());
            }

            if (verified != null) {
                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();



                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);


                    setDatos(fetchResp, usuario);
                }

                //return verified;  // success
                return usuario;  // success
            } else {
                throw new NoAutentificadoException("No se ha podido identificar");
            }
        } catch (OpenIDException e) {
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
    public WebsiteUsersEntity login(HttpServletRequest httpReq) throws NoAutentificadoException {
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
    public String solicitarAutentificacion(String userSuppliedString,
            HttpServletRequest httpReq,
            HttpServletResponse httpResp)
            throws IOException, DiscoveryException {
        return authRequest(userSuppliedString, httpReq, httpResp);
    }

    @Override
    public boolean isUserLogged(HttpServletRequest req, WebsiteUsersEntity user) {
        if (user != null && user.getUserId().equals((Integer) req.getSession().getAttribute(USER_ID))) {
            return true;
        }

        return false;
    }
}
