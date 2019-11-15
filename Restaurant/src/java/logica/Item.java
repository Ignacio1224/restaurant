package logica;

public class Item {

    /* Atributos */
    private Servicio servicio;
    private int cantidad;
    private String descripcion;
    private Producto producto;
    

    /* Constructor */
    public Item(Servicio servicio, int cantidad, String descripcion, Producto producto) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.producto = producto;
    }

    /* Comportamientos */
    public void avisarParaProcesar() {

    }
    
    public float getMonto() {
        return producto.getPrecio() * cantidad;
    }

    /* Getters & Setters */
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

    @Override
    public boolean equals(Object obj) {
        Item other = (Item) obj;
        return servicio.equals(other.getServicio()) && producto.equals(other.getProducto());
    }

}
