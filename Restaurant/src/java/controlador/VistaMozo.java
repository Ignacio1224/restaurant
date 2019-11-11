package controlador;

import java.util.ArrayList;
import logica.Mesa;

public interface VistaMozo {
    
    public void cargarMesas(ArrayList<Mesa> mesa);
    
    public void mostrarMesa(Mesa mesa);
    
    public void mostrarNombreUsuario(String nombreCompleto);

    public void notificarError(String message);
    
}
