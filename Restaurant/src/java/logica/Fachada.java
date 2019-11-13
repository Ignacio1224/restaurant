package logica;

import java.util.ArrayList;
import utilidades.CustomException;

/**
 *
 * @author Ignacio
 */
public class Fachada {

    // Atrubutos
    private static Fachada instancia;
    private SistemaUsuario sistemaUsuario;
    private SistemaProducto sistemaProdutcto;
    private SistemaCliente sistemaCliente;

    // Constructor
    private Fachada() {
        sistemaUsuario = new SistemaUsuario();
        sistemaProdutcto = new SistemaProducto();
        sistemaCliente = new SistemaCliente();
    }

    // Singleton
    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    // Delegaciones
    public Mozo loginMozo(String nombreUsuario, String contrasena) {
        return sistemaUsuario.loginMozo(nombreUsuario, contrasena);
    }

    public boolean logoutMozo(Mozo m) {
        return sistemaUsuario.logoutMozo(m);
    }

    public Gestor loginGestor(String nombreUsuario, String contrasena) {
        return sistemaUsuario.loginGestor(nombreUsuario, contrasena);
    }

    public boolean logoutGestor(Gestor g) {
        return sistemaUsuario.logoutGestor(g);
    }

    public ArrayList<Mozo> getMozosTodos() {
        return sistemaUsuario.getMozosTodos();
    }

    public ArrayList<Mozo> getMozosLogueados() {
        return sistemaUsuario.getMozosLogueados();
    }

    public ArrayList<Gestor> getGestoresTodos() {
        return sistemaUsuario.getGestoresTodos();
    }

    public ArrayList<Gestor> getGestoresLogueados() {
        return sistemaUsuario.getGestoresLogueados();
    }

    public void setMozosTodos(ArrayList<Mozo> mozosTodos) {
        sistemaUsuario.setMozosTodos(mozosTodos);
    }

    public void setGestoresTodos(ArrayList<Gestor> gestoresTodos) {
        sistemaUsuario.setGestoresTodos(gestoresTodos);
    }

    public void agregarProducto(Producto producto) throws CustomException {
        sistemaProdutcto.agregarProducto(producto);
    }

    public ArrayList<Producto> getProductos() {
        return sistemaProdutcto.getProductos();
    }

    public ArrayList<Producto> getProductosConStock() {
        return sistemaProdutcto.getProductosConStock();
    }

    public void setProductos(ArrayList<Producto> productos) {
        sistemaProdutcto.setProductos(productos);
    }

    public Producto getProductoByCodigo(String code) {
        return sistemaProdutcto.getProductoByCodigo(code);
    }

    public void agregarCliente(Cliente cliente) throws CustomException {
        sistemaCliente.agregarCliente(cliente);
    }

    public Cliente getClienteById(int id) {
        return sistemaCliente.getClienteById(id);
    }

    public ArrayList<Cliente> getClientes() {
        return sistemaCliente.getClientes();
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        sistemaCliente.setClientes(clientes);
    }

}
