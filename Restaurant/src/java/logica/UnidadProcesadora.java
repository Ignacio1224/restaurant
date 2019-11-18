package logica;

import java.util.ArrayList;
import java.util.Observable;
import utilidades.CustomException;

public class UnidadProcesadora extends Observable {

    //<editor-fold desc="Atributos">
    private String nombre;
    private ArrayList<Item> itemsPendientes;
    private ArrayList<Gestor> gestores;

    public enum Eventos {
        actualizarListaPendientes,
        actualizarItem
    };
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public UnidadProcesadora(String nombre) {
        this.nombre = nombre;
        this.itemsPendientes = new ArrayList();
        this.gestores = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    private void avisar(Eventos evento) {
        setChanged();
        notifyObservers(evento);
    }
    
    public void avisarFinalizado() {
        avisar(Eventos.actualizarItem);
    }
    public void agregarItem(Item item) {
        itemsPendientes.add(item);
        avisar(Eventos.actualizarListaPendientes);
    }

    public void addGestor(Gestor gestor) throws CustomException {

        if (gestores.contains(gestor)) {
            throw new CustomException("El gestor ya está ingresado!");
        }

        gestor.setUnidadProcesadora(this);
        gestores.add(gestor);
        
    }

    public void removeGestor(Gestor gestor) throws CustomException {

        if (!gestores.contains(gestor)) {
            throw new CustomException("El gestor no está ingresado!");
        }

        gestores.remove(gestor);
    }

    protected void itemTomado(Item item) {
        itemsPendientes.remove(item);
        avisar(Eventos.actualizarListaPendientes);
    }

    //</editor-fold>
    //<editor-fold desc="Getters & Setters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Item> getItemsPendientes() {
        return itemsPendientes;
    }

    public void setItemsPendientes(ArrayList<Item> itemsPendientes) {
        this.itemsPendientes = itemsPendientes;
    }

    public ArrayList<Gestor> getGestores() {
        return gestores;
    }

    public void setGestores(ArrayList<Gestor> gestores) {
        this.gestores = gestores;
    }
    //</editor-fold>

}
