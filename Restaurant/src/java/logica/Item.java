package logica;

import java.util.Objects;
import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Item {

    /* Atributos */
    private Servicio servicio;
    private int cantidad;
    private String descripcion;
    private float monto;
    private Producto producto;

    /* Constructor */
    public Item(Servicio servicio, int cantidad, String descripcion, float monto, Producto producto) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.monto = monto;
        this.producto = producto;
    }

    /* Comportamientos */
    public void avisarParaProcesar() {

    }

    /* Getters & Setters */
    public Servicio getServicio() {
        return servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public boolean equals(Object obj) {
        Item other = (Item) obj;
        return servicio.equals(other.getServicio()) && producto.equals(other.getProducto());
    }

    
}
