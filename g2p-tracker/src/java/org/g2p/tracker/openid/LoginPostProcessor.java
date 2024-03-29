/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.openid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;

/**
 * Finaliza el proceso de autentificación
 *
 * Una vez que el usuario ha sido autentificado (o no) por el proveedor
 * este controlador realiza las acciones pertinentes.
 *
 * @author Cristian Pacheco
 */
public class LoginPostProcessor implements Constants {

    public LoginPostProcessor() {
    }
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static Properties properties;

    public static void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException, NoAutentificadoException, Exception {

        // obtiene el objeto de autentificacion
        ISSO sso = (ISSO) request.getSession().getAttribute(SSO);

        System.out.println("URL After " + request.getRequestURL().toString());

        // el usuario ingresa al sistema
        WebsiteUsersEntity usuario = sso.login(request);

        // si se obtuvieron sus datos exitosamente
        if (usuario != null) {
            request.getSession().setAttribute(USER, usuario);
            request.getSession().setAttribute(USER_NAME, usuario.getNombre() + " " + usuario.getApellido());
        } else {
            // si no se ha obtenido su ID abortar
            if (request.getSession().getAttribute(CLAIMED_ID) == null) {
                return;
            }
        }

        InputStream is = (request.getSession().getServletContext().getResourceAsStream("/WEB-INF/openid.properties"));

        properties = new Properties();
        properties.load(is);

        
        String app_url = properties.getProperty("app_url");

        // se redirige a la pagina principal de la aplicacion
        response.sendRedirect(app_url);
        return;
    }

    // eliminar cuando corresponda
    public static void solicitarLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(LOGIN_PAGE);
    }
}
