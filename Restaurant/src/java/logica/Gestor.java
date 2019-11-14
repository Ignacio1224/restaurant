package logica;

import java.util.ArrayList;
import utilidades.CustomException;

/**
 *
 * @author Ignacio
 */
public class Gestor extends Usuario {

    private UnidadProcesadora uProcesadora;
    private ArrayList<Item> itemsParaProcesar;

    public Gestor(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
        this.itemsParaProcesar = new ArrayList();
    }

    @Override
    public Gestor login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

    public void setUnidadProcesadora(UnidadProcesadora up) {
        this.uProcesadora = up;
    }

    public void tomarItem(Item i) {
        this.itemsParaProcesar.add(i);
//        TODO: sacar el item de la lista (itemsPendientes) de la uProcesadora
    }
}
