package logica;

import logica.beneficio.Beneficio;

public class Cliente {

    //<editor-fold desc="Atributos">
    private int id;
    private String email;
    private String nombre;
    private Beneficio beneficio;
    private Servicio servicio;
    private int oid;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Cliente() {
    }

    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public float calcularMonto() {
        return beneficio.calcularMonto(servicio);
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">

    /* Getters & Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
    //</editor-fold>

}
