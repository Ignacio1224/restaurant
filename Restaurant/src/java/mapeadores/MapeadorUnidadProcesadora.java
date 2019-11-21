package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.UnidadProcesadora;
import persistencia.Mapeador;

public class MapeadorUnidadProcesadora implements Mapeador {

    private UnidadProcesadora unidadProcesadora;

    public MapeadorUnidadProcesadora() {
    }

    public MapeadorUnidadProcesadora(UnidadProcesadora unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }

    @Override
    public int getOid() {
        return unidadProcesadora.getOid();
    }

    @Override
    public void setOid(int oid) {
        unidadProcesadora.setOid(oid);
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
        
        return "SELECT * FROM unidadprocesadora;";
    }

    @Override
    public void crearNuevo() {
        unidadProcesadora = new UnidadProcesadora();
    }

    @Override
    public Object getObjeto() {
        return unidadProcesadora;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        unidadProcesadora.setNombre(rs.getString("nombre"));
        unidadProcesadora.setOid(rs.getInt("oid"));
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {

    }

}
