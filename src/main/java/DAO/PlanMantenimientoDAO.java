package DAO;

import ConexionDB.Conexion;
import DTO.PlanMantenimientoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD de Planes de Mantenimiento
 */
public class PlanMantenimientoDAO implements IGenericDAO<PlanMantenimientoDTO> {
    
    private Conexion conexion;
    
    public PlanMantenimientoDAO() {
        this.conexion = Conexion.getInstance();
    }
    
    @Override
    public List<PlanMantenimientoDTO> obtenerTodos() throws SQLException {
        List<PlanMantenimientoDTO> planes = new ArrayList<>();
        String sql = "SELECT id, id_tecnico, id_vehiculo, tipo_mantenimiento, kilometraje, " +
                     "falla, fecha, servicio_pago FROM plan_mantenimiento ORDER BY id";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PlanMantenimientoDTO plan = new PlanMantenimientoDTO(
                    rs.getInt("id"),
                    rs.getInt("id_tecnico"),
                    rs.getInt("id_vehiculo"),
                    rs.getString("tipo_mantenimiento"),
                    rs.getInt("kilometraje"),
                    rs.getString("falla"),
                    rs.getString("fecha"),
                    rs.getDouble("servicio_pago")
                );
                planes.add(plan);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener todos los planes: " + e.getMessage());
            throw e;
        }
        
        return planes;
    }
    
    @Override
    public PlanMantenimientoDTO obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, id_tecnico, id_vehiculo, tipo_mantenimiento, kilometraje, " +
                     "falla, fecha, servicio_pago FROM plan_mantenimiento WHERE id = ?";
        PlanMantenimientoDTO plan = null;
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    plan = new PlanMantenimientoDTO(
                        rs.getInt("id"),
                        rs.getInt("id_tecnico"),
                        rs.getInt("id_vehiculo"),
                        rs.getString("tipo_mantenimiento"),
                        rs.getInt("kilometraje"),
                        rs.getString("falla"),
                        rs.getString("fecha"),
                        rs.getDouble("servicio_pago")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al obtener plan por ID: " + e.getMessage());
            throw e;
        }
        
        return plan;
    }
    
    @Override
    public boolean crear(PlanMantenimientoDTO plan) throws SQLException {
        String sql = "INSERT INTO plan_mantenimiento (id_tecnico, id_vehiculo, tipo_mantenimiento, " +
                     "kilometraje, falla, fecha, servicio_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, plan.getIdTecnico());
            stmt.setInt(2, plan.getIdVehiculo());
            stmt.setString(3, plan.getTipoMantenimiento());
            stmt.setInt(4, plan.getKilometraje());
            stmt.setString(5, plan.getFalla());
            stmt.setString(6, plan.getFecha());
            stmt.setDouble(7, plan.getServicioPago());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al crear plan: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean actualizar(PlanMantenimientoDTO plan) throws SQLException {
        String sql = "UPDATE plan_mantenimiento SET id_tecnico = ?, id_vehiculo = ?, " +
                     "tipo_mantenimiento = ?, kilometraje = ?, falla = ?, fecha = ?, " +
                     "servicio_pago = ? WHERE id = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, plan.getIdTecnico());
            stmt.setInt(2, plan.getIdVehiculo());
            stmt.setString(3, plan.getTipoMantenimiento());
            stmt.setInt(4, plan.getKilometraje());
            stmt.setString(5, plan.getFalla());
            stmt.setString(6, plan.getFecha());
            stmt.setDouble(7, plan.getServicioPago());
            stmt.setInt(8, plan.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al actualizar plan: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM plan_mantenimiento WHERE id = ?";
        
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            conexion.commit();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            conexion.rollback();
            System.err.println("[ERROR] Error al eliminar plan: " + e.getMessage());
            throw e;
        }
    }
}
