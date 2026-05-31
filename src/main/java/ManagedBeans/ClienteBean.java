package ManagedBeans;

import Controladores.ControladorCliente;
import DTO.ClienteDTO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed Bean para gestionar clientes con JSF.
 * Actua como capa de presentacion: recibe datos de la vista, valida y delega
 * la logica de negocio/persistencia al Controlador. No contiene SQL.
 */
@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ClienteDTO cliente = new ClienteDTO();
    private List<ClienteDTO> clientes = new ArrayList<>();
    private final ControladorCliente controlador;

    public ClienteBean() {
        this.controlador = new ControladorCliente();
        cargarClientes();
    }

    /**
     * Carga la lista de todos los clientes desde la base de datos.
     */
    public final void cargarClientes() {
        try {
            clientes = controlador.obtenerTodosLosClientes();
        } catch (Exception e) {
            // Se captura Exception (no solo SQLException) para que la vista
            // siga renderizando aunque la base de datos no este disponible.
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se pudieron cargar los clientes: " + e.getMessage());
        }
    }

    /**
     * Prepara el formulario para registrar un cliente nuevo.
     */
    public String nuevoCliente() {
        this.cliente = new ClienteDTO();
        return "formulario-cliente.xhtml?faces-redirect=true";
    }

    /**
     * Carga un cliente existente en el formulario para editarlo.
     */
    public String editarCliente(ClienteDTO seleccionado) {
        try {
            // Se trae una copia fresca desde la BD para no editar la fila de la tabla.
            this.cliente = controlador.obtenerClientePorId(seleccionado.getCodigo());
            if (this.cliente == null) {
                this.cliente = new ClienteDTO();
            }
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se pudo cargar el cliente: " + e.getMessage());
            return null;
        }
        return "formulario-cliente.xhtml?faces-redirect=true";
    }

    /**
     * Guarda un nuevo cliente o actualiza uno existente.
     * Valida los datos antes de enviarlos al controlador (requisito de la rubrica).
     */
    public String guardarCliente() {
        if (!datosValidos()) {
            return null; // Se queda en el formulario mostrando los errores.
        }
        try {
            boolean ok;
            String exito;
            if (cliente.getCodigo() == 0) {
                ok = controlador.agregarCliente(cliente);
                exito = "Cliente creado correctamente";
            } else {
                ok = controlador.actualizarCliente(cliente);
                exito = "Cliente actualizado correctamente";
            }
            if (ok) {
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Exito", exito);
                cliente = new ClienteDTO();
                cargarClientes();
                return "clientes.xhtml?faces-redirect=true";
            }
            agregarMensaje(FacesMessage.SEVERITY_WARN, "Atencion",
                    "No se realizo ningun cambio.");
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error",
                    "Error al guardar cliente: " + e.getMessage());
        }
        return null;
    }

    /**
     * Elimina un cliente.
     */
    public String eliminarCliente(int codigo) {
        try {
            if (controlador.eliminarCliente(codigo)) {
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Exito",
                        "Cliente eliminado correctamente");
                cargarClientes();
            }
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error",
                    "Error al eliminar cliente: " + e.getMessage());
        }
        return null;
    }

    /**
     * Validacion de datos previa a insertar/actualizar.
     * Devuelve true si todo es valido; si no, agrega mensajes de error.
     */
    private boolean datosValidos() {
        boolean valido = true;

        if (esVacio(cliente.getNombres())) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Validacion",
                    "Los nombres son obligatorios.");
            valido = false;
        }
        if (esVacio(cliente.getApellidos())) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Validacion",
                    "Los apellidos son obligatorios.");
            valido = false;
        }
        String correo = cliente.getCorreo();
        if (!esVacio(correo) && !correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$")) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Validacion",
                    "El correo no tiene un formato valido.");
            valido = false;
        }
        String celular = cliente.getCelular();
        if (!esVacio(celular) && !celular.matches("^[0-9]{6,15}$")) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Validacion",
                    "El celular debe contener solo numeros (6 a 15 digitos).");
            valido = false;
        }
        if (cliente.getSexo() != 'M' && cliente.getSexo() != 'F') {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Validacion",
                    "Debe seleccionar el sexo (Masculino o Femenino).");
            valido = false;
        }
        return valido;
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private void agregarMensaje(FacesMessage.Severity severidad, String resumen, String detalle) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        // Conserva los mensajes tras un redirect (patron POST-Redirect-GET).
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        contexto.addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    // Getters y Setters
    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }
}
