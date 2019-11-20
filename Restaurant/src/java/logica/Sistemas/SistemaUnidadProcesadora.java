package logica.Sistemas;

import java.util.ArrayList;
import logica.UnidadProcesadora;
import mapeadores.MapeadorUnidadProcesadora;
import persistencia.Persistencia;
import utilidades.CustomException;

public class SistemaUnidadProcesadora {

    private ArrayList<UnidadProcesadora> unidadesProcesadoras;

    public SistemaUnidadProcesadora() {
        unidadesProcesadoras = new ArrayList();
    }

    public ArrayList<UnidadProcesadora> getUnidadesProcesadoras() {
        return this.unidadesProcesadoras;
    }

    public void setUnidadesProcesadoras(ArrayList<UnidadProcesadora> uProcesadoras) {
        this.unidadesProcesadoras = uProcesadoras;
    }

    public UnidadProcesadora getUnidadProcesadoraByName(String name) {
        for (UnidadProcesadora u : unidadesProcesadoras) {
            if (u.getNombre().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public void cargar() throws CustomException {
        unidadesProcesadoras = Persistencia.getInstancia().obtenerTodos(new MapeadorUnidadProcesadora());
    }

    public UnidadProcesadora getUnidadProcesadoraByOid(int aInt) {
        for (UnidadProcesadora u : unidadesProcesadoras) {
            if (u.getOid() == aInt) {
                return u;
            }
        }
        return null;
    }
}
