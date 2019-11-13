package logica.beneficio;
import logica.Servicio;

/**
 *
 * @author Ignacio Cabrera
 */
public class Comun extends Beneficio {

    @Override
    public float calcularMonto(Servicio servicio) {
        return 0f;
    }
    
}
