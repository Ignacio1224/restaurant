package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Producto {

    /* Atributos */
    private String codigo;
    private String nombre;
    private int stock;
    private float precio;
    private UnidadProcesadora uProcesadora;

    /* Constructor */
    public Producto(String codigo, String nombre, int stock, float precio, UnidadProcesadora uProcesadora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.uProcesadora = uProcesadora;
    }

    /* Comportamientos */
    public void disminuirStock(int cantidad) throws CustomException {
        if (cantidad > stock) {
            throw new CustomException("No puedes ordenar una cantidad más grande que el stock");
        }
        stock -= cantidad;
    }

    /* Getters & Setters */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    @Override
    public boolean equals(Object obj) {
        Producto other = (Producto) obj;
        return codigo.equals(other.getCodigo());
    }

}
