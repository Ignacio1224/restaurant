package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.Fachada;
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
        vistaLista();
    }

    
    public void vistaLista() {
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        vista.mostrarNombreUnidadProcesadora(usuario.getUnidadProcesadora().getNombre());
        vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
        vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
    }


    public void tomarPedido(int indexItem) throws CustomException {
        Item item = usuario.getUnidadProcesadora().getItemsPendientes().get(indexItem);
        usuario.tomarItem(item);
        vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
    }

   
    public void finalizarPedido(int indexItem) throws CustomException {
        Item item = usuario.getItemsParaProcesar().get(indexItem);
        usuario.finalizar(item);
        vista.cargarPedidosTomados(usuario.getItemsParaProcesar());
    }
    
    
    @Override
    public void update(Observable origen, Object evento) {
        if (evento.equals(UnidadProcesadora.Eventos.recargarPanelItemsPendientes)) {
            vista.cargarPedidosPendientes(usuario.getUnidadProcesadora().getItemsPendientes());
        }
    }
}
