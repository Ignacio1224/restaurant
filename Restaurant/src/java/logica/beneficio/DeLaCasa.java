package logica.beneficio;

import logica.Servicio;

public class DeLaCasa extends Beneficio {

    public DeLaCasa(String descripcion) {
        super(descripcion);
    }

    @Override
    public float calcularMonto(Servicio servicio) {
        float total = Math.round(servicio.calcularTotal() - 500);
        return total;
    }

}
