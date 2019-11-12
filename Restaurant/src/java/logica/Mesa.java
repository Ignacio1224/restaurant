package logica;

import java.util.Observable;
import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Mesa extends Observable {

    /* Atributos */
    private int numero;
    private boolean abierta;
    private Mozo responsable;
    private Servicio servicio;

    /* Constructores */
    public Mesa(int numero, boolean abierta, Mozo responsable) {
        this.numero = numero;
        this.abierta = abierta;
        this.responsable = responsable;
    }

    public Mesa(int numero, Mozo responsable) {
        this.numero = numero;
        this.abierta = false;
        this.responsable = responsable;
    }
    
    public enum Eventos {
        actualizar
    }
    
    private void avisar(Eventos evento) {
        setChanged();
        notifyObservers(evento);
    }

    /* Comportamiento */
    public void abrir() throws CustomException {
        if (abierta) {
            throw new CustomException("La mesa ya está abierta!");
        } else {
            abierta = true;
            servicio = new Servicio(this);
            responsable.actualizarListadoMesas();
        }
    }

    public void cerrar() throws CustomException {
        if (!abierta) {
            throw new CustomException("La mesa ya está cerrada!");
        } else {
            abierta = false;
            servicio = null;
            responsable.actualizarListadoMesas();
        }
    }

    /* Getters & Setters */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setResponsable(Mozo responsable) {
        this.responsable = responsable;
    }

    public Mozo getResponsable() {
        return responsable;
    }

    public Servicio getServicio() {
        return servicio;
    }

    @Override
    public boolean equals(Object obj) {
        Mesa other = (Mesa) obj;
        return numero == other.getNumero();
    }

    @Override
    public String toString() {
        return "Mesa " + numero + (abierta ? " Abierta" : " Cerrada");
    }

}
