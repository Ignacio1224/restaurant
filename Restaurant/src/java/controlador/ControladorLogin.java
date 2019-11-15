package controlador;

import logica.Fachada;
import logica.Gestor;
import logica.Mozo;
import utilidades.CustomException;

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
        try {
            Fachada.getInstancia().logoutMozo(user);
            vista.desloguear();

        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
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
        try {
            
            Fachada.getInstancia().logoutGestor(gestor);
            vista.desloguear();
        } catch (CustomException ex) {
            vista.notificarError(ex.getMessage());
        }
    }

}
