package logica.beneficio;
import logica.Item;
import logica.Servicio;

public class Comun extends Beneficio {

    public Comun(String descripcion) {
        super(descripcion);
    }

    @Override
    public float calcularMonto(Servicio servicio) {
        float descuento = 0;
        
        for (Item i : servicio.getItems()) {
            if (i.getProducto().getCodigo() == 8) {
                descuento += i.getProducto().getPrecio() * i.getCantidad();
            }
        }
        
        // Ahora es el total
        descuento = servicio.calcularTotal() - descuento;
        
        return descuento < 0 ? 0 : descuento;
    }
    
}
