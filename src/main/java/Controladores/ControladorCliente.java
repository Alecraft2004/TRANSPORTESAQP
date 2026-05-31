package Controladores;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar operaciones de clientes
 */
public class ControladorCliente {
    private ClienteDAO clienteDAO;

    public ControladorCliente() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Obtiene todos los clientes
     */
    public List<ClienteDTO> obtenerTodosLosClientes() throws SQLException {
        return clienteDAO.obtenerTodos();
    }

    /**
     * Obtiene un cliente por código
     */
    public ClienteDTO obtenerClientePorId(int codigo) throws SQLException {
        return clienteDAO.obtenerPorId(codigo);
    }

    /**
     * Crea un nuevo cliente
     */
    public boolean agregarCliente(ClienteDTO cliente) throws SQLException {
        return clienteDAO.crear(cliente);
    }

    /**
     * Actualiza un cliente existente
     */
    public boolean actualizarCliente(ClienteDTO cliente) throws SQLException {
        return clienteDAO.actualizar(cliente);
    }

    /**
     * Elimina un cliente por código
     */
    public boolean eliminarCliente(int codigo) throws SQLException {
        return clienteDAO.eliminar(codigo);
    }
}
