package logica;

import java.util.ArrayList;
import utilidades.CustomException;

public class Gestor extends Usuario {

    //<editor-fold desc="Atributos">
    private UnidadProcesadora unidadProcesadora;
    private ArrayList<Item> itemsParaProcesar;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Gestor(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
        this.itemsParaProcesar = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    @Override
    public Gestor login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

    public void tomarItem(Item item) throws CustomException {
        if (!unidadProcesadora.getItemsPendientes().contains(item)) {
            throw new CustomException("El item no está ingresado!");
        }

        item.setGestor(this);
        itemsParaProcesar.add(item);
        unidadProcesadora.itemTomado(item);
    }

    public void finalizar(Item item) throws CustomException {

        if (!itemsParaProcesar.contains(item)) {
            throw new CustomException("El item no está ingresado!");
        }
        itemsParaProcesar.remove(item);
        unidadProcesadora.avisarFinalizado();
    }

    public void logout() throws CustomException {
        if (!itemsParaProcesar.isEmpty()) {
            throw new CustomException("El gestor tiene pedidos tomados!");
        }

        unidadProcesadora.removeGestor(this);

    }

    //</editor-fold>
    //<editor-fold desc="Getters & Setters">
    public UnidadProcesadora getUnidadProcesadora() {
        return unidadProcesadora;
    }

    public void setUnidadProcesadora(UnidadProcesadora unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }

    public ArrayList<Item> getItemsParaProcesar() {
        return itemsParaProcesar;
    }

    public void setItemsParaProcesar(ArrayList<Item> itemsParaProcesar) {
        this.itemsParaProcesar = itemsParaProcesar;
    }
    //</editor-fold>

}
