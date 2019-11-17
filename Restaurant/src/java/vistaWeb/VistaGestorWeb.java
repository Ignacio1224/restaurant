package vistaWeb;

import controlador.ControladorGestor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Gestor;
import controlador.VistaGestor;
import java.util.ArrayList;
import logica.Item;
import utilidades.CustomException;

public class VistaGestorWeb implements VistaGestor {

    private ControladorGestor controlador;
    private String destino;
    private PrintWriter out;

    /*Constructor*/
    public VistaGestorWeb(Gestor gestor) {
        this.controlador = new ControladorGestor(this, gestor);
    }

    public void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, CustomException {

        //Gestor gestor = (Gestor) request.getSession().getAttribute("Usuario");
        String accion = request.getParameter("accion");

        switch (accion) {
            case ("conectarSSE"): {
                conectarSSE(request, response);
                controlador.vistaLista();
                break;
            }
            case ("tomarPedido"): {
                controlador.tomarPedido(Integer.parseInt(request.getParameter("indexItem")));
                break;
            }
            case ("finalizarPedido"):{
                controlador.finalizarPedido(Integer.parseInt(request.getParameter("indexItem")));
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

    @Override
    public void cargarPedidosPendientes(ArrayList<Item> itemsPendientes) {
        enviar("eventoCargarPedidosPendientes", utilidades.ComponentsHTML.armarPedidosPendientes(itemsPendientes));
    }

    @Override
    public void cargarPedidosTomados(ArrayList<Item> itemsParaProcesar) {
        enviar("eventoCargarPedidosTomados", utilidades.ComponentsHTML.armarPedidosTomados(itemsParaProcesar));
    }

}
