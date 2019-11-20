package logica.beneficio;

import logica.Servicio;

public abstract class Beneficio {

    //<editor-fold desc="Atributos">
    protected String descripcion;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Beneficio(String descripcion) {
        this.descripcion = descripcion;
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public abstract float calcularMonto(Servicio servicio);
    //<editor-fold desc="Getters & Setters">

    public String getDescripcion() {
        return descripcion;
    }
    //</editor-fold>

}
