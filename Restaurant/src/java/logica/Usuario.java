package logica;

import java.util.Observable;
import utilidades.CustomException;

public abstract class Usuario extends Observable {

    // Atributos
    protected String nombreusuario;
    protected String contrasena;
    protected String nombreCompleto;
    private int oid;

    // Constructor
    public Usuario() {

    }

    public Usuario(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {

        if (nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty() || nombreCompleto.trim().isEmpty()) {
            throw new CustomException("Usuario no válido");
        }

        this.nombreusuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    // Login
    public abstract Usuario login(String nombreUsuario, String contrasena);

    // Getters & Setters
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

    // To String
    @Override
    public String toString() {
        return "Usuario: " + nombreusuario + ", Nombre completo: " + nombreCompleto;
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        Usuario other = (Usuario) obj;

        return nombreusuario.equals(other.getNombreUsuario());

    }

}
