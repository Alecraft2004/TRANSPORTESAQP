package DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) para usuarios
 * Utilizado para transferir datos entre capas sin dependencias de la lógica de negocio
 */
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String usuario;
    private String contrasena;

    // Constructor vacío
    public UsuarioDTO() {}

    // Constructor con parámetros
    public UsuarioDTO(int id, String usuario, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
