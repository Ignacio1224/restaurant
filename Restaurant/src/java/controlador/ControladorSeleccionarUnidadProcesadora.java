package controlador;

import logica.Fachada;
import logica.Gestor;
import logica.UnidadProcesadora;
import utilidades.CustomException;

public class ControladorSeleccionarUnidadProcesadora {

    private VistaSeleccionarUnidadProcesadora vista;
    private Gestor usuario;

    public ControladorSeleccionarUnidadProcesadora(VistaSeleccionarUnidadProcesadora vista, Gestor gestor) {
        this.vista = vista;
        usuario = gestor;
    }

    public void seleccionarUnidadProcesadora(Gestor gestor, UnidadProcesadora unidadProcesadora) {
        try {
            unidadProcesadora.addGestor(gestor);
            vista.mostrarGestionPedidos();
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

    public void vistaLista() {
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        cargarUnidadesProcesadoras();
    }

    private void cargarUnidadesProcesadoras() {
        vista.mostrarUnidadesProcesadoras(Fachada.getInstancia().getUnidadesProcesadoras());
    }

}
