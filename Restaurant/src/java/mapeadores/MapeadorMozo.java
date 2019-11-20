package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Mesa;
import logica.Mozo;
import persistencia.Mapeador;

public class MapeadorMozo implements Mapeador {

    private Mozo mozo;

    public MapeadorMozo() {
    }

    public MapeadorMozo(Mozo m) {
        mozo = m;
    }

    public void setUsuario(Mozo m) {
        mozo = m;
    }

    @Override
    public int getOid() {
        return mozo.getOid();
    }

    @Override
    public void setOid(int oid) {
        mozo.setOid(oid);
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
        return "select u.*, m.* from usuario u, mesa m where m.oidMozo=u.oid and u.tipo='mozo';";
    }

    @Override
    public void crearNuevo() {
        mozo = new Mozo();
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        mozo.setNombreUsuario(rs.getString("usuario"));
        mozo.setNombreCompleto(rs.getString("nombreCompleto"));
        mozo.setContrasena(rs.getString("clave"));
    }

    @Override
    public Object getObjeto() {
        return mozo;
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        mozo.getMesasAsignadas().add(new Mesa(rs.getInt("nroMesa"), mozo));
    }

}
