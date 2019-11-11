package controlador;

import java.util.ArrayList;
import logica.Mesa;
import logica.Producto;

public interface VistaMozo {
    
    public void cargarMesas(ArrayList<Mesa> mesa);
    
    public void mostrarMesa(Mesa mesa);
    
    public void mostrarNombreUsuario(String nombreCompleto);

    public void mostrarProductos(ArrayList<Producto> productos);
    
    public void notificarError(String message);
    
}
