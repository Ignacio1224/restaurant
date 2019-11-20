package logica.Sistemas;

import java.util.ArrayList;
import logica.Cliente;
import mapeadores.MapeadorCliente;
import persistencia.Persistencia;
import utilidades.CustomException;

public class SistemaCliente {

    /* Atributos */
    private ArrayList<Cliente> clientes;

    /* Constructor */
    public SistemaCliente() {
        clientes = new ArrayList<>();
    }
    
    /* Comportamiento */
    public void agregarCliente(Cliente cliente) throws CustomException {
        if (clientes.contains(cliente)) {
            throw new CustomException("Producto ya registrado");
        }
        clientes.add(cliente);
    }
    
    public Cliente getClienteById(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
    public void cargar() throws CustomException {
        clientes = Persistencia.getInstancia().obtenerTodos(new MapeadorCliente());
    }
    
    /* Getters & Setters */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
}
