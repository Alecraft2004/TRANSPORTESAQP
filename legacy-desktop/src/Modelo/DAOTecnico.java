
package Modelo;

import ConexionDB.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTecnico {
    private Conexion conexionDB = new Conexion();

    // Método para insertar un nuevo técnico
    public void insertarTecnico(Tecnico tecnico) {
        String sql = "INSERT INTO tecnicos (nombres, apellidos, direccion, sexo, correo, celular, especialidad, tiempo_servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tecnico.getNombres());
            ps.setString(2, tecnico.getApellidos());
            ps.setString(3, tecnico.getDireccion());
            ps.setString(4, String.valueOf(tecnico.getSexo()));
            ps.setString(5, tecnico.getCorreo());
            ps.setString(6, tecnico.getCelular());
            ps.setString(7, tecnico.getEspecialidad());
            ps.setInt(8, tecnico.getTiempoServicio());
            ps.executeUpdate();
            System.out.println("Técnico insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar técnico: " + e.getMessage());
        }
    }

    // Método para obtener todos los técnicos
    public List<Tecnico> obtenerTecnicos() {
        List<Tecnico> listaTecnicos = new ArrayList<>();
        String sql = "SELECT * FROM tecnicos";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Tecnico tecnico = new Tecnico(
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
                listaTecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener técnicos: " + e.getMessage());
        }
        return listaTecnicos;
    }

    // Método para actualizar un técnico existente
    public void actualizarTecnico(Tecnico tecnico) {
        String sql = "UPDATE tecnicos SET nombres = ?, apellidos = ?, direccion = ?, sexo = ?, correo = ?, celular = ?, especialidad = ?, tiempo_servicio = ? WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tecnico.getNombres());
            ps.setString(2, tecnico.getApellidos());
            ps.setString(3, tecnico.getDireccion());
            ps.setString(4, String.valueOf(tecnico.getSexo()));
            ps.setString(5, tecnico.getCorreo());
            ps.setString(6, tecnico.getCelular());
            ps.setString(7, tecnico.getEspecialidad());
            ps.setInt(8, tecnico.getTiempoServicio());
            ps.setInt(9, tecnico.getCodigo());
            ps.executeUpdate();
            System.out.println("Técnico actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar técnico: " + e.getMessage());
        }
    }

    // Método para eliminar un técnico
    public void eliminarTecnico(int codigo) {
        String sql = "DELETE FROM tecnicos WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Técnico eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar técnico: " + e.getMessage());
        }
    }
    
    public Tecnico obtenerTecnicoPorId(int idTecnico) {
        String sql = "SELECT * FROM tecnicos WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTecnico);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Tecnico(
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
            System.out.println("Error al obtener el técnico por ID: " + e.getMessage());
        }
        return null;
    }



    // Método para buscar técnicos por criterio
   public List<Tecnico> buscarTecnicosPorCriterio(String criterio) {
        List<Tecnico> listaTecnicos = new ArrayList<>();
        String sql = "SELECT * FROM tecnicos WHERE " +
                     "nombres LIKE ? OR " +
                     "apellidos LIKE ? OR " +
                     "correo LIKE ? OR " +
                     "celular LIKE ? OR " +
                     "direccion LIKE ? OR " +
                     "especialidad LIKE ?";

        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            String criterioBusqueda = "%" + criterio + "%";
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, criterioBusqueda);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tecnico tecnico = new Tecnico(
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
                    listaTecnicos.add(tecnico);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar técnicos: " + e.getMessage());
        }
        return listaTecnicos;
    }
}