package logica.beneficio;

import logica.Servicio;

/**
 *
 * @author Ignacio Cabrera
 */
public abstract class Beneficio {
    
    /* Atributos */
    protected String descripcion;
    
    /* Comportamientos */
    public abstract float calcularMonto(Servicio servicio);
    
    /* Getters & Setters */
    public String getDescripcion() {
        return descripcion;
    }
}
