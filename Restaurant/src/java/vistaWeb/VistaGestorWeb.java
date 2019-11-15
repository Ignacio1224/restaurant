package vistaWeb;

import controlador.ControladorGestor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Gestor;
import controlador.VistaGestor;

public class VistaGestorWeb implements VistaGestor {

    private ControladorGestor controlador;
    private String destino;
    private PrintWriter out;

    /*Constructor*/
    public VistaGestorWeb(Gestor gestor) {
        this.controlador = new ControladorGestor(this, gestor);
    }

    @Override
    public void mostrarNombreUsuario(String nombreUsuario) {
        enviar("eventoMostrarNombreUsuario", nombreUsuario);
    }

    public void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gestor gestor = (Gestor) request.getSession().getAttribute("Usuario");
        String accion = request.getParameter("accion");

        switch (accion) {
            case ("conectarSSE"): {
                conectarSSE(request, response);
                controlador.vistaLista();
                break;
            }
        }

        if (destino != null) {
            response.sendRedirect(destino);
        }

    }

    private void conectarSSE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext contexto = request.startAsync();
        contexto.getResponse().setContentType("text/event-stream");
        contexto.getResponse().setCharacterEncoding("UTF-8");
        contexto.setTimeout(0);
        out = contexto.getResponse().getWriter();

    }

    private void enviar(String evento, String dato) {
        out.write("event: " + evento + "\n");
        dato = dato.replace("\n", "");
        out.write("data: " + dato + "\n\n");
        if (out.checkError()) {
            System.out.println("Error");
        }
    }

    @Override
    public void mostrarNombreUnidadProcesadora(String nombre) {
        enviar("eventoNombreUnidadProcesadora", nombre);
    }

}
