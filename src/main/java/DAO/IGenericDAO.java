package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD
 * Define los métodos que todo DAO debe implementar
 */
public interface IGenericDAO<T> {
    
    /**
     * Obtiene todos los registros
     */
    List<T> obtenerTodos() throws SQLException;
    
    /**
     * Obtiene un registro por ID
     */
    T obtenerPorId(int id) throws SQLException;
    
    /**
     * Crea un nuevo registro
     */
    boolean crear(T objeto) throws SQLException;
    
    /**
     * Actualiza un registro existente
     */
    boolean actualizar(T objeto) throws SQLException;
    
    /**
     * Elimina un registro por ID
     */
    boolean eliminar(int id) throws SQLException;
}
