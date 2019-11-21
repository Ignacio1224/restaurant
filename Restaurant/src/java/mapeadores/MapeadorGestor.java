package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Gestor;
import persistencia.Mapeador;

public class MapeadorGestor implements Mapeador {

    private Gestor gestor;

    public MapeadorGestor() {
    }

    public MapeadorGestor(Gestor g) {
        gestor = g;
    }

    public void setUsuario(Gestor g) {
        gestor = g;
    }

    @Override
    public int getOid() {
        return gestor.getOid();
    }

    @Override
    public void setOid(int oid) {
        gestor.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> getSqlActualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> getSqlBorrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSqlSeleccionar() {
        return "select * from usuario where tipo='gestor';";
    }

    @Override
    public void crearNuevo() {
        gestor = new Gestor();
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        gestor.setNombreUsuario(rs.getString("usuario"));
        gestor.setNombreCompleto(rs.getString("nombreCompleto"));
        gestor.setContrasena(rs.getString("clave"));
        gestor.setOid(rs.getInt("oid"));
    }

    @Override
    public Object getObjeto() {
        return gestor;
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
    }

}
