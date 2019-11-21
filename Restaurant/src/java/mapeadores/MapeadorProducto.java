package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Fachada;
import logica.Producto;
import persistencia.Mapeador;

public class MapeadorProducto implements Mapeador {

    private Producto p;

    @Override
    public int getOid() {
        return p.getOid();
    }

    @Override
    public void setOid(int oid) {
        p.setOid(oid);
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
        return "SELECT * FROM producto";
    }

    @Override
    public void crearNuevo() {
        p = new Producto();
    }

    @Override
    public Object getObjeto() {
        return p;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        p.setCodigo(rs.getInt("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getFloat("precio"));
        p.setStock(rs.getInt("stock"));
        p.setOid(rs.getInt("oid"));
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        p.setuProcesadora(Fachada.getInstancia().getUnidadProcesadoraByOid(rs.getInt("oidUnidadProcesadora")));
    }

}
