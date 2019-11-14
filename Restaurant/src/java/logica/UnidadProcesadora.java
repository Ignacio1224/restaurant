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
        if (!itemsPendientes.contains(i)) {
            itemsPendientes.add(i);
            return true;
        }
        return false;
    }

    public boolean addGestor(Gestor g) {
        if (!this.gestores.contains(g)) {
            this.gestores.add(g);
            return true;
        }
        return false;
    }

    public boolean removeGestor(Gestor g) {
        if (this.gestores.contains(g)) {
            this.gestores.remove(g);
            return true;
        }
        return false;
    }

    public boolean asignarGestor(Item i, Gestor g) {
        if (this.itemsPendientes.contains(i) && this.gestores.contains(g)) {
            g.tomarItem(i);
            this.itemsPendientes.remove(i);
            return true;
        }
        return false;
    }

    public boolean finalizar(Item i) {
        return false;
    }
}
