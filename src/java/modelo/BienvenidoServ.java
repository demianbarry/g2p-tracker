/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Finaliza el proceso de autentificación
 *
 * Una vez que el usuario ha sido autentificado (o no) por el proveedor
 * este controlador realiza las acciones pertinentes.
 *
 * @author Cristian Pacheco
 */
public class BienvenidoServ extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        try {

            // obtiene el objeto de autentificacion
            ISSO sso = Global.getInstance().getSso();


            System.out.println("URL After" + request.getRequestURL().toString());

            // el usuario ingresa al sistema
            IUsuario usuario = sso.login(request);

            if (!usuario.isConectado()) {
                solicitarLogin(response);
                return;
            }

            // otro usuario conectado
            Global.getInstance().addUsuario(usuario);

            //System.out.println("email: " + usuario.getEmails().nextElement());
            //System.out.println("nombre: " + usuario.getNombre());

            // por seguridad
            Global.getInstance().unsetSso();

        } catch (NoAutentificadoException e) {
            // si el usuario no se registro correctamente
            // lo envia de vuelta a la pagina de autentificacion

            response.sendRedirect("http://localhost:8468/OpenID3.1/MainServ"); // cambiar por la que corresponda

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {


        try {
        processRequest(request, response);

        }
        catch (NullPointerException e){
            solicitarLogin(response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {

        processRequest(request, response);

        }
        catch (NullPointerException e){
            solicitarLogin(response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // eliminar cuando corresponda
    public void solicitarLogin(HttpServletResponse response) throws IOException{
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
