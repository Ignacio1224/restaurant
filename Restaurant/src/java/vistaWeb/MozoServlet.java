package vistaWeb;

import datosPrueba.DatosPrueba;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Mozo;

/**
 *
 * @author Ignacio
 */
public class MozoServlet extends HttpServlet {

    public MozoServlet() {
        DatosPrueba.cargarMesas();
        DatosPrueba.cargarProductos();
        DatosPrueba.cargarClientes();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Mozo m = (Mozo) request.getSession().getAttribute("Usuario");
        if (m == null) {
            response.sendRedirect("mozoLogin.jsp?message=Debe loguearse");
            return;
        }
        
        VistaMozoWeb vista;
        synchronized (this) {
            vista = (VistaMozoWeb) request.getSession().getAttribute("vistaMozo");
            if (vista == null) {
                vista = new VistaMozoWeb(m);
                request.getSession().setAttribute("vistaMozo", vista);
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
