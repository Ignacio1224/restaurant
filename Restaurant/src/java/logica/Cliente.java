package logica;

import logica.beneficio.Beneficio;

public class Cliente {

    /* Atributos */
    private int id;
    private String email;
    private String nombre;
    private Beneficio beneficio;
    private Servicio servicio;
    private int oid;

    /* Constructor */
    public Cliente() {
    }

    public Cliente(int id, String email, String nombre) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
    }

    public Cliente(int id, String email, String nombre, Beneficio beneficio) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.beneficio = beneficio;
    }

    public float calcularBeneficio() {
        return beneficio.calcularMonto(servicio);
    }

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

}
