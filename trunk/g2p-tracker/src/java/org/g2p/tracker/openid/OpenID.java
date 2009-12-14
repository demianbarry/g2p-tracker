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

        RealmVerifier rv = new RealmVerifier(false);
        rv.setEnforceRpId(false);
        manager.setRealmVerifier(rv);
        
    }

    /**
     * Realiza la preparación y todos los procesos previos
     * a la redirección al endpoint de autentificacion del proveedor
     *
     * @param userSuppliedString El identificador OpenID
     * @param httpReq El Request
     * @param httpResp El Response
     * @return El endpoint del proveedor
     * @throws IOException
     * @throws DiscoveryException
     */
    private String authRequest(String userSuppliedString,
            HttpServletRequest httpReq,
            HttpServletResponse httpResp)
            throws IOException, DiscoveryException {
        try {

            // abro el archivo de propiedades
            InputStream is = (httpReq.getSession().getServletContext().getResourceAsStream("/WEB-INF/openid.properties"));

            // mapeo el archivo de propiedades con un objeto de propiedades
            properties = new Properties();
            properties.load(is);

            // obtengo la url de retorno, a la cual el provedor OpenId
            // enviara su respuesta
            String returnToUrl = properties.getProperty("return_to_url");
            manager.setConnectTimeout(Integer.parseInt(properties.getProperty("timeout")));

            // verifico si hay un proxy funcionando
            if (Boolean.parseBoolean(properties.getProperty("is_proxy"))) {
                // si lo hay, lo configuro
                ProxyProperties proxyProps = new ProxyProperties();
                proxyProps.setProxyHostName(properties.getProperty("ip_proxy"));
                proxyProps.setProxyPort(Integer.parseInt(properties.getProperty("port_proxy")));
                HttpClientFactory.setProxyProperties(proxyProps);
            }

            manager.setMaxRedirects(3);

            System.out.println("---URLLLLLLLLLLLLLLLL "+returnToUrl);
            // realizo el discovery con el identificador suminstrado
            List discoveries = manager.discover(userSuppliedString);

            // establezco una asociación con el proveedor
            // y obtenego el endpoint del sitio de autentificacion
            DiscoveryInformation discovered = manager.associate(discoveries);

            // guardo la informacion recuperada en la sesion del usuario
            httpReq.getSession().setAttribute("openid-disc", discovered);

            // obtengo un mensaje de solicitud de autentificacion para ser
            // enviado al proveedor de OpenID
            AuthRequest authReq;
            authReq = manager.authenticate(discovered, returnToUrl);

            // armo la solicitud de los datos de usuario
            authReq.addExtension(crearSolicitudDatos());

            // retorno el endpoint del proveedor
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

    /**
     * Arma la solicitud de datos del usuario al proveedor
     * @return La solicitud de datos
     */
    private FetchRequest crearSolicitudDatos() {
        
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

    /**
     * Una vez recuperados los datos del usuario del proveedor
     * los vincula con el objeto usuario actual
     * 
     * @param fetch Solicitud de datos con los valores recuperados
     * @param user Usuario al cual asignarle los datos
     */
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

    /**
     * Verifica si se ha autenticado exitosamente el usuario ante el proveedor
     * 
     * @param httpReq
     * @return
     * @throws NoAutentificadoException
     */
    private WebsiteUsersEntity verifyResponse(HttpServletRequest httpReq) throws NoAutentificadoException {
        try {
            // recupero los parametros de la respuesta
            // (que proviene como un HTTP Request desde el proveedor OpenID)
            ParameterList response =
                    new ParameterList(httpReq.getParameterMap());

            // recupero los datos guardados del discovery realizado previamente
            DiscoveryInformation discovered = (DiscoveryInformation) httpReq.getSession().getAttribute("openid-disc");

            // obtengo la URL del HTTP Request
            StringBuffer receivingURL = httpReq.getRequestURL();
            // y le agrego los parametros correspondientes, si los tiene
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                receivingURL.append("?").append(httpReq.getQueryString());
            }

            // verifico la respuesta
            // el ConsumerManager debe ser la misma instancia que se uso para
            // enviar la solicitud de autentificacion
            System.out.println("----------------URLLLLL "+receivingURL);
            VerificationResult verification = manager.verify(
                    receivingURL.toString(),
                    response, discovered);

            // examino el resultado de la verificación
            // y extraigo el identificador verificado
            Identifier verified = verification.getVerifiedId();

            // verifico si el usuario ya esta registrado en la base de
            // datos de la aplicacion
            Hashtable parameters = new Hashtable();
            parameters.put("claimedId", verified.getIdentifier());

            List users = BaseModel.findEntities("WebsiteUsersEntity.findByClaimedId", parameters);

            WebsiteUsersEntity usuario = null;
            if (users.size() != 0) { // si lo esta
                // lo recupero
                usuario = (WebsiteUsersEntity) users.get(0);
                // y el proveedor ya no me interesa recordarlo
                httpReq.getSession().removeAttribute(PROVEEDOR_SSO_ID);
            } else { // si no esta
                // guardo su identificación
                httpReq.getSession().setAttribute(CLAIMED_ID, verified.getIdentifier());
            }

            // si la verificacion fue exitosa
            if (verified != null) {
                // recupero la respuesta de autentificacion
                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();


                // si trae mas información de lo habitual
                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                    // la recupero
                    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);

                    // y se la asigno al usuario
                    setDatos(fetchResp, usuario);
                }

                //return verified;  // success
                // devuelvo la información del usuario
                return usuario;  // success
            } else { // si no lo fue
                // aviso que la autentificacion no fue exitosa y no pudo ingresar a
                // la aplicacion
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

    /**
     * Verifica si se trata de un usuario con la sesión activa
     * @param req Requerimiento HTTP
     * @param user Usuario a comprobar su estado
     * @return true si se encuentra con la sesión activa, false en cualquier otro caso
     */
    @Override
    public boolean isUserLogged(HttpServletRequest req, WebsiteUsersEntity user) {
        if (user != null && user.equals((WebsiteUsersEntity) req.getSession().getAttribute(USER))) {
            return true;
        }

        return false;
    }
}
