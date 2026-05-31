
package Controladores;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar operaciones de usuarios
 * Actúa como intermediario entre la capa de presentación y la capa DAO
 */
public class ControladorUsuario {
    private UsuarioDAO usuarioDAO;

    public ControladorUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Autentica un usuario con sus credenciales
     */
    public UsuarioDTO iniciarSesion(String usuario, String contrasena) throws SQLException {
        return usuarioDAO.autenticar(usuario, contrasena);
    }

    /**
     * Obtiene todos los usuarios
     */
    public List<UsuarioDTO> obtenerTodosLosUsuarios() throws SQLException {
        return usuarioDAO.obtenerTodos();
    }

    /**
     * Obtiene un usuario por ID
     */
    public UsuarioDTO obtenerUsuarioPorId(int id) throws SQLException {
        return usuarioDAO.obtenerPorId(id);
    }

    /**
     * Crea un nuevo usuario
     */
    public boolean crearUsuario(UsuarioDTO usuario) throws SQLException {
        return usuarioDAO.crear(usuario);
    }

    /**
     * Actualiza un usuario existente
     */
    public boolean actualizarUsuario(UsuarioDTO usuario) throws SQLException {
        return usuarioDAO.actualizar(usuario);
    }

    /**
     * Elimina un usuario por ID
     */
    public boolean eliminarUsuario(int id) throws SQLException {
        return usuarioDAO.eliminar(id);
    }
}
