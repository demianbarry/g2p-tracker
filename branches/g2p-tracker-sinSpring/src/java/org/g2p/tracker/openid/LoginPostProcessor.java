/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.openid;

import java.io.IOException;
import java.io.PrintWriter;
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

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        try {

            // obtiene el objeto de autentificacion
            ISSO sso = (ISSO) request.getSession().getAttribute(SSO);

            System.out.println("URL After " + request.getRequestURL().toString());

            // el usuario ingresa al sistema
            WebsiteUsersEntity usuario = sso.login(request);

            if (!sso.isUserLogged(request, usuario)) {
                solicitarLogin(response);
                return;
            }

            // otro usuario conectado
            request.getSession().setAttribute(USER_ID, usuario);

            response.sendRedirect("http://localhost:8081/g2p-tracker-sinSpring/");

        } catch (NoAutentificadoException e) {
            // si el usuario no se registro correctamente
            // lo envia de vuelta a la pagina de autentificacion

            response.sendRedirect("http://localhost:8081/g2p-tracker-sinSpring/LoginPage.zul"); // cambiar por la que corresponda

        }
    }

    // eliminar cuando corresponda
    public static void solicitarLogin(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>No Autorizado</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Debe loguearse para ingresar al sistema</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
