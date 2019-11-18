package logica.Sistemas;

import java.util.ArrayList;
import logica.Gestor;
import logica.Mesa;
import logica.Mozo;
import utilidades.CustomException;

public class SistemaUsuario {

    // Atributos
    private ArrayList<Mozo> mozosTodos;
    private ArrayList<Mozo> mozosLogueados;
    private ArrayList<Gestor> gestoresTodos;
    private ArrayList<Gestor> gestoresLogueados;

    public SistemaUsuario() {
        mozosTodos = new ArrayList<>();
        mozosLogueados = new ArrayList<>();
        gestoresTodos = new ArrayList<>();
        gestoresLogueados = new ArrayList<>();

    }

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

    public void logoutMozo(Mozo m) throws CustomException {
        if (m == null) {
            throw new CustomException("No hay mozo!");
        }

        if (!m.getMesasAbiertas().isEmpty()) {
            throw new CustomException("Tienes mesas abiertas!");
        }

        if (!mozosLogueados.contains(m)) {
            throw new CustomException("No está logueado!");
        }

        mozosLogueados.remove(m);

    }

    public Gestor loginGestor(String nombreUsuario, String contrasena) {
        for (Gestor g : gestoresTodos) {
            Gestor gestor = g.login(nombreUsuario, contrasena);
            if (gestor != null) {
                gestoresLogueados.add(gestor);
                return gestor;
            }
        }
        return null;
    }

    public void logoutGestor(Gestor g) throws CustomException {
        if (g == null) {
            throw new CustomException("No hay gestor!");
        }

        if (!gestoresLogueados.contains(g)) {
            throw new CustomException("No está logueado!");
        }
        
        if (!g.getItemsParaProcesar().isEmpty()) {
            throw new CustomException("Tienes items procesando!");
        }
        
        g.logout();
        
        gestoresLogueados.remove(g);

    }

    public Mozo getMozoByUsername(String username) {
        for (Mozo m : mozosTodos) {
            if (m.getNombreUsuario().equals(username)) {
                return m;
            }
        }
        return null;
    }

    public Mesa getMesaByNumero(int numero) {
        for (Mozo m : mozosTodos) {
            Mesa me = m.getMesaByNumero(numero);
            if (me != null) {
                return me;
            }
        }
        return null;
    }

    // Getters & Setters
    public void setMozosTodos(ArrayList<Mozo> mozosTodos) {
        this.mozosTodos = mozosTodos;
    }

    public ArrayList<Mozo> getMozosTodos() {
        return mozosTodos;
    }

    public ArrayList<Mozo> getMozosLogueados() {
        return mozosLogueados;
    }

    public void setGestoresTodos(ArrayList<Gestor> gestoresTodos) {
        this.gestoresTodos = gestoresTodos;
    }

    public ArrayList<Gestor> getGestoresTodos() {
        return gestoresTodos;
    }

    public ArrayList<Gestor> getGestoresLogueados() {
        return gestoresLogueados;
    }

}
