package datosPrueba;

import java.util.ArrayList;
import logica.Fachada;
import logica.Gestor;
import logica.Mozo;
import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class DatosPrueba {

    public static void cargarMozos() {

        try {
            ArrayList<Mozo> mozos = new ArrayList<>();

            Mozo mozoNacho = new Mozo("mozoNacho", "1234", "Ignacio Cabrera");
            mozos.add(mozoNacho);

            Mozo mozoSantiago = new Mozo("mozoSantiago", "1234", "Santiago Manzoni");
            mozos.add(mozoSantiago);

            Fachada.getInstancia().setMozosTodos(mozos);

        } catch (CustomException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void cargarGestores() {

        try {
            ArrayList<Gestor> gestores = new ArrayList<>();

            Gestor gestorNacho = new Gestor("gestorNacho", "1234", "Ignacio Cabrera");
            gestores.add(gestorNacho);

            Gestor gestorSantiago = new Gestor("gestorSantiago", "1234", "Santiago Manzoni");
            gestores.add(gestorSantiago);

            Fachada.getInstancia().setGestoresTodos(gestores);

        } catch (CustomException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}