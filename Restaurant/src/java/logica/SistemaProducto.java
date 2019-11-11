package logica;

import java.util.ArrayList;
import utilidades.CustomException;

public class SistemaProducto {

    /* Atributos */
    private ArrayList<Producto> productos;

    /* Constructor */
    public SistemaProducto() {
        productos = new ArrayList<>();
    }
    
    /* Comportamiento */
    public void agregarProducto(Producto producto) throws CustomException {
        if (productos.contains(producto)) {
            throw new CustomException("Producto ya registrado");
        }
        productos.add(producto);
    }
    
    public Producto getProductoByCodigo(String code) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(code)) {
                return p;
            }
        }
        return null;
    }
    
    /* Getters & Setters */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}
