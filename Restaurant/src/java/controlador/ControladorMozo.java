package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import logica.Mesa;
import logica.Mozo;
import utilidades.CustomException;

public class ControladorMozo implements Observer {
    
    private VistaMozo vista;
    private Mozo usuario;
    private ArrayList<Mesa> mesasDelMozo;
    
    public ControladorMozo(VistaMozo vista, Mozo usuario) {
        this.vista = vista;
        this.usuario = usuario;
        mesasDelMozo = usuario.getMesasAsignadas();
    }
    
    public void cargarMesas() {
        vista.cargarMesas(mesasDelMozo);
    }
    
    public void abrirMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.abrir();
                cargarMesas();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }
    
    public void vistaLista() {
        usuario.addObserver(this);
        cargarMesas();
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
    }

    public void cerrarMesa(Mesa mesa) {
        if (mesasDelMozo.contains(mesa)) {
            try {
                mesa.cerrar();
                cargarMesas();
            } catch (CustomException ex) {
                vista.notificarError(ex.getMessage());
            }
        } else {
            vista.notificarError("El mozo no contiene a la mesa!");
        }
    }

    @Override
    public void update(Observable orijen, Object evento) {
        if(evento.equals(Mozo.Eventos.listaMesas)){
            cargarMesas();
        }
    }
    
}
