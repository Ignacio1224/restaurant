package logica;

import java.util.Observable;
import utilidades.CustomException;

public abstract class Usuario extends Observable {

    //<editor-fold desc="Atributos">
    protected String nombreusuario;
    protected String contrasena;
    protected String nombreCompleto;
    private int oid;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Usuario() {

    }
    //</editor-fold>

    //<editor-fold desc="Comportamientos">
    public abstract Usuario login(String nombreUsuario, String contrasena);

    public abstract void logout() throws CustomException;
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreusuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreusuario = nombreUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    //</editor-fold>

    //<editor-fold desc="To String">
    @Override
    public String toString() {
        return "Usuario: " + nombreusuario + ", Nombre completo: " + nombreCompleto;
    }

    //<editor-fold desc="Equals">
    @Override
    public boolean equals(Object obj) {
        Usuario other = (Usuario) obj;

        return nombreusuario.equals(other.getNombreUsuario());

    }
    //</editor-fold>

}
