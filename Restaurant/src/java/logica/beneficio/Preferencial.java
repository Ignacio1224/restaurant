package logica.beneficio;

import logica.Item;
import logica.Servicio;

public class Preferencial extends Beneficio {

    public Preferencial(String descripcion) {
        super(descripcion);
    }

    @Override
    public float calcularMonto(Servicio servicio) {
        
        float descuento = 0;
        float totalServicio = servicio.calcularTotal();
        for (Item i : servicio.getItems()) {
            if ( i.getProducto().getCodigo() == 8 ) {
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
