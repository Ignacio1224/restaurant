package utilidades;

import logica.Item;

public class Utilities {
    public static String getEstadoItem(Item i) {
        if (i.getEstado().equals(Item.Estados.Finalizado)) {
            return "Finalizado";
        }
        if (i.getEstado().equals(Item.Estados.Procesando)) {
            return "Procesando";
        }
        return "Pendiente";
    }
}
