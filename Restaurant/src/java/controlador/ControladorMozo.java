package controlador;

import java.util.ArrayList;
import logica.Fachada;
import logica.Item;
import logica.Mesa;
import logica.Mozo;
import logica.Servicio;
import utilidades.CustomException;

public class ControladorMozo {

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
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
    }

    private void cargarMesas() {
        vista.cargarMesas(mesasDelMozo);
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

    public void cerrarMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.cerrar();
                cargarMesas();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
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

}
