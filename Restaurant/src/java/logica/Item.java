package logica;

/**
 *
 * @author Ignacio Cabrera
 */
public class Item {

    /* Atributos */
    private Servicio servicio;

    /* Constructor */
    public Item(Servicio servicio) {
        this.servicio = servicio;
    }

    /* Getters & Setters */
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
