
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos PostgreSQL
 * Implementa patrón Singleton para reutilizar conexiones
 * Maneja transacciones y proporciona métodos robustos para JDBC
 */
public class Conexion {
    private static Conexion instancia;
    
    private final String url = "jdbc:postgresql://localhost:5432/transportesaqp";
    private final String usuario = "postgres"; 
    private final String contrasena = "TuContraseña";
    private Connection conexion;

    // Constructor privado para Singleton
    private Conexion() {
        cargarDriver();
    }

    /**
     * Obtiene la instancia única de Conexion (Singleton)
     */
    public static synchronized Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    /**
     * Carga el driver de PostgreSQL
     */
    private void cargarDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("[INFO] Driver PostgreSQL cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] El driver PostgreSQL no está en el classpath: " + e.getMessage());
            throw new RuntimeException("No se pudo cargar el driver de PostgreSQL", e);
        }
    }

    /**
     * Obtiene una conexión a la base de datos
     * Si existe una conexión activa, la retorna; si no, crea una nueva
     */
    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(url, usuario, contrasena);
                conexion.setAutoCommit(false); // Deshabilitar autocommit para transacciones
                System.out.println("[INFO] Conexión establecida a la base de datos");
            }
            return conexion;
        } catch (SQLException e) {
            System.err.println("[ERROR] No se pudo conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
    }

    /**
     * Realiza un commit de la transacción actual
     */
    public void commit() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.commit();
            System.out.println("[INFO] Transacción confirmada exitosamente");
        }
    }

    /**
     * Revierte los cambios de la transacción actual
     */
    public void rollback() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.rollback();
            System.out.println("[INFO] Transacción revertida");
        }
    }

    /**
     * Cierra la conexión a la base de datos
     */
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("[INFO] Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al cerrar la conexión: " + e.getMessage());
        }
    }

    /**
     * Verifica si la conexión está activa
     */
    public boolean isConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
