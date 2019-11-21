package mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Item;
import logica.Servicio;
import persistencia.Mapeador;
import persistencia.Persistencia;

public class MapeadorServicio implements Mapeador {

    private Servicio servicio;

    public MapeadorServicio() {

    }

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
        sqls.add("insert into servicio values(" + servicio.getMesaCorrespondiente().getOid() + "," + servicio.getCliente().getOid() 
                        +","+ servicio.getMesaCorrespondiente().getResponsable().getOid()+","+ servicio.calcularTotal()+""
                        + ","+Persistencia.getInstancia().proximoOid()+");");
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            sqls.add(
                    "insert into itemServicio values(" + Persistencia.getInstancia().proximoOid() + ","
                    + item.getServicio().getMesaCorrespondiente().getOid() + "," + item.getServicio().getOid() + ","
                    + item.getProducto().getOid() + ",'" + item.getDescripcion() + "'," + item.getCantidad() + ")"
            );
        }

    }

}
