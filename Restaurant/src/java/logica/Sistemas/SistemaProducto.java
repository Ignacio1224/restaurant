package logica.Sistemas;

import java.util.ArrayList;
import logica.Producto;
import mapeadores.MapeadorProducto;
import persistencia.Persistencia;
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
    
    public Producto getProductoByCodigo(int code) {
        for (Producto p : productos) {
            if (p.getCodigo() == code) {
                return p;
            }
        }
        return null;
    }
    
    public void cargarProductos() throws CustomException {
        productos = Persistencia.getInstancia().obtenerTodos(new MapeadorProducto());
    }
    
    /* Getters & Setters */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Producto> getProductosConStock() {
        ArrayList<Producto> ps = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() > 0) {
                ps.add(p);
            }
        }
        return ps;
    }

}
