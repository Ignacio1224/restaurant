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
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        vista.mostrarNombreUnidadProcesadora(usuario.getUnidadProcesadora().getNombre());
        vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
        vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
    }

    public void tomarPedido(String indexItemS) {
        try {

            int indexItem = Integer.parseInt(indexItemS);
            Item item = usuario.getUnidadProcesadora().getItemsPendientes().get(indexItem);
            usuario.tomarItem(item);

            vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
        } catch (CustomException ex) {

        }
    }

    public void finalizarPedido(String indexItemS) {
        try {
            int indexItem = Integer.parseInt(indexItemS);
            Item item = usuario.getItemsParaProcesar().get(indexItem);
            usuario.finalizar(item);
            vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
        } catch (CustomException ex) {
            
        }
    }

    @Override
    public void update(Observable origen, Object evento) {
        if (evento.equals(UnidadProcesadora.Eventos.recargarPanelItemsPendientes)) {
            vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
        }
    }
}
