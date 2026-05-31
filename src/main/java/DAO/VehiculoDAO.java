package DAO;

import ConexionDB.Conexion;
import DTO.VehiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD de Vehículos
 */
public class VehiculoDAO implements IGenericDAO<VehiculoDTO> {
    
    private Conexion conexion;
    
    public VehiculoDAO() {
        this.conexion = Conexion.getInstance();
    }
    
    @Override
    public List<VehiculoDTO> obtenerTodos() throws SQLException {
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        String sql = "SELECT codigo, placa, numero_serie, anho_fabricacion, color, " +
                     "cantidad_puertas, cilindrada, duenho FROM vehiculo ORDER BY codigo";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                VehiculoDTO vehiculo = new VehiculoDTO(
                    rs.getInt("codigo"),
                    rs.getString("placa"),
                    rs.getString("numero_serie"),
                    rs.getInt("anho_fabricacion"),
                    rs.getString("color"),
                    rs.getInt("cantidad_puertas"),
                    rs.getDouble("cilindrada"),
                    rs.getString("duenho")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener todos los vehículos: " + e.getMessage());
            throw e;
        }
        
        return vehiculos;
    }
    
    @Override
    public VehiculoDTO obtenerPorId(int codigo) throws SQLException {
        String sql = "SELECT codigo, placa, numero_serie, anho_fabricacion, color, " +
                     "cantidad_puertas, cilindrada, duenho FROM vehiculo WHERE codigo = ?";
        VehiculoDTO vehiculo = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new VehiculoDTO(
                        rs.getInt("codigo"),
                        rs.getString("placa"),
                        rs.getString("numero_serie"),
                        rs.getInt("anho_fabricacion"),
                        rs.getString("color"),
                        rs.getInt("cantidad_puertas"),
                        rs.getDouble("cilindrada"),
                        rs.getString("duenho")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener vehículo por ID: " + e.getMessage());
            throw e;
        }
        
        return vehiculo;
    }
    
    @Override
    public boolean crear(VehiculoDTO vehiculo) throws SQLException {
        String sql = "INSERT INTO vehiculo (placa, numero_serie, anho_fabricacion, color, " +
                     "cantidad_puertas, cilindrada, duenho) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getNumeroSerie());
            stmt.setInt(3, vehiculo.getAnhoFabricacion());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getCantidadPuertas());
            stmt.setDouble(6, vehiculo.getCilindrada());
            stmt.setString(7, vehiculo.getDuenho());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al crear vehículo: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean actualizar(VehiculoDTO vehiculo) throws SQLException {
        String sql = "UPDATE vehiculo SET placa = ?, numero_serie = ?, anho_fabricacion = ?, " +
                     "color = ?, cantidad_puertas = ?, cilindrada = ?, duenho = ? WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getNumeroSerie());
            stmt.setInt(3, vehiculo.getAnhoFabricacion());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getCantidadPuertas());
            stmt.setDouble(6, vehiculo.getCilindrada());
            stmt.setString(7, vehiculo.getDuenho());
            stmt.setInt(8, vehiculo.getCodigo());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al actualizar vehículo: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM vehiculo WHERE codigo = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al eliminar vehículo: " + e.getMessage());
            throw e;
        }
    }
}
