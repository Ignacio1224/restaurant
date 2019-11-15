package vistaWeb;

import controlador.ControladorMozo;
import controlador.VistaMozo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Cliente;
import logica.Fachada;
import logica.Mesa;
import logica.Mozo;
import logica.Producto;
import logica.Servicio;
import logica.Transferencia;
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
            mesa = Fachada.getInstancia().getMesaByNumero(numeroMesa);
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
                
                String idCliente = request.getParameter("idCliente");
                Cliente c = null;

                if (!idCliente.isEmpty()) {
                    c = Fachada.getInstancia().getClienteById(Integer.parseInt(idCliente));
                    if (c == null) {
                        notificarError("Cliente no encontrado!");
                        break;
                    }
                }

                controlador.cerrarMesa(mesa, c);
                break;

            case "confirmarCierre":
                if (mesa == null) {
                    notificarError("Mesa no asignada");
                    break;
                }
                
                controlador.confirmarCierre(mesa);
                break;

            case "cargarProductos":
                controlador.cargarProductos();
                break;

            case "getMozosLogueados":
                controlador.cargarMozosLogueados(mozo);
                break;
                
            case "confirmarTransferencia":
                Boolean aceptada = Boolean.parseBoolean(request.getParameter("confirmada"));
                
                controlador.terminarTransferencia(mozo, mesa, aceptada);
                break;

            case "transferirMesa":
                if (mesa == null) {
                    notificarError("Mesa no asignada");
                    break;
                }
                
                Mozo mozoDestino = Fachada.getInstancia().getMozosByUsername(request.getParameter("mozo"));
                System.out.println(mozoDestino.toString());
                
                if (mozoDestino == null) {
                    notificarError("Mozo no encontrado");
                    break;
                }
                
                controlador.iniciarTransferencia(mozo, mozoDestino, mesa);
                break;

            case "aniadirItemAServicio":
                if (mesa == null) {
                    notificarError("Mesa no asignada");
                    break;
                }
                
                String codigoProducto = request.getParameter("codigoProducto");
                int cantidadProducto = Integer.parseInt(request.getParameter("cantidadProducto"));
                String descripcionItem = request.getParameter("descripcionItem");

                controlador.aniadirItemAServicio(mesa, codigoProducto, cantidadProducto, descripcionItem);

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
            mesasString += ComponentsHTML.armarMesa(m);
        }

        enviar("eventoCargarMesas", mesasString);
    }

    @Override
    public void mostrarNombreUsuario(String nombreCompleto) {
        enviar("eventoNombreUsuario", nombreCompleto);
    }

    @Override
    public void notificarError(String message) {
        enviar("eventoNotificarError", message);
    }

    @Override
    public void mostrarProductos(ArrayList<Producto> productos) {
        String productosString = "";

        for (Producto p : productos) {
            productosString += ComponentsHTML.armarProductos(p);
        }

        enviar("eventoCargarProductos", productosString);

    }

    @Override
    public void mostrarCuenta(Servicio s) {
        enviar("eventoMostrarCuenta", ComponentsHTML.armarCuenta(s));
    }

    @Override
    public void mostrarMozosLogueados(ArrayList<Mozo> mozosWOSelf) {

        String mozos = "";
        for (Mozo m : mozosWOSelf) {
            mozos += ComponentsHTML.armarMozo(m);
        }

        enviar("eventoMozosLogueados", mozos);

    }

    @Override
    public void avisarNuevaTransferencia(Transferencia transferencia) {
        enviar("eventoNumeroMesa", transferencia.getMesa().getNumero() + "");
        enviar("eventoRecepcionTransferencia", ComponentsHTML.armarTransferencia(transferencia));
    }

    @Override
    public void notificarResultadoTransferencia(boolean resultado) {
        enviar("eventoResultadoTransferencia", ComponentsHTML.armarResultadoTransferencia(resultado));
    }

}
