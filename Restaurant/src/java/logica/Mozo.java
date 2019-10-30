package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio
 */
public class Mozo extends Usuario {

    public Mozo(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
    }

    @Override
    public Mozo login(String nombreUsuario, String contrasena) {
        if (this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

}
