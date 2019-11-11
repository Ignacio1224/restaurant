package utilidades;

import logica.Mesa;

public class ComponentsHTML {

    public static String ArmarMesaHTML(Mesa mesa) {
        String mesaString = ""
                + "<div class=\"col-lg-3\">\n"
                + "    <figure class=\"figure border rounded-right p-3 bg-light\">\n"
                + "        <button class=\"btn btn-mesa\" onclick=\"cargarModalMesa(" + mesa.getNumero() + ", " + mesa.isAbierta() + ")\">\n"
                + "            <img src=\"img/mesa_Icon.png\" class=\"figure-img img-fluid rounded p-2 " + (mesa.isAbierta() ? "bg-danger" : "bg-success") + "\" alt=\"Icono mesa.\"/>\n"
                + "        </button>\n"
                + "        <figcaption class=\"figure-caption text-right\">\n"
                + "            <p class=\"mt-2 font-weight-bold\">Mesa Nº " + mesa.getNumero() + "</p>\n"
                + "        </figcaption>\n"
                + "    </figure>\n"
                + "</div>";

        return mesaString;
    }

}
