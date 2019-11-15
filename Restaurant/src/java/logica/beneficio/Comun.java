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
            if (i.getProducto().getCodigo().equals("cafe")) {
                descuento += i.getProducto().getPrecio() * i.getCantidad();
            }
        }
        
        return servicio.calcularTotal() - descuento;
    }
    
}
