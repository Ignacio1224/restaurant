package logica;

import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class SistemaUsuario {

    // Atributos
    private ArrayList<Mozo> mozosTodos;
    private ArrayList<Mozo> mozosLogueados;
    private ArrayList<Gestor> gestoresTodos;
    private ArrayList<Gestor> gestoresLogueados;

    public Mozo loginMozo(String nombreUsuario, String contrasena) {
        for (Mozo m : mozosTodos) {
            Mozo mozo = m.login(nombreUsuario, contrasena);
            if (mozo != null) {
                mozosLogueados.add(mozo);
                return mozo;
            }
        }
        return null;
    }

    public boolean logoutMozo(Mozo m) {
        try {
            mozosLogueados.remove(m);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Getters & Setters
    public ArrayList<Mozo> getMozosTodos() {
        return mozosTodos;
    }

    public void setMozosTodos(ArrayList<Mozo> mozosTodos) {
        this.mozosTodos = mozosTodos;
    }

    public ArrayList<Mozo> getMozosLogueados() {
        return mozosLogueados;
    }

    public ArrayList<Gestor> getGestoresTodos() {
        return gestoresTodos;
    }

    public ArrayList<Gestor> getGestoresLogueados() {
        return gestoresLogueados;
    }

}
