package controlador;

import java.util.Observable;
import java.util.Observer;
import logica.Gestor;
import logica.Item;
import logica.UnidadProcesadora;
import utilidades.CustomException;

public class ControladorGestor implements Observer {

    private VistaGestor vista;
    private Gestor usuario;

    public ControladorGestor(VistaGestor vista, Gestor usuario) {
        this.vista = vista;
        this.usuario = usuario;
    }

    public void vistaLista() {
        usuario.getUnidadProcesadora().addObserver(this);
        usuario.addObserver(this);
        
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        vista.mostrarNombreUnidadProcesadora(usuario.getUnidadProcesadora().getNombre());
        vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
        vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
    }

    public void tomarPedido(Item item) {
        try {
            usuario.tomarItem(item);
            vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    public void finalizarPedido(Item item) {
        try {
            usuario.finalizar(item);
            vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    @Override
    public void update(Observable origen, Object evento) {
        if (evento.equals(UnidadProcesadora.Eventos.actualizarListaPendientes)) {
            vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
            vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
        }
    }
}
