package datosPrueba;

import java.util.ArrayList;
import logica.Mozo;
import logica.SistemaUsuario;

/**
 *
 * @author Ignacio
 */
public class DatosPrueba {

    public static ArrayList<Mozo> cargarMozos() {
        ArrayList<Mozo> mozos = new ArrayList<>();
        
        Mozo mozoNacho = new Mozo("mozoNacho", "1234", "Ignacio Cabrera");
        mozos.add(mozoNacho);
        
        Mozo mozoSantiago = new Mozo("mozoSantiago", "1234", "Santiago Manzoni");
        mozos.add(mozoSantiago);
        
        return mozos;
    }
}
