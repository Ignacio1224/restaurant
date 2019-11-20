package datosPrueba;

import logica.Fachada;
import persistencia.BaseDatos;
import utilidades.CustomException;

public class DatosPrueba {

    public static void cargarDatos() {
        
        Fachada fachada = Fachada.getInstancia();
        
        String url = "jdbc:mysql://localhost/obldda";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectar("com.mysql.jdbc.Driver", url, "root", "root");
        
        try {
            fachada.cargarUsuarios();
            fachada.cargarUnidadesProcesadoras();
            fachada.cargarProductos();
            fachada.cargarClientes();
        } catch (CustomException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
