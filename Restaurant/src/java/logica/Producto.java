package logica;

import utilidades.CustomException;

public class Producto {

    //<editor-fold desc="Atributos">
    private int codigo;
    private String nombre;
    private int stock;
    private float precio;
    private UnidadProcesadora uProcesadora;
    private int oid;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Producto() {
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public void disminuirStock(int cantidad) throws CustomException {
        if (cantidad > stock) {
            throw new CustomException("No puedes ordenar una cantidad más grande que el stock");
        }
        stock -= cantidad;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public UnidadProcesadora getuProcesadora() {
        return uProcesadora;
    }

    public void setuProcesadora(UnidadProcesadora uProcesadora) {
        this.uProcesadora = uProcesadora;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
    //</editor-fold>

    //<editor-fold desc="Equals">
    @Override
    public boolean equals(Object obj) {
        Producto other = (Producto) obj;
        return codigo == other.getCodigo();
    }
    //</editor-fold>

    //<editor-fold desc="To String">
    @Override
    public String toString() {
        return "Producto: " + nombre + " (" + codigo + ") $" + precio + " (" + uProcesadora.getNombre() + ")";
    }
    //</editor-fold>

}
