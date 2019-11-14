package controlador;

import java.util.ArrayList;
import logica.Mesa;
import logica.Mozo;
import logica.Producto;
import logica.Servicio;
import logica.Transferencia;

public interface VistaMozo {
    
    public void cargarMesas(ArrayList<Mesa> mesa);
        
    public void mostrarNombreUsuario(String nombreCompleto);

    public void mostrarProductos(ArrayList<Producto> productos);
    
    public void notificarError(String message);

    public void mostrarCuenta(Servicio s);

    public void mostrarMozosLogueados(ArrayList<Mozo> mozosWOSelf);

    public void avisarNuevaTransferencia(Transferencia transferencia);

    public void notificarResultadoTransferencia(boolean resultado);
    
}
