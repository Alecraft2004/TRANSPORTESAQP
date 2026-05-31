
package Modelo;

import ConexionDB.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPlanMantenimiento {
    private Conexion conexionDB = new Conexion();

    // Método para insertar un nuevo plan de mantenimiento
    public void insertarPlan(PlanMantenimiento plan) {
        String sql = "INSERT INTO planes_mantenimiento (id_tecnico, id_vehiculo, tipo_mantenimiento, kilometraje, falla, fecha, servicio_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, plan.getIdTecnico());
            ps.setInt(2, plan.getIdVehiculo());
            ps.setString(3, plan.getTipoMantenimiento());
            ps.setInt(4, plan.getKilometraje());
            ps.setString(5, plan.getFalla());
            ps.setDate(6, Date.valueOf(plan.getFecha())); // Conversion de String a Date
            ps.setDouble(7, plan.getServicioPago());
            ps.executeUpdate();
            System.out.println("Plan de mantenimiento insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar plan de mantenimiento: " + e.getMessage());
        }
    }

    // Método para obtener todos los planes de mantenimiento
    public List<PlanMantenimiento> obtenerPlanes() {
        List<PlanMantenimiento> planes = new ArrayList<>();
        String sql = "SELECT * FROM planes_mantenimiento";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PlanMantenimiento plan = new PlanMantenimiento(
                    rs.getInt("codigo"),
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
            System.out.println("Error al obtener planes de mantenimiento: " + e.getMessage());
        }
        return planes;
    }

    // Método para actualizar un plan de mantenimiento existente
    public void actualizarPlan(PlanMantenimiento plan) {
        String sql = "UPDATE planes_mantenimiento SET id_tecnico = ?, id_vehiculo = ?, tipo_mantenimiento = ?, kilometraje = ?, falla = ?, fecha = ?, servicio_pago = ? WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, plan.getIdTecnico());
            ps.setInt(2, plan.getIdVehiculo());
            ps.setString(3, plan.getTipoMantenimiento());
            ps.setInt(4, plan.getKilometraje());
            ps.setString(5, plan.getFalla());
            ps.setDate(6, Date.valueOf(plan.getFecha()));
            ps.setDouble(7, plan.getServicioPago());
            ps.setInt(8, plan.getCodigo());
            ps.executeUpdate();
            System.out.println("Plan de mantenimiento actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar plan de mantenimiento: " + e.getMessage());
        }
    }

    // Método para eliminar un plan de mantenimiento por código
    public void eliminarPlan(int codigo) {
        String sql = "DELETE FROM planes_mantenimiento WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Plan de mantenimiento eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar plan de mantenimiento: " + e.getMessage());
        }
    }

    // Método adicional para buscar planes por criterio específico
    public List<PlanMantenimiento> buscarPlanesPorCriterio(String criterio) {
        List<PlanMantenimiento> planes = new ArrayList<>();
        String sql = "SELECT * FROM planes_mantenimiento WHERE " +
                     "CAST(codigo AS TEXT) LIKE ? OR " +
                     "tipo_mantenimiento LIKE ? OR " +
                     "falla LIKE ? OR " +
                     "CAST(servicio_pago AS TEXT) LIKE ?";

        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            String busqueda = "%" + criterio + "%";
            ps.setString(1, busqueda);  // Búsqueda en código (convertido a texto)
            ps.setString(2, busqueda);  // Búsqueda en tipo_mantenimiento
            ps.setString(3, busqueda);  // Búsqueda en falla
            ps.setString(4, busqueda);  // Búsqueda en servicio_pago (convertido a texto)

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PlanMantenimiento plan = new PlanMantenimiento(
                        rs.getInt("codigo"),
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
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar planes de mantenimiento: " + e.getMessage());
        }
        return planes;
    }
}
