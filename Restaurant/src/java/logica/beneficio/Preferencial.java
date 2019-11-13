package logica.beneficio;

import logica.Item;
import logica.Servicio;

/**
 *
 * @author Ignacio Cabrera
 */
public class Preferencial extends Beneficio {

    public Preferencial(String descripcion) {
        super(descripcion);
    }

    @Override
    public float calcularMonto(Servicio servicio) {
        
        float descuento = 0;
        float totalServicio = servicio.calcularTotal();
        for (Item i : servicio.getItems()) {
            if (i.getProducto().getCodigo().equals("agua")) {
                descuento += i.getProducto().getPrecio() * i.getCantidad();
            }
        }
        
        float total = totalServicio - descuento;
        
        if (totalServicio > 2000) {
            total -= totalServicio * 0.005;
        }
        
        return total;
    }

}
