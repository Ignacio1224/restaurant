package vistaWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Gestor;


public class SeleccionarUnidadProcesadoraServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        Gestor g = (Gestor) request.getSession().getAttribute("Usuario");
        
        if (g == null) {
            response.sendRedirect("gestorLogin.jsp?message=Debe loguearse");
            return;
        }
        
        VistaSeleccionarUnidadProcesadoraWeb vista;
        synchronized (this) {
            vista = (VistaSeleccionarUnidadProcesadoraWeb) request.getSession().getAttribute("vistaGestorUnidadProcesadora");
            
            if (vista == null) {
                vista = new VistaSeleccionarUnidadProcesadoraWeb(g);
                request.getSession().setAttribute("vistaGestorUnidadProcesadora", vista);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
