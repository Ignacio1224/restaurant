package controlador;

import logica.Fachada;
import logica.Gestor;
import logica.Mozo;

/**
 *
 * @author Ignacio Cabrera
 */
public class ControladorLogin {

    private VistaLogin vista;

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
    }

    public void loginMozo(String userS, String passwordS) {
        Mozo user = Fachada.getInstancia().loginMozo(userS, passwordS);

        if (user == null) {
            vista.accesoDenegadoMozo("Usuario y/o contraseña incorrectos");
        } else {
            vista.accesoPermitidoMozo(user);
        }
    }

    public void logoutMozo(Mozo user) {
        Fachada.getInstancia().logoutMozo(user);
    }

    public void loginGestor(String userS, String passwordS) {
        Gestor user = Fachada.getInstancia().loginGestor(userS, passwordS);
        if (user == null) {
            vista.accesoDenegadoGestor("Usuario y/o contraseña incorrectos");
        } else {
            vista.accesoPermitidoGestor(user);
        }
    }

    public void logoutGestor(Gestor gestor) {
        Fachada.getInstancia().logoutGestor(gestor);
    }

}
