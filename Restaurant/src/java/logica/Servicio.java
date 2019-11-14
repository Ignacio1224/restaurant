package logica;

import java.util.ArrayList;
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
    public void agregarItem(Item item) throws CustomException {
                
        if (item.getCantidad() > item.getProducto().getStock()) {
            throw new CustomException("No hay stock!");
        }
        
        item.getProducto().disminuirStock(item.getCantidad());
        items.add(item);
        
    }
    
    private Item getItem(Item item) {
//        for (Item i : items) {
//            if (i.equals(item)) {
//                return i;
//            }
//        }
        if(items.contains(item)) return item;
        else return null;
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
