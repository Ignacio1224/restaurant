package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Mapeador {

    public int getOid();
    
    public void setOid(int oid);

    public ArrayList<String> getSqlInsertar();

    public ArrayList<String> getSqlActualizar();

    public ArrayList<String> getSqlBorrar();

    public String getSqlSeleccionar();

    public void crearNuevo();

    public Object getObjeto();

    public void leerCompuesto(ResultSet rs) throws SQLException;

    public void leerComponente(ResultSet rs) throws SQLException;

    
    
    
}
