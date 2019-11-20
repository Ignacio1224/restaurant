package vistaWeb;

import controlador.ControladorGestor;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Gestor;
import controlador.VistaGestor;
import java.util.ArrayList;
import logica.Item;
import utilidades.ComponentsHTML;

public class VistaGestorWeb extends GenericViewWeb implements VistaGestor {

    private ControladorGestor controlador;
    private String destino;

    public VistaGestorWeb(Gestor gestor) {
        super();
        controlador = new ControladorGestor(this, gestor);
    }

    @Override
    public void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gestor usuario = (Gestor) request.getSession().getAttribute("Usuario");
        String accion = request.getParameter("accion");

        switch (accion) {
            case "conectarSSE": {
                conectarSSE(request, response);
                controlador.vistaLista();
                break;
            }
            case "tomarPedido": {
                Item itemP = null;
                String indexItemS = request.getParameter("indexItem");
                if (!indexItemS.isEmpty()) {
                    int indexItem = Integer.parseInt(indexItemS);
                    itemP = usuario.getUnidadProcesadora().getItemsPendientes().get(indexItem);
                    if (itemP == null) {
                        notificarError("No se ha encontrado el item");
                    }
                }
                controlador.tomarPedido(itemP);
                break;
            }
            case "finalizarPedido": {
                Item itemF = null;
                String indexItemS = request.getParameter("indexItem");
                if (!indexItemS.isEmpty()) {
                    int indexItem = Integer.parseInt(indexItemS);
                    itemF = usuario.getItemsParaProcesar().get(indexItem);
                    if (itemF == null) {
                        notificarError("No se ha encontrado el item");
                    }
                }
                controlador.finalizarPedido(itemF);
                break;
            }
        }

        if (destino != null) {
            response.sendRedirect(destino);
        }

    }

    @Override
    public void mostrarNombreUsuario(String nombreUsuario) {
        enviar("eventoMostrarNombreUsuario", nombreUsuario);
    }

    @Override
    public void mostrarNombreUnidadProcesadora(String nombre) {
        enviar("eventoNombreUnidadProcesadora", nombre);
    }

    @Override
    public void cargarPedidosPendientes(ArrayList<Item> itemsPendientes) {
        enviar("eventoCargarPedidosPendientes", ComponentsHTML.armarPedidosPendientes(itemsPendientes));
    }

    @Override
    public void cargarPedidosTomados(ArrayList<Item> itemsParaProcesar) {
        enviar("eventoCargarPedidosTomados", ComponentsHTML.armarPedidosTomados(itemsParaProcesar));
    }

    @Override
    public void notificarError(String message) {
        enviar("eventoNotificarError", message);
    }

}
