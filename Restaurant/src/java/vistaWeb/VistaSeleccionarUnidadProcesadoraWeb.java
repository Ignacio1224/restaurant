package vistaWeb;

import controlador.ControladorSeleccionarUnidadProcesadora;
import controlador.VistaSeleccionarUnidadProcesadora;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Fachada;
import logica.Gestor;
import logica.UnidadProcesadora;
import utilidades.ComponentsHTML;

public class VistaSeleccionarUnidadProcesadoraWeb extends GenericViewWeb implements VistaSeleccionarUnidadProcesadora {

    private ControladorSeleccionarUnidadProcesadora controlador;
    private String destino;

    public VistaSeleccionarUnidadProcesadoraWeb(Gestor g) {
        super();
        controlador = new ControladorSeleccionarUnidadProcesadora(this, g);
    }

    @Override
    public void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String accion = request.getParameter("accion");

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

}
