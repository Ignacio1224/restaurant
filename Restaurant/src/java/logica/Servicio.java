package logica;

import java.util.ArrayList;
import java.util.Objects;
import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Servicio {

    /* Atributos */
    private Mesa mesaCorrespondiente;
    private ArrayList<Item> items;
    private Cliente cliente;

    /* Constructor */
    public Servicio(Mesa mesaCorrespondiente) {
        this.mesaCorrespondiente = mesaCorrespondiente;
        items = new ArrayList<>();
    }

    /* Comportamientos */
    public void agregarItem(int cantidad, String descripcion, float monto, Producto producto) throws CustomException {
        Item item = new Item(this, cantidad, descripcion, monto, producto);
        if (items.contains(item)) {
            throw new CustomException("El servicio ya posee el item!");
        }
        items.add(item);
    }

    public float calcularTotal() {
        float montoTotal = 0;

        for (Item item : items) {
            montoTotal += item.getMonto();
        }

        return montoTotal;
    }

    /* Getters & Setters */
    public Mesa getMesaCorrespondiente() {
        return mesaCorrespondiente;
    }

    public void setMesaCorrespondiente(Mesa mesaCorrespondiente) {
        this.mesaCorrespondiente = mesaCorrespondiente;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object obj) {
        Servicio other = (Servicio) obj;
        return mesaCorrespondiente.equals(other.getMesaCorrespondiente()) && items.equals(other.getItems());
    }

}
