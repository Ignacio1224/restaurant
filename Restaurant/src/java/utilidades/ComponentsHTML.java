package utilidades;

import java.util.ArrayList;
import logica.Item;
import logica.Mesa;
import logica.Mozo;
import logica.Producto;
import logica.Servicio;
import logica.Transferencia;
import logica.UnidadProcesadora;

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
            s += "     <button class=\"btn btn-link\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseMesa" + mesa.getNumero() + "\" aria-expanded=\"false\" aria-controls=\"collapseMesa" + mesa.getNumero() + "\">\n"
                    + "     Total gastado: $" + mesa.getServicio().calcularTotal() + "\n"
                    + "</button>"
                    + "<div class=\"collapse\" id=\"collapseMesa" + mesa.getNumero() + "\">\n"
                    + "     <div class=\"card card-body\">\n"
                    + armarServicio(mesa.getServicio())
                    + "     </div>\n"
                    + "</div>";
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

        if (servicio.getItems().isEmpty()) {
            return "<p>Sin items.</p>";
        }

        String serviciosStr = "";
        for (Item i : servicio.getItems()) {
            serviciosStr += "<div class=\"card card-body mb-2\">"
                    + "<p>Producto: " + i.getProducto().getNombre() + "</p>"
                    + "<p>Cantidad: " + i.getCantidad() + "</p>"
                    + "<p>Precio unitario: " + i.getProducto().getPrecio() + "</p>"
                    + "<p>Precio total: " + i.getMonto() + "</p>"
                    + "<p>Descripción: " + i.getDescripcion() + "</p>"
                    + "<p>Estado: " + Utilities.getEstadoItem(i) + "</p>"
                    + "</div>";
        }
        return serviciosStr;
    }

    public static String armarCuenta(Servicio servicio) {

        if (servicio.getCliente() == null) {
            return "<p>Monto total: " + servicio.calcularTotal() + "</p>";
        }

        String s = ""
                + "<p>Cliente: " + servicio.getCliente().getNombre() + "</p>"
                + "<p>Monto total: " + servicio.calcularTotal() + "</p>"
                + "<p>Beneficio: " + servicio.getCliente().getBeneficio().getDescripcion() + "</p>"
                //                TODO: Revisar logica de calcular monto con beneficio
                + "<p>Monto a pagar: " + servicio.getCliente().calcularMonto() + "</p>"
                + "";

        return s;
    }

    public static String armarMozo(Mozo mozo) {
        return "<option value=\"" + mozo.getNombreUsuario() + "\">" + mozo.getNombreCompleto() + "</option>";
    }

    public static String armarTransferencia(Transferencia t) {
        return ""
                + "<div class=\"card card-body\">"
                + "    <p>Mesa Nº: " + t.getMesa().getNumero() + "</p>"
                + "    <p>Estado: " + (t.getMesa().isAbierta() ? "abierta" : "cerrada") + "</p>"
                + "    <p>Mozo emisor: " + t.getMesa().getResponsable().getNombreCompleto() + "</p>"
                + "</div>";
    }

    public static String armarResultadoTransferencia(boolean resultado) {
        if (resultado) {
            return "<div class=\"alert alert-success\" role=\"alert\">Transferencia aceptada</div>";
        }
        return "<div class=\"alert alert-danger\" role=\"alert\">Transferencia rechazada</div>";
    }

    public static String armarUnidadesProcesadoras(ArrayList<UnidadProcesadora> unidadesProcesadoras) {
        String s = "";
        for (UnidadProcesadora u : unidadesProcesadoras) {
            s += "<option value=\"" + u.getNombre() + "\">" + u.getNombre() + "</option>";
        }
        return s;
    }

    public static String armarPedidosPendientes(ArrayList<Item> itemsPendientes) {
        String s = "";

        for (int i = 0; i < itemsPendientes.size(); i++) {
            s += "<tr>\n"
                    + "<td>" + itemsPendientes.get(i).getProducto().getNombre() + "</td>\n"
                    + "<td>" + itemsPendientes.get(i).getCantidad() + "</td>\n"
                    + "<td>" + itemsPendientes.get(i).getDescripcion() + "</td>\n"
                    + "<td>" + itemsPendientes.get(i).getServicio().getMesaCorrespondiente().getNumero() + "</td>\n"
                    + "<td>" + itemsPendientes.get(i).getServicio().getMesaCorrespondiente().getResponsable().getNombreCompleto() + "</td>\n"
                    + "<td> <input type=\"button\" onclick=\"tomarPedido(" + i + ");\" value=\"Tomar pedido\" class=\"btn btn-primary\" > </td>\n"
                    + "</tr>";
        }
        return s;
    }

    public static String armarPedidosTomados(ArrayList<Item> itemsParaProcesar) {
        String s = "";

        for (int i = 0; i < itemsParaProcesar.size(); i++) {
            s += "<tr>\n"
                    + "<td>" + itemsParaProcesar.get(i).getProducto().getNombre() + "</td>\n"
                    + "<td>" + itemsParaProcesar.get(i).getCantidad() + "</td>\n"
                    + "<td>" + itemsParaProcesar.get(i).getDescripcion() + "</td>\n"
                    + "<td>" + itemsParaProcesar.get(i).getServicio().getMesaCorrespondiente().getNumero() + "</td>\n"
                    + "<td>" + itemsParaProcesar.get(i).getServicio().getMesaCorrespondiente().getResponsable().getNombreCompleto() + "</td>\n"
                    + "<td> <input type=\"button\" onclick=\"finalizarPedido(" + i + ");\" value=\"Finalizar pedido\" class=\"btn btn-success\"> </td>\n"
                    + "</tr>";
        }
        return s;
    }

    public static String armarItem(Item i) {
        return ""
                + "<p>Producto: " + i.getProducto().getNombre() + "</p>"
                + "<p>Cantidad: " + i.getCantidad()+ "</p>"
                + "<p>Mesa: " + i.getServicio().getMesaCorrespondiente().getNumero()+ "</p>";
    }
}
