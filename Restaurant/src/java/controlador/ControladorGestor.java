package controlador;

import logica.Gestor;


public class ControladorGestor {
    
    private VistaGestor vista;
    private Gestor usuario;

    public ControladorGestor(VistaGestor vista, Gestor usuario) {
        this.vista = vista;
        this.usuario = usuario;
    }

    public void vistaLista() {
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        vista.mostrarNombreUnidadProcesadora(usuario.getUnidadProcesadora().getNombre());
    }
}
