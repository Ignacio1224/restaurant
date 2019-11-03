package logica;

/**
 *
 * @author Ignacio Cabrera
 */
public class Servicio {

    /* Atributos */
    private Mesa mesaCorrespondiente;

    /* Constructor */
    public Servicio(Mesa mesaCorrespondiente) {
        this.mesaCorrespondiente = mesaCorrespondiente;
    }

    /* Getters & Setters */
    public Mesa getMesaCorrespondiente() {
        return mesaCorrespondiente;
    }

    public void setMesaCorrespondiente(Mesa mesaCorrespondiente) {
        this.mesaCorrespondiente = mesaCorrespondiente;
    }

}
