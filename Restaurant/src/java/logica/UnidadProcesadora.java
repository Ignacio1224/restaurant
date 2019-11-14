package logica;

import java.util.ArrayList;

public class UnidadProcesadora {

    private String nombre;
    private ArrayList<Item> itemsPendientes;
    private ArrayList<Gestor> gestores;

    public UnidadProcesadora(String nombre) {
        this.nombre = nombre;
        this.itemsPendientes = new ArrayList();
        this.gestores = new ArrayList();
    }

    public boolean agregarItem(Item i) {
//        TODO: Uso de excepciones (CustomException), la función no será mas bool (Algo asi como que si el item está en la lista de pendientes que tire excepción, sino no pasa nada)
        if (!itemsPendientes.contains(i)) {
            itemsPendientes.add(i);
            return true;
        }
        return false;
    }

    public boolean addGestor(Gestor g) {
//        TODO: Lo mismo que el metodo anterior, uso de excepciones 
        if (!this.gestores.contains(g)) {
            this.gestores.add(g);
            return true;
        }
        return false;
    }

    public boolean removeGestor(Gestor g) {
//        TODO: Uso de excepciones
        if (this.gestores.contains(g)) {
            this.gestores.remove(g);
            return true;
        }
        return false;
    }

    public boolean asignarGestor(Item i, Gestor g) {
//        TODO: Uso de Excepciones
        if (this.itemsPendientes.contains(i) && this.gestores.contains(g)) {
            g.tomarItem(i);
            this.itemsPendientes.remove(i);
            return true;
        }
        return false;
    }

    public boolean finalizar(Item i) {
//        TODO: Uso de excepciones
        return false;
    }

    /* Getters & Setters */
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

}
