package logica.beneficio;

import logica.Servicio;

/**
 *
 * @author Ignacio Cabrera
 */
public class Preferencial extends Beneficio {

    @Override
    public float calcularMonto(Servicio servicio) {
        return 0f;
    }

}
