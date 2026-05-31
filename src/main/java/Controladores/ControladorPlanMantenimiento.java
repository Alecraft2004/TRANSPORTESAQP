
package Controladores;

import DAO.PlanMantenimientoDAO;
import DAO.VehiculoDAO;
import DTO.PlanMantenimientoDTO;
import DTO.VehiculoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar operaciones de planes de mantenimiento
 */
public class ControladorPlanMantenimiento {
    private PlanMantenimientoDAO planDAO;
    private VehiculoDAO vehiculoDAO;

    public ControladorPlanMantenimiento() {
        this.planDAO = new PlanMantenimientoDAO();
        this.vehiculoDAO = new VehiculoDAO();
    }

    /**
     * Obtiene todos los planes de mantenimiento
     */
    public List<PlanMantenimientoDTO> obtenerTodosLosPlanes() throws SQLException {
        return planDAO.obtenerTodos();
    }

    /**
     * Obtiene un plan por ID
     */
    public PlanMantenimientoDTO obtenerPlanPorId(int id) throws SQLException {
        return planDAO.obtenerPorId(id);
    }

    /**
     * Crea un nuevo plan de mantenimiento
     */
    public boolean agregarPlan(PlanMantenimientoDTO plan) throws SQLException {
        return planDAO.crear(plan);
    }

    /**
     * Actualiza un plan de mantenimiento existente
     */
    public boolean actualizarPlan(PlanMantenimientoDTO plan) throws SQLException {
        return planDAO.actualizar(plan);
    }

    /**
     * Elimina un plan de mantenimiento por su ID
     */
    public boolean eliminarPlan(int id) throws SQLException {
        return planDAO.eliminar(id);
    }

    /**
     * Obtiene un vehículo por su ID
     */
    public VehiculoDTO obtenerVehiculo(int idVehiculo) throws SQLException {
        return vehiculoDAO.obtenerPorId(idVehiculo);
    }
}
