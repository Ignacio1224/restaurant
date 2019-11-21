package logica;

public class Item {

    //<editor-fold desc="Atributos">
    private Servicio servicio;
    private int cantidad;
    private String descripcion;
    private Producto producto;
    private Gestor gestor;

    public enum Estados {
        Pendiente,
        Procesando,
        Finalizado
    }

    private int oid;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Item(Servicio servicio, int cantidad, String descripcion, Producto producto) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.producto = producto;
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public void avisarParaProcesar() {
        producto.getuProcesadora().agregarItem(this);
    }

    public float getMonto() {
        return producto.getPrecio() * cantidad;
    }

    public Estados getEstado() {
        if (gestor == null) {
            return Estados.Pendiente;
        }

        if (gestor.getItemsParaProcesar().contains(this)) {
            return Estados.Procesando;
        }

        return Estados.Finalizado;
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public Servicio getServicio() {
        return servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProducto() {
        return producto;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
    //</editor-fold>

}
