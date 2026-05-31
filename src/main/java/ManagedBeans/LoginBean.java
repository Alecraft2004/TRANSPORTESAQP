package ManagedBeans;

import Controladores.ControladorUsuario;
import DTO.UsuarioDTO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Managed Bean para gestionar la autenticación de usuarios con JSF
 * Alcance de sesión para mantener el usuario autenticado
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String usuario;
    private String contrasena;
    private UsuarioDTO usuarioAutenticado;
    private ControladorUsuario controlador;
    
    public LoginBean() {
        this.controlador = new ControladorUsuario();
    }
    
    /**
     * Intenta autenticar al usuario con las credenciales ingresadas
     */
    public String autenticar() {
        try {
            usuarioAutenticado = controlador.iniciarSesion(usuario, contrasena);
            
            if (usuarioAutenticado != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                        "Bienvenido " + usuarioAutenticado.getUsuario()));
                return "menu.xhtml?faces-redirect=true"; // Redirige al menú principal
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                        "Usuario o contraseña incorrectos"));
                return null;
            }
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                    "Error de base de datos: " + e.getMessage()));
            return null;
        }
    }
    
    /**
     * Cierra la sesión del usuario
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
    
    public boolean isAutenticado() {
        return usuarioAutenticado != null;
    }
    
    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public UsuarioDTO getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
    
    public void setUsuarioAutenticado(UsuarioDTO usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
}
