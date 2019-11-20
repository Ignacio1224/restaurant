package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Cliente;
import logica.beneficio.Comun;
import logica.beneficio.DeLaCasa;
import logica.beneficio.Preferencial;
import persistencia.Mapeador;

public class MapeadorCliente implements Mapeador {

    private Cliente cliente;

    @Override
    public int getOid() {
        return cliente.getOid();
    }

    @Override
    public void setOid(int oid) {
        cliente.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlActualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlBorrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlSeleccionar() {
        return "SELECT c.*, b.* FROM cliente c, beneficio b where c.oidBeneficio = b.oid;";
    }

    @Override
    public void crearNuevo() {
        cliente = new Cliente();
    }

    @Override
    public Object getObjeto() {
        return cliente;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setEmail(rs.getString("email"));

    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        String descripcion = rs.getString("descripcion");
        String className = descripcion.toLowerCase().replaceAll("\\s+", "");

        switch (className) {
            case "delacasa":
                cliente.setBeneficio(new DeLaCasa(descripcion));
                break;

            case "preferencial":
                cliente.setBeneficio(new Preferencial(descripcion));
                break;

            case "comun":
                cliente.setBeneficio(new Comun(descripcion));
                break;
        }
    }

}
