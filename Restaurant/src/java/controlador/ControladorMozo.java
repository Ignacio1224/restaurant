package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.Fachada;
import logica.Item;
import logica.Mesa;
import logica.Mozo;
import logica.Servicio;
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
    
    private void cargarMesas() {
        vista.cargarMesas(mesasDelMozo);
    }
    
    public void abrirMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.abrir();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }
    
    public void vistaLista() {
        usuario.addObserver(this);
        for (Mesa m : mesasDelMozo) {
            m.addObserver(this);
        }
        cargarMesas();
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
    }

    public void cerrarMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.cerrar();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }

    @Override
    public void update(Observable origen, Object evento) {
        if(evento.equals(Mozo.Eventos.listaMesas)){
            cargarMesas();
        }
    }

    public void cargarProductos() {
        vista.mostrarProductos(Fachada.getInstancia().getProductos());
    }

    public void aniadirItemAServicio(Mesa mesa, String codigoProducto, int cantidadProducto, String descripcionItem) {
        try {
            Servicio servicio = mesa.getServicio();
            servicio.agregarItem(new Item(servicio, cantidadProducto, descripcionItem, Fachada.getInstancia().getProductoByCodigo(codigoProducto)));
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }
    
}
