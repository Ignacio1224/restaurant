package vistaWeb;

import controlador.ControladorSeleccionarUnidadProcesadora;
import controlador.VistaSeleccionarUnidadProcesadora;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Fachada;
import logica.Gestor;
import logica.UnidadProcesadora;
import utilidades.ComponentsHTML;

public class VistaSeleccionarUnidadProcesadoraWeb implements VistaSeleccionarUnidadProcesadora {

    private ControladorSeleccionarUnidadProcesadora controlador;
    private String destino;
    private PrintWriter out;

    public VistaSeleccionarUnidadProcesadoraWeb(Gestor g) {
        controlador = new ControladorSeleccionarUnidadProcesadora(this, g);
    }

    void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String accion = request.getParameter("accion");

        out = response.getWriter();
        Gestor gestor = (Gestor) request.getSession().getAttribute("Usuario");

        switch (accion) {
            case "conectarSSE":
                conectarSSE(request, response);
                controlador.vistaLista();
                break;

            case "seleccionarUnidadProcesadora":
                String unidadString = request.getParameter("unidadProcesadora");
                UnidadProcesadora unidadProcesadora = Fachada.getInstancia().getUnidadProcesadoraByName(unidadString);
                controlador.seleccionarUnidadProcesadora(gestor, unidadProcesadora);
                break;

        }

        if (destino != null) {
            response.sendRedirect(destino);
        }

    }

    @Override
    public void mostrarGestionPedidos() {
        destino = "gestor.jsp";
    }

    @Override
    public void notificarError(String message) {
        destino = "seleccionarUnidadProcesadora.jsp?message=" + message;
    }

    @Override
    public void mostrarNombreUsuario(String nombreCompleto) {
        enviar("eventoNombreUsuario", nombreCompleto);
    }

    @Override
    public void mostrarUnidadesProcesadoras(ArrayList<UnidadProcesadora> unidadesProcesadoras) {
        enviar("eventoCargarUnidadesProcesadoras", ComponentsHTML.armarUnidadesProcesadoras(unidadesProcesadoras));
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
}
