package logica.Sistemas;

import java.util.ArrayList;
import logica.UnidadProcesadora;

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
}
