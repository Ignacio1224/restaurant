package logica;

import java.util.Objects;
import utilidades.CustomException;

/**
 *
 * @author Ignacio
 */
public abstract class Usuario {

    // Atributos
    protected String nombreUsuario;
    protected String contrasena;
    protected String nombreCompleto;

    // Constructor
    public Usuario(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        
        if (nombreUsuario.trim().isEmpty() || contrasena.trim().isEmpty() || nombreCompleto.trim().isEmpty()) {
            throw new CustomException("Usuario no válido");
        }
        
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    // Login
    public abstract Usuario login(String nombreUsuario, String contrasena);
    
    // Getters & Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // To String
    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Nombre Completo=" + nombreCompleto;
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        return true;
    }

}
