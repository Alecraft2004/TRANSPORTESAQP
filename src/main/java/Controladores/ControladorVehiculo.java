
package Controladores;

import DAO.VehiculoDAO;
import DTO.VehiculoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar operaciones de vehículos
 */
public class ControladorVehiculo {
    private VehiculoDAO vehiculoDAO;

    public ControladorVehiculo() {
        this.vehiculoDAO = new VehiculoDAO();
    }

    /**
     * Obtiene todos los vehículos
     */
    public List<VehiculoDTO> obtenerTodosLosVehiculos() throws SQLException {
        return vehiculoDAO.obtenerTodos();
    }

    /**
     * Obtiene un vehículo por código
     */
    public VehiculoDTO obtenerVehiculoPorId(int codigo) throws SQLException {
        return vehiculoDAO.obtenerPorId(codigo);
    }

    /**
     * Crea un nuevo vehículo
     */
    public boolean agregarVehiculo(VehiculoDTO vehiculo) throws SQLException {
        return vehiculoDAO.crear(vehiculo);
    }

    /**
     * Actualiza un vehículo existente
     */
    public boolean actualizarVehiculo(VehiculoDTO vehiculo) throws SQLException {
        return vehiculoDAO.actualizar(vehiculo);
    }

    /**
     * Elimina un vehículo por código
     */
    public boolean eliminarVehiculo(int codigo) throws SQLException {
        return vehiculoDAO.eliminar(codigo);
    }
}
