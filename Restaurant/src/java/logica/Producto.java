package logica;

import java.util.Objects;
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

    /* Constructor */
    public Producto(String codigo, String nombre, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
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

    @Override
    public boolean equals(Object obj) {
        Producto other = (Producto) obj;
        return codigo.equals(other.getCodigo());
    }
    
        
    
}
