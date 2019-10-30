package logica;

import utilidades.CustomException;

/**
 *
 * @author Ignacio
 */
public class Gestor extends Usuario {

    public Gestor(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
    }

    @Override
    public Gestor login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

}
