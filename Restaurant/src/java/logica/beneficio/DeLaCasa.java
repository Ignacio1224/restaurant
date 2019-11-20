package logica.beneficio;

import logica.Servicio;

public class DeLaCasa extends Beneficio {

    public DeLaCasa(String descripcion) {
        super(descripcion);
    }

    @Override
    public float calcularMonto(Servicio servicio) {
        float total = Math.abs(servicio.calcularTotal() - 500);
        return total;
    }

}
