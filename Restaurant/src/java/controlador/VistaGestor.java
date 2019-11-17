package controlador;

import java.util.ArrayList;
import logica.Item;

public interface VistaGestor {

    public void mostrarNombreUsuario(String nombreUsuario);

    public void mostrarNombreUnidadProcesadora(String nombre);

    public void cargarPedidosPendientes(ArrayList<Item> itemsPendientes);

    public void cargarPedidosTomados(ArrayList<Item> itemsParaProcesar);

}
