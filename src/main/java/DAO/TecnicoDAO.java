package DAO;

import ConexionDB.Conexion;
import DTO.TecnicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD de Técnicos
 */
public class TecnicoDAO implements IGenericDAO<TecnicoDTO> {
    
    private Conexion conexion;
    
    public TecnicoDAO() {
        this.conexion = Conexion.getInstance();
    }
    
    @Override
    public List<TecnicoDTO> obtenerTodos() throws SQLException {
        List<TecnicoDTO> tecnicos = new ArrayList<>();
        String sql = "SELECT codigo, nombres, apellidos, direccion, sexo, correo, celular, " +
                     "especialidad, tiempo_servicio FROM tecnico ORDER BY codigo";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TecnicoDTO tecnico = new TecnicoDTO(
                    rs.getInt("codigo"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("correo"),
                    rs.getString("celular"),
                    rs.getString("especialidad"),
                    rs.getInt("tiempo_servicio")
                );
                tecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener todos los técnicos: " + e.getMessage());
            throw e;
        }
        
        return tecnicos;
    }
    
    @Override
    public TecnicoDTO obtenerPorId(int codigo) throws SQLException {
        String sql = "SELECT codigo, nombres, apellidos, direccion, sexo, correo, celular, " +
                     "especialidad, tiempo_servicio FROM tecnico WHERE codigo = ?";
        TecnicoDTO tecnico = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tecnico = new TecnicoDTO(
                        rs.getInt("codigo"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("correo"),
                        rs.getString("celular"),
                        rs.getString("especialidad"),
                        rs.getInt("tiempo_servicio")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener técnico por ID: " + e.getMessage());
            throw e;
        }
        
        return tecnico;
    }
    
    @Override
    public boolean crear(TecnicoDTO tecnico) throws SQLException {
        String sql = "INSERT INTO tecnico (nombres, apellidos, direccion, sexo, correo, celular, " +
                     "especialidad, tiempo_servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tecnico.getNombres());
            stmt.setString(2, tecnico.getApellidos());
            stmt.setString(3, tecnico.getDireccion());
            stmt.setString(4, String.valueOf(tecnico.getSexo()));
            stmt.setString(5, tecnico.getCorreo());
            stmt.setString(6, tecnico.getCelular());
            stmt.setString(7, tecnico.getEspecialidad());
            stmt.setInt(8, tecnico.getTiempoServicio());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al crear técnico: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean actualizar(TecnicoDTO tecnico) throws SQLException {
        String sql = "UPDATE tecnico SET nombres = ?, apellidos = ?, direccion = ?, sexo = ?, " +
                     "correo = ?, celular = ?, especialidad = ?, tiempo_servicio = ? WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tecnico.getNombres());
            stmt.setString(2, tecnico.getApellidos());
            stmt.setString(3, tecnico.getDireccion());
            stmt.setString(4, String.valueOf(tecnico.getSexo()));
            stmt.setString(5, tecnico.getCorreo());
            stmt.setString(6, tecnico.getCelular());
            stmt.setString(7, tecnico.getEspecialidad());
            stmt.setInt(8, tecnico.getTiempoServicio());
            stmt.setInt(9, tecnico.getCodigo());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al actualizar técnico: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM tecnico WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al eliminar técnico: " + e.getMessage());
            throw e;
        }
    }
}
