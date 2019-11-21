package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Cliente;
import logica.Item;
import logica.Servicio;
import persistencia.Mapeador;
import persistencia.Persistencia;

public class MapeadorServicio implements Mapeador {

    private Servicio servicio;

    public MapeadorServicio(Servicio s) {
        servicio = s;
    }

    @Override
    public int getOid() {
        return servicio.getOid();
    }

    @Override
    public void setOid(int oid) {
        servicio.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        ArrayList<String> sqls = new ArrayList();
        Cliente c = servicio.getCliente();
        sqls.add("insert into servicio (oidMesa, oidCliente, oidMozo, costo, oid) values("
                + servicio.getMesaCorrespondiente().getOid() + ","
                + ((c == null) ? "NULL" : c.getOid()) + ","
                + servicio.getMesaCorrespondiente().getResponsable().getOid() + ","
                + servicio.calcularTotal() + ","
                + Persistencia.getInstancia().proximoOid()
                + ");");

        generarItems(sqls);
        return sqls;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearNuevo() {
        servicio = new Servicio();
    }

    @Override
    public Object getObjeto() {
        return servicio;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void generarItems(ArrayList<String> sqls) {
        ArrayList<Item> items = servicio.getItems();
        for (Item item : items) {
            String desc = item.getDescripcion();
            sqls.add(
                    "insert into item (oid, oidServicio, oidProducto, descripcion, cantidad, oidGestor) values("
                    + Persistencia.getInstancia().proximoOid() + ","
                    + servicio.getOid() + ","
                    + item.getProducto().getOid() + ","
                    + ((desc.isEmpty()) ? "NULL" : "'" + desc + "'") + ","
                    + item.getCantidad() + ","
                    + item.getGestor().getOid()
                    + ");"
            );
        }

    }

}
