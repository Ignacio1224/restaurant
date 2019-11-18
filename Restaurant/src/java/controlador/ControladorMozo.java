package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.Cliente;
import logica.Fachada;
import logica.Gestor;
import logica.Item;
import logica.Mesa;
import logica.Mozo;
import logica.Producto;
import logica.Servicio;
import logica.Transferencia;
import logica.UnidadProcesadora;
import utilidades.CustomException;

public class ControladorMozo implements Observer {

    private VistaMozo vista;
    private Mozo usuario;
    private ArrayList<Mesa> mesasDelMozo;

    public ControladorMozo(VistaMozo vista, Mozo usuario) {
        this.vista = vista;
        this.usuario = usuario;
        mesasDelMozo = usuario.getMesasAsignadas();
    }

    public void vistaLista() {
        cargarMesas();
        usuario.addObserver(this);
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
    }

    private void cargarMesas() {
        vista.cargarMesas(mesasDelMozo);
    }

    private void mostrarCuenta(Servicio s) {
        vista.mostrarCuenta(s);
    }

    public void abrirMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.abrir();
                cargarMesas();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }

    public void cerrarMesa(Mesa mesa, Cliente c) {
        if (mesasDelMozo.contains(mesa)) {
            mesa.getServicio().setCliente(c);
            mostrarCuenta(mesa.getServicio());
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }

    public void confirmarCierre(Mesa mesa) {
        try {
            mesa.cerrar();
            cargarMesas();
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    public void cargarProductos() {
        vista.mostrarProductos(Fachada.getInstancia().getProductosConStock());
    }

    public void aniadirItemAServicio(Mesa mesa, String codigoProducto, int cantidadProducto, String descripcionItem) {
        try {
            Servicio servicio = mesa.getServicio();
            Producto p = Fachada.getInstancia().getProductoByCodigo(codigoProducto);
            p.getuProcesadora().addObserver(this);
            servicio.agregarItem(new Item(servicio, cantidadProducto, descripcionItem, p));
            cargarMesas();
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    public void cargarMozosLogueados(Mozo m) {
        ArrayList<Mozo> mozosWOSelf = new ArrayList<>();
        ArrayList<Mozo> mozosTodos = Fachada.getInstancia().getMozosLogueados();

        for (Mozo mozo : mozosTodos) {
            if (!mozo.equals(m)) {
                mozosWOSelf.add(mozo);
            }
        }

        vista.mostrarMozosLogueados(mozosWOSelf);
    }

    public void iniciarTransferencia(Mozo mozo, Mozo mozoDestino, Mesa mesa) {
        try {
            mozo.iniciarTransferencia(mesa, mozoDestino);
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    @Override
    public void update(Observable origen, Object evento) {
        if (evento.equals(Mozo.Eventos.nuevaTransferencia)) {
            ArrayList<Transferencia> transferencias = ((Mozo) origen).getTransferenciasPendientes();
            vista.avisarNuevaTransferencia(transferencias.get(transferencias.size() - 1));
        }

        if (evento.equals(Mozo.Eventos.transferenciaAceptada)) {
            vista.cargarMesas(mesasDelMozo);
            vista.notificarResultadoTransferencia(true);
        }

        if (evento.equals(Mozo.Eventos.transferenciaRechazada)) {
            vista.notificarResultadoTransferencia(false);
        }

        if (evento.equals(UnidadProcesadora.Eventos.actualizarItem)) {
            vista.cargarMesas(mesasDelMozo);
            notificarCambioPedido();
        }
        
        if (evento.equals(UnidadProcesadora.Eventos.actualizarListaPendientes)) {
            vista.cargarMesas(mesasDelMozo);
        }
    }

    public void terminarTransferencia(Mozo mozo, Mesa mesa, boolean aceptada) {
        try {

            Mozo mozoOrigen = mesa.getResponsable();

            mozo.aceptarTransferencia(new Transferencia(mozoOrigen, mozo, mesa), aceptada);

            if (aceptada) {
                vista.cargarMesas(mesasDelMozo);
            }

        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    private void notificarCambioPedido() {
        Item item = null;

        // Items del mozo
        for (Mesa m : mesasDelMozo) {
            if (m.getServicio() != null) {
                for (Item i : m.getServicio().getItems()) {
                    
                    if (i.getProducto().getuProcesadora().getItemsPendientes().contains(i)) {
                        // El item esta pendiente
                        continue;
                    }
                    
                    boolean flag = false;
                    for (Gestor g : i.getProducto().getuProcesadora().getGestores()) {
                        if (g.getItemsParaProcesar().contains(i)) {
                            // El item esta siendo procesado
                            flag = true;
                        }
                    }

                    if (!flag) {
                        item = i;
                        break;
                    }

                }
            }
        }
        
        cargarMesas();
        
        if (item != null) {
            vista.avisarItemFinalizado(item);        
        }

    }

}
