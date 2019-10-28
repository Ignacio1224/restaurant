package logica;

import datosPrueba.DatosPrueba;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class Fachada {

    // Atrubutos
    private Fachada instancia;
    private SistemaUsuario sistemaUsuario;

    // Constructor
    private Fachada() {
        sistemaUsuario = new SistemaUsuario();
        setMozosTodos(DatosPrueba.cargarMozos());
    }

    // Singleton
    public Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    // Delegaciones
    public Mozo loginMozo(String nombreUsuario, String contrasena) {
        return sistemaUsuario.loginMozo(nombreUsuario, contrasena);
    }

    public boolean logoutMozo(Mozo m) {
        return sistemaUsuario.logoutMozo(m);
    }

    public ArrayList<Mozo> getMozosTodos() {
        return sistemaUsuario.getMozosTodos();
    }

    public ArrayList<Mozo> getMozosLogueados() {
        return sistemaUsuario.getMozosLogueados();
    }

    public ArrayList<Gestor> getGestoresTodos() {
        return sistemaUsuario.getGestoresTodos();
    }

    public ArrayList<Gestor> getGestoresLogueados() {
        return sistemaUsuario.getGestoresLogueados();
    }

    public void setMozosTodos(ArrayList<Mozo> mozosTodos) {
        sistemaUsuario.setMozosTodos(mozosTodos);
    }

}
