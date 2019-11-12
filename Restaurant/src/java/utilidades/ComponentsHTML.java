package utilidades;

import logica.Item;
import logica.Mesa;
import logica.Producto;
import logica.Servicio;

public class ComponentsHTML {

    public static String armarMesa(Mesa mesa) {
        String s = ""
                + "<div class=\"col-3 offset-1 \">\n"
                + "    <figure class=\"figure border rounded-right p-3 bg-light\">\n"
                + "        <button class=\"btn btn-mesa\" onclick=\"cargarModalMesa(" + mesa.getNumero() + ", " + mesa.isAbierta() + ")\">\n"
                + "            <img src=\"img/mesa_Icon.png\" class=\"figure-img img-fluid rounded p-2 " + (mesa.isAbierta() ? "bg-danger" : "bg-success") + "\" alt=\"Icono mesa.\"/>\n"
                + "        </button>\n"
                + "        <figcaption class=\"figure-caption text-left\">\n"
                + "            <p class=\"mt-2 font-weight-bold\">Mesa Nº " + mesa.getNumero() + "</p>\n";

        if (mesa.isAbierta()) {
            s += "            <div id=\"servicioMesa" + mesa.getNumero() + "\">";
            s += armarServicio(mesa.getServicio());
            s += "            </div>";
        }

        s += ""
                + "        </figcaption>\n"
                + "    </figure>\n"
                + "</div>";

        return s;
    }

    public static String armarProductos(Producto producto) {
        return "<option value=\"" + producto.getCodigo() + "\">" + producto.getNombre() + "</option>";
    }

    public static String armarServicio(Servicio servicio) {
        String serviciosStr = "";
        for (Item i : servicio.getItems()) {
            serviciosStr += "<div class=\"card card-body mb-2\">"
                    + "<p>Producto: " + i.getProducto().getNombre() + "</p>"
                    + "<p>Cantidad: " + i.getCantidad() + "</p>"
                    + "<p>Precio: " + i.getProducto().getPrecio() + "</p>"
                    + "<p>Descripción: " + i.getDescripcion()+ "</p>"
                    + "</div>";
        }
        return serviciosStr;
    }

}
