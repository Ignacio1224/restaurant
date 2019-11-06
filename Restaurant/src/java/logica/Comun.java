package logica;

import logica.beneficio.Beneficio;

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
