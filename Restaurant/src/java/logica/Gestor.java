package logica;

/**
 *
 * @author Ignacio
 */
public class Gestor extends Usuario {

    public Gestor(String nombreUsuario, String contrasena, String nombreCompleto) {
        super(nombreUsuario, contrasena, nombreCompleto);
    }

    @Override
    public Gestor login(String nombreUsuario, String contrasena) {
        if (this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

}
