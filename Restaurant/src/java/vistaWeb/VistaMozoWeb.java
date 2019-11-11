package vistaWeb;

import controlador.ControladorMozo;
import controlador.VistaMozo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Mesa;
import logica.Mozo;
import utilidades.ComponentsHTML;

public class VistaMozoWeb implements VistaMozo {

    private ControladorMozo controlador;
    private String destino;
    private HttpServletRequest request;
    private PrintWriter out;

    public VistaMozoWeb(Mozo m) {
        controlador = new ControladorMozo(this, m);
    }

    public void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Mozo mozo = (Mozo) request.getSession().getAttribute("Usuario");

        String accion = request.getParameter("accion");
        String mesaString = request.getParameter("mesa");

        Mesa mesa = null;
        
        if (mesaString != null) {
            int numeroMesa = Integer.parseInt(mesaString);
            mesa = mozo.getMesaByNumero(numeroMesa);
        }

        this.request = request;

        switch (accion) {
            case "conectarSSE":
                conectarSSE(request, response);
                controlador.vistaLista();
                break;
            case "abrirMesa":
                if (mesa == null) {
                    notificarError("Mesa no asignada");
                    break;
                }
                controlador.abrirMesa(mesa);
                break;
                
            case "cerrarMesa":
                if (mesa == null) {
                    notificarError("Mesa no asignada");
                    break;
                }
                controlador.cerrarMesa(mesa);
                break;
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
    public void cargarMesas(ArrayList<Mesa> mesas) {
        String mesasString = "";

        for (Mesa m : mesas) {
            mesasString += ComponentsHTML.ArmarMesaHTML(m);
        }

        enviar("eventoCargarMesas", mesasString);
    }

    @Override
    public void mostrarMesa(Mesa mesa) {

    }

    @Override
    public void mostrarNombreUsuario(String nombreCompleto) {
        enviar("eventoNombreUsuario", nombreCompleto);
    }

    @Override
    public void notificarError(String message) {
        enviar("eventoNotificarError", message);
    }

}
