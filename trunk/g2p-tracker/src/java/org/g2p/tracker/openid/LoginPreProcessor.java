/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.openid;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.g2p.tracker.controllers.Constants;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.discovery.DiscoveryException;

/**
 * Comienza el proceso de autentificacion
 *
 * Una vez que el usuario ha elegido el proveedor, este controlador
 * se encargará de realizar la conexión con el mismo y enviará al usuario
 * al sitio del proveedor para que se autentifique
 *
 * @author Cristian Pacheco
 */
public class LoginPreProcessor implements Constants{

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String processRequest(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException, ConsumerException, DiscoveryException {

        // creo el objeto que llevara a cabo el proceso de autentificacion
        IOpenID auth = (IOpenID) new OpenIDFactory().create();

        // guardo el objeto de autentificacion
        // para poder invocarlo cuando el proveedor
        // me devuerlva el control
        request.getSession().setAttribute(SSO, auth);

        // invoco la autentificacion del usuario
        return auth.solicitarAutentificacion(url,
                request,
                response);
    }
}
