package vistaWeb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Gestor;
import utilidades.CustomException;


@WebServlet(name = "GestorServlet", urlPatterns = {"/gestor"})
public class GestorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CustomException {
        response.setContentType("text/html;charset=UTF-8");

        Gestor gestor = (Gestor) request.getSession().getAttribute("Usuario");

        if (gestor == null) {
            response.sendRedirect("gestorLogin.jsp?message=Debe loguearse");
            return;
        }

        VistaGestorWeb vista;
        synchronized (this) {
            vista = (VistaGestorWeb) request.getSession().getAttribute("vistaGestor");
            if (vista == null) {
                vista = new VistaGestorWeb(gestor);
                request.getSession().setAttribute("vistaGestor", vista);
            }
        }

        vista.procesarRequest(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
        } catch (CustomException ex) {
            Logger.getLogger(GestorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
        } catch (CustomException ex) {
            Logger.getLogger(GestorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
