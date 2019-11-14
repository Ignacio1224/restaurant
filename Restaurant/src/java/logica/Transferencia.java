package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public final class Transferencia {

    /* Atributos */
    private final Mozo emisor;
    private final Mozo receptor;
    private final Mesa mesa;

    public Transferencia(Mozo emisor, Mozo receptor, Mesa mesa) throws CustomException {
        this.emisor = emisor;
        this.receptor = receptor;
        this.mesa = mesa;
    }

    public void notificarReceptor() throws CustomException {
        receptor.agregarTransferenciaPendiente(this);
    }

    public void terminar(boolean aceptada) throws CustomException {
        emisor.eliminarTransferencia(this, aceptada);
        if (aceptada) {
            receptor.agregarMesaAsignada(mesa);
        }
    }

    /* Getters & Setters */
    public Mozo getEmisor() {
        return emisor;
    }

    public Mozo getReceptor() {
        return receptor;
    }

    public Mesa getMesa() {
        return mesa;
    }

    @Override
    public boolean equals(Object obj) {
        Transferencia other = (Transferencia) obj;
        return mesa.equals(other.getMesa()) && emisor.equals(other.getEmisor()) && receptor.equals(other.getReceptor());
    }
    
    

}
