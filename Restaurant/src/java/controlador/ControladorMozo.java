package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.Cliente;
import logica.Fachada;
import logica.Item;
import logica.Mesa;
import logica.Mozo;
import logica.Servicio;
import logica.Transferencia;
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
            servicio.agregarItem(new Item(servicio, cantidadProducto, descripcionItem, Fachada.getInstancia().getProductoByCodigo(codigoProducto)));
            cargarMesas();
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    public void cargarMozosLogueados(Mozo m) {
        ArrayList<Mozo> mozosWOSelf = new ArrayList<>();
        ArrayList<Mozo> mozosTodos = Fachada.getInstancia().getMozosLogueados();
        
        for(Mozo mozo : mozosTodos) {
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
            vista.avisarNuevaTransferencia(transferencias.get(transferencias.size()-1));
        }
        
        if (evento.equals(Mozo.Eventos.transferenciaAceptada)) {
            vista.cargarMesas(mesasDelMozo);
            vista.notificarResultadoTransferencia(true);
        }
        
        if (evento.equals(Mozo.Eventos.transferenciaRechazada)) {
            vista.notificarResultadoTransferencia(false);
        }
    }

    public void terminarTransferencia(Mozo mozo, Mesa mesa, boolean aceptada) {
        try {
            
            Mozo mozoOrigen = mozo.getTransferenciaPendienteByMesa(mesa).getEmisor();
            
            if (mozoOrigen == null) {
                vista.notificarError("A ocurrido un error!");
                return;
            }
            
            mozo.aceptarTransferencia(new Transferencia(mozoOrigen, mozo, mesa), aceptada);
            
            if (aceptada) {
                vista.cargarMesas(mesasDelMozo);
            }
            
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

}
