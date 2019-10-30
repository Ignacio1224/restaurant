package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Mesa {

    // Atributos
    private int numero;
    private boolean abierta;
    private Mozo responsable;

    // Constructores
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
        }
    }

    public void cerrar() throws CustomException {
        if (abierta) {
            abierta = false;
        } else {
            throw new CustomException("La mesa ya está cerrada!");
        }
    }

    // Getters & Setters
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

    @Override
    public boolean equals(Object obj) {
        Mesa other = (Mesa) obj;
        return numero == other.getNumero();
    }

    
    
}
