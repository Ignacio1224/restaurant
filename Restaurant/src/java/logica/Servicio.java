package logica;

import java.util.ArrayList;
import utilidades.CustomException;

public class Servicio {

    //<editor-fold desc="Atributos">
    private Mesa mesaCorrespondiente;
    private ArrayList<Item> items;
    private Cliente cliente;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Servicio(Mesa mesaCorrespondiente) {
        this.mesaCorrespondiente = mesaCorrespondiente;
        items = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public void agregarItem(Item item) throws CustomException {

        if (item.getCantidad() > item.getProducto().getStock()) {
            throw new CustomException("No hay stock!");
        }

        item.getProducto().disminuirStock(item.getCantidad());
        item.avisarParaProcesar();
        items.add(item);

    }

    public float calcularTotal() {
        float montoTotal = 0;

        for (Item item : items) {
            montoTotal += item.getMonto();
        }

        return montoTotal;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
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
        if (this.cliente != null) {
            this.cliente.setServicio(this);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Equals">
    @Override
    public boolean equals(Object obj) {
        Servicio other = (Servicio) obj;
        return mesaCorrespondiente.equals(other.getMesaCorrespondiente()) && items.equals(other.getItems());
    }
    //</editor-fold>

}
