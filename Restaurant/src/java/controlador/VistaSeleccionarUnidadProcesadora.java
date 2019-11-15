package controlador;

import java.util.ArrayList;
import logica.UnidadProcesadora;


public interface VistaSeleccionarUnidadProcesadora {

    public void mostrarGestionPedidos();

    public void notificarError(String message);

    public void mostrarNombreUsuario(String nombreCompleto);

    public void mostrarUnidadesProcesadoras(ArrayList<UnidadProcesadora> unidadesProcesadoras);
    
}
