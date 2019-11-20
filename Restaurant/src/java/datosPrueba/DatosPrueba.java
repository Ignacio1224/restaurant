package datosPrueba;

import java.util.ArrayList;
import logica.Cliente;
import logica.Fachada;
import logica.Producto;
import logica.UnidadProcesadora;
import logica.beneficio.Beneficio;
import logica.beneficio.Comun;
import logica.beneficio.DeLaCasa;
import logica.beneficio.Preferencial;
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
        } catch (CustomException ex) {
            System.out.println(ex.getMessage());
        }

    }

//    public static void cargarGestores() {
//
//        try {
//            ArrayList<Gestor> gestores = new ArrayList<>();
//
//            Gestor gestorNacho = new Gestor("gestorNacho", "1234", "Ignacio Cabrera");
//            gestores.add(gestorNacho);
//
//            Gestor gestorSantiago = new Gestor("gestorSantiago", "1234", "Santiago Manzoni");
//            gestores.add(gestorSantiago);
//
//            Fachada.getInstancia().setGestoresTodos(gestores);
//
//        } catch (CustomException ex) {
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }
//
//    public static void cargarMesas() {
//        ArrayList<Mozo> mozos = Fachada.getInstancia().getMozosTodos();
//
//        try {
//
//            int i = 0;
//            for (Mozo m : mozos) {
//                for (int j = 0; j < 8; j++) {
//                    m.agregarMesaAsignada(new Mesa(++i, false, m));
//                }
//            }
//
//        } catch (CustomException ex) {
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }

//    public static void cargarUnidadesProcesadoras(ArrayList<UnidadProcesadora> uProcesadoras) {
//        Fachada.getInstancia().setUnidadesProcesadoras(uProcesadoras);
//    }

    public static void cargarProductos() {
        ArrayList<UnidadProcesadora> uProcesadoras = new ArrayList();
        ArrayList<Producto> productos = new ArrayList<>();

        UnidadProcesadora bar = new UnidadProcesadora("bar");
        UnidadProcesadora postres = new UnidadProcesadora("postres");
        UnidadProcesadora bebidas = new UnidadProcesadora("bebidas");

        uProcesadoras.add(bar);
        uProcesadoras.add(postres);
        uProcesadoras.add(bebidas);

        productos.add(new Producto(1, "Pan", 46, 20f, bar));
        productos.add(new Producto(2, "Papas fritas", 46, 20f, bar));
        productos.add(new Producto(3, "Milanesa", 46, 20f, bar));
        productos.add(new Producto(4, "Pure", 46, 20f, bar));
        productos.add(new Producto(5, "Omelette", 46, 20f, bar));
        productos.add(new Producto(6, "Helado", 46, 20f, postres));
        productos.add(new Producto(7, "Vino", 46, 20f, bebidas));
        productos.add(new Producto(8, "Agua", 46, 20f, bebidas));
        productos.add(new Producto(9, "Cafe", 46, 20f, bar));

        Fachada.getInstancia().setProductos(productos);
//        cargarUnidadesProcesadoras(uProcesadoras);
    }

    public static void cargarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        Beneficio deLaCasa = new DeLaCasa("DeLaCasa");
        Beneficio preferencial = new Preferencial("Preferencial");
        Beneficio comun = new Comun("Comun");

        clientes.add(new Cliente(1, "c1@gmail.con", "Cliente de la casa"));
        clientes.add(new Cliente(2, "c2@gmail.con", "Cliente preferencial"));
        clientes.add(new Cliente(3, "c3@gmail.con", "Cliente comun"));

        clientes.get(0).setBeneficio(deLaCasa);
        clientes.get(1).setBeneficio(preferencial);
        clientes.get(2).setBeneficio(comun);

        Fachada.getInstancia().setClientes(clientes);

    }

}
