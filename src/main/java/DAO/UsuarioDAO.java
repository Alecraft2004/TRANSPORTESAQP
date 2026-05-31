package DAO;

import ConexionDB.Conexion;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD de Usuarios
 * Utiliza JDBC para interactuar con la base de datos
 */
public class UsuarioDAO implements IGenericDAO<UsuarioDTO> {
    
    private Conexion conexion;
    
    public UsuarioDAO() {
        this.conexion = Conexion.getInstance();
    }
    
    @Override
    public List<UsuarioDTO> obtenerTodos() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        String sql = "SELECT id, usuario, contrasena FROM usuarios ORDER BY id";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener todos los usuarios: " + e.getMessage());
            throw e;
        }
        
        return usuarios;
    }
    
    @Override
    public UsuarioDTO obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, usuario, contrasena FROM usuarios WHERE id = ?";
        UsuarioDTO usuario = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener usuario por ID: " + e.getMessage());
            throw e;
        }
        
        return usuario;
    }
    
    /**
     * Autentica un usuario por nombre y contraseña
     */
    public UsuarioDTO autenticar(String usuario, String contrasena) throws SQLException {
        String sql = "SELECT id, usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?";
        UsuarioDTO usuarioDTO = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioDTO = new UsuarioDTO(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al autenticar usuario: " + e.getMessage());
            throw e;
        }
        
        return usuarioDTO;
    }
    
    @Override
    public boolean crear(UsuarioDTO usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getContrasena());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al crear usuario: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean actualizar(UsuarioDTO usuario) throws SQLException {
        String sql = "UPDATE usuarios SET usuario = ?, contrasena = ? WHERE id = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getContrasena());
            stmt.setInt(3, usuario.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al actualizar usuario: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al eliminar usuario: " + e.getMessage());
            throw e;
        }
    }
}
