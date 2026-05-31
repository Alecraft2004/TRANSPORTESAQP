
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConexionDB.Conexion;

public class DAOCliente {
    private Conexion conexionDB = new Conexion();

    // Método para insertar un nuevo cliente
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombres, apellidos, direccion, sexo, correo, celular) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, String.valueOf(cliente.getSexo()));
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getCelular());
            ps.executeUpdate();
            System.out.println("Cliente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("codigo"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("correo"),
                    rs.getString("celular")
                );
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return listaClientes;
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, apellidos = ?, direccion = ?, sexo = ?, correo = ?, celular = ? WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, String.valueOf(cliente.getSexo()));
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getCelular());
            ps.setInt(7, cliente.getCodigo());
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }
    
    

    public void eliminarCliente(int codigo) {
        String sql = "DELETE FROM clientes WHERE codigo = ?";
        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }


       // Método para buscar clientes por criterio de nombres, apellidos o código
    public List<Cliente> buscarClientesPorCriterio(String criterio) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombres LIKE ? OR apellidos LIKE ? OR correo LIKE ? OR celular LIKE ?";

        try (Connection conexion = conexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            // Preparar el criterio de búsqueda para buscar en todos los campos
            String criterioBusqueda = "%" + criterio + "%";
            ps.setString(1, criterioBusqueda);
            ps.setString(2, criterioBusqueda);
            ps.setString(3, criterioBusqueda);
            ps.setString(4, criterioBusqueda);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getInt("codigo"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("correo"),
                        rs.getString("celular")
                    );
                    listaClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar clientes: " + e.getMessage());
        }
        return listaClientes;
    }
}
