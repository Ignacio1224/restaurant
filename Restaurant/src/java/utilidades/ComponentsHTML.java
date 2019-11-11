package utilidades;

import logica.Item;
import logica.Mesa;
import logica.Producto;

public class ComponentsHTML {

    public static String armarMesa(Mesa mesa) {
        return ""
                + "<div class=\"col-xs-6 col-sm-6 col-md-4 col-lg-3 col-xl-3 offset-3 offset-md-0 \">\n"
                + "    <figure class=\"figure border rounded-right p-3 bg-light\">\n"
                + "        <button class=\"btn btn-mesa\" onclick=\"cargarModalMesa(" + mesa.getNumero() + ", " + mesa.isAbierta() + ")\">\n"
                + "            <img src=\"img/mesa_Icon.png\" class=\"figure-img img-fluid rounded p-2 " + (mesa.isAbierta() ? "bg-danger" : "bg-success") + "\" alt=\"Icono mesa.\"/>\n"
                + "        </button>\n"
                + "        <figcaption class=\"figure-caption text-left\">\n"
                + "            <p class=\"mt-2 font-weight-bold\">Mesa Nº " + mesa.getNumero() + "</p>\n"
                + "            <button class=\"btn btn-link collapsed\" type=\"button\" data-toggle=\"collapse\" data-target=\"#servicioMesa" + mesa.getNumero() + "\" aria-expanded=\"false\" aria-controls=\"servicioMesa" + mesa.getNumero() + "\">\n"
                + "                Descripción del servicio\n"
                + "            </button>"
                + "            <div class=\"accordion\" id=\"accordionServicioMesa" + mesa.getNumero() + "\">"
                + "                <div id=\"servicioMesa" + mesa.getNumero() + "\" class=\"collapse\" aria-labelledby=\"headingTwo\" data-parent=\"#accordionExample\">\n"
                + "                    <div class=\"card-body\" id=\"divServicioMesa\">\n"
                + "                        No hay servicio .\n"
                + "                    </div>\n"
                + "                </div>"
                + "            </div>"
                + "        </figcaption>\n"
                + "    </figure>\n"
                + "</div>";
    }

    public static String armarProductos(Producto producto) {
        return "<option value=\"" + producto.getCodigo() + "\">" + producto.getNombre() + "</option>";
    }

    public static String armarServicio(Mesa mesa) {
        String servicios = "<div class=\"accordion\" id=\"accordionServicio" + mesa.getNumero() + "\">\n";

        int i = 1000;
        for (Item item : mesa.getServicio().getItems()) {
            servicios += "  <div class=\"card\">\n";
            servicios += "    <div class=\"card-header\">\n";
            servicios += "      <h2 class=\"mb-0\">\n";
            servicios += "        <button class=\"btn btn-link\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapse" + i + "\" aria-expanded=\"true\" aria-controls=\"collapse" + i + "\">\n";
            servicios += "          Prodcto: " + item.getProducto().getNombre() + "\n";
            servicios += "        </button>\n";
            servicios += "      </h2>\n";
            servicios += "    </div>\n";
            servicios += "    <div id=\"collapse" + i + "\" class=\"collapse show\" aria-labelledby=\"heading" + i + "\" data-parent=\"#accordionServicio\">\n";
            servicios += "      <div class=\"card-body\">\n";
            servicios += "          <p>"
                    + "                 Precio: " + item.getProducto().getPrecio()
                    + "                 Cantidad: " + item.getCantidad()
                    + "                 Descripción: " + item.getDescripcion()
                    + "             </p>.\n";
            servicios += "      </div>\n";
            servicios += "    </div>\n";
            servicios += "  </div>\n";
            i++;
        }
        
        servicios += "</div>";

        return servicios;
    }

}
