/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConexionDB.Conexion;

public class DAOVehiculo {
    private Conexion conexionDB = new Conexion();

    // Método para insertar un nuevo vehículo
    public void insertarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (placa, numero_serie, anho_fabricacion, color, cantidad_puertas, cilindrada, duenho) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getNumeroSerie());
            ps.setInt(3, vehiculo.getAnhoFabricacion());
            ps.setString(4, vehiculo.getColor());
            ps.setInt(5, vehiculo.getCantidadPuertas());
            ps.setDouble(6, vehiculo.getCilindrada());
            ps.setString(7, vehiculo.getDuenho());
            ps.executeUpdate();
            System.out.println("Vehículo insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar vehículo: " + e.getMessage());
        }
    }

    // Método para obtener todos los vehículos
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("codigo"),
                    rs.getString("placa"),
                    rs.getString("numero_serie"),
                    rs.getInt("anho_fabricacion"),
                    rs.getString("color"),
                    rs.getInt("cantidad_puertas"),
                    rs.getDouble("cilindrada"),
                    rs.getString("duenho")
                );
                listaVehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener vehículos: " + e.getMessage());
        }
        return listaVehiculos;
    }
    
    public Vehiculo obtenerVehiculoPorId(int codigo) {
        String sql = "SELECT * FROM vehiculos WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Vehiculo(
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
            System.out.println("Error al obtener el vehículo por código: " + e.getMessage());
        }
        return null; // Retorna null si no se encuentra el vehículo
    }

    // Método para actualizar un vehículo existente
    public void actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET placa = ?, numero_serie = ?, anho_fabricacion = ?, color = ?, cantidad_puertas = ?, cilindrada = ?, duenho = ? WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getNumeroSerie());
            ps.setInt(3, vehiculo.getAnhoFabricacion());
            ps.setString(4, vehiculo.getColor());
            ps.setInt(5, vehiculo.getCantidadPuertas());
            ps.setDouble(6, vehiculo.getCilindrada());
            ps.setString(7, vehiculo.getDuenho());
            ps.setInt(8, vehiculo.getCodigo());
            ps.executeUpdate();
            System.out.println("Vehículo actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    // Método para eliminar un vehículo
    public void eliminarVehiculo(int codigo) {
        String sql = "DELETE FROM vehiculos WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Vehículo eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar vehículo: " + e.getMessage());
        }
    }
    
    public boolean existeCodigo(int codigo) {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si el código existe
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el código: " + e.getMessage());
        }
        return false;
    }

    public int obtenerUltimoCodigo() {
        String sql = "SELECT MAX(codigo) FROM vehiculos";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Retorna el último código (máximo valor)
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el último código: " + e.getMessage());
        }
        return 0; // Retorna 0 si no hay registros
    }

    
    public List<Vehiculo> buscarVehiculosPorCriterio(String criterio) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql;

        if (criterio == null || criterio.isEmpty()) {
            // Si el criterio está vacío, selecciona todos los vehículos
            sql = "SELECT * FROM vehiculos";
        } else {
            // Si hay criterio, busca en placa con LIKE y en código de forma exacta
            sql = "SELECT * FROM vehiculos WHERE placa LIKE ? OR CAST(codigo AS TEXT) LIKE ?";
        }

        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            if (criterio == null || criterio.isEmpty()) {
                // No se requiere setear parámetros para una búsqueda completa
            } else {
                ps.setString(1, "%" + criterio + "%"); // Busca parcial en placa
                ps.setString(2, "%" + criterio + "%"); // Busca parcial en código convertido a texto
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("codigo"),
                        rs.getString("placa"),
                        rs.getString("numero_serie"),
                        rs.getInt("anho_fabricacion"),
                        rs.getString("color"),
                        rs.getInt("cantidad_puertas"),
                        rs.getDouble("cilindrada"),
                        rs.getString("duenho")
                    );
                    listaVehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar vehículos: " + e.getMessage());
        }
        return listaVehiculos;
    }


}
