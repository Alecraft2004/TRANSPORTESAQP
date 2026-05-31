
package Controladores;

import DAO.TecnicoDAO;
import DTO.TecnicoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar operaciones de técnicos
 */
public class ControladorTecnico {
    private TecnicoDAO tecnicoDAO;

    public ControladorTecnico() {
        this.tecnicoDAO = new TecnicoDAO();
    }

    /**
     * Obtiene todos los técnicos
     */
    public List<TecnicoDTO> obtenerTodosLosTecnicos() throws SQLException {
        return tecnicoDAO.obtenerTodos();
    }

    /**
     * Obtiene un técnico por código
     */
    public TecnicoDTO obtenerTecnicoPorId(int codigo) throws SQLException {
        return tecnicoDAO.obtenerPorId(codigo);
    }

    /**
     * Crea un nuevo técnico
     */
    public boolean agregarTecnico(TecnicoDTO tecnico) throws SQLException {
        return tecnicoDAO.crear(tecnico);
    }

    /**
     * Actualiza un técnico existente
     */
    public boolean actualizarTecnico(TecnicoDTO tecnico) throws SQLException {
        return tecnicoDAO.actualizar(tecnico);
    }

    /**
     * Elimina un técnico por código
     */
    public boolean eliminarTecnico(int codigo) throws SQLException {
        return tecnicoDAO.eliminar(codigo);
    }
}
