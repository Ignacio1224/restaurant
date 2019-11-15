package logica;

import java.util.ArrayList;
import utilidades.CustomException;

public class Gestor extends Usuario {

    //<editor-fold desc="Atributos">
    private UnidadProcesadora uProcesadora;
    private ArrayList<Item> itemsParaProcesar;

    public enum Eventos {
        itemTomado,
        itemFinalizado
    };
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Gestor(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
        this.itemsParaProcesar = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    private void avisar(Eventos evento) {
        setChanged();
        notifyObservers(evento);
    }

    @Override
    public Gestor login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

    public void tomarItem(Item item) throws CustomException {
        if (!uProcesadora.getItemsPendientes().contains(item)) {
            throw new CustomException("El item no está ingresado!");
        }

        item.setGestor(this);
        itemsParaProcesar.add(item);
        uProcesadora.itemTomado(item);
        avisar(Eventos.itemTomado);
    }

    public void finalizar(Item item) throws CustomException {

        if (!itemsParaProcesar.contains(item)) {
            throw new CustomException("El item no está ingresado!");
        }

        itemsParaProcesar.remove(item);
        avisar(Eventos.itemFinalizado);

    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public UnidadProcesadora getuProcesadora() {
        return uProcesadora;
    }

    public void setuProcesadora(UnidadProcesadora uProcesadora) {
        this.uProcesadora = uProcesadora;
    }

    public ArrayList<Item> getItemsParaProcesar() {
        return itemsParaProcesar;
    }

    public void setItemsParaProcesar(ArrayList<Item> itemsParaProcesar) {
        this.itemsParaProcesar = itemsParaProcesar;
    }
    //</editor-fold>

}
