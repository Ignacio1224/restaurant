package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Mesa {

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

    public void abrir() throws CustomException {
        if (abierta) {
            throw new CustomException("La mesa ya está abierta!");
        } else {
            abierta = true;
            responsable.actualizarListadoMesas();
        }
    }

    public void cerrar() throws CustomException {
        if (!abierta) {
            throw new CustomException("La mesa ya está cerrada!");
        } else {
            abierta = false;
            responsable.actualizarListadoMesas();
        }
    }

    public void agrergarServicio() throws CustomException {
        if (servicio != null) {
            throw new CustomException("El servicio ya existe!");
        }
        this.servicio = new Servicio(this);
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
