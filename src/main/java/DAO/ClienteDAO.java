package DAO;

import ConexionDB.Conexion;
import DTO.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD de Clientes
 */
public class ClienteDAO implements IGenericDAO<ClienteDTO> {
    
    private Conexion conexion;
    
    public ClienteDAO() {
        this.conexion = Conexion.getInstance();
    }
    
    @Override
    public List<ClienteDTO> obtenerTodos() throws SQLException {
        List<ClienteDTO> clientes = new ArrayList<>();
        String sql = "SELECT codigo, nombres, apellidos, direccion, sexo, correo, celular " +
                     "FROM cliente ORDER BY codigo";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO(
                    rs.getInt("codigo"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("correo"),
                    rs.getString("celular")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener todos los clientes: " + e.getMessage());
            throw e;
        }
        
        return clientes;
    }
    
    @Override
    public ClienteDTO obtenerPorId(int codigo) throws SQLException {
        String sql = "SELECT codigo, nombres, apellidos, direccion, sexo, correo, celular " +
                     "FROM cliente WHERE codigo = ?";
        ClienteDTO cliente = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteDTO(
                        rs.getInt("codigo"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("correo"),
                        rs.getString("celular")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener cliente por ID: " + e.getMessage());
            throw e;
        }
        
        return cliente;
    }
    
    @Override
    public boolean crear(ClienteDTO cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nombres, apellidos, direccion, sexo, correo, celular) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNombres());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, String.valueOf(cliente.getSexo()));
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getCelular());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al crear cliente: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean actualizar(ClienteDTO cliente) throws SQLException {
        String sql = "UPDATE cliente SET nombres = ?, apellidos = ?, direccion = ?, " +
                     "sexo = ?, correo = ?, celular = ? WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNombres());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, String.valueOf(cliente.getSexo()));
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getCelular());
            stmt.setInt(7, cliente.getCodigo());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al actualizar cliente: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM cliente WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al eliminar cliente: " + e.getMessage());
            throw e;
        }
    }
}
