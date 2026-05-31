package DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) para planes de mantenimiento
 */
public class PlanMantenimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int idTecnico;
    private int idVehiculo;
    private String tipoMantenimiento;
    private int kilometraje;
    private String falla;
    private String fecha;
    private double servicioPago;

    // Constructor vacío
    public PlanMantenimientoDTO() {}

    // Constructor con parámetros
    public PlanMantenimientoDTO(int id, int idTecnico, int idVehiculo, String tipoMantenimiento,
                                int kilometraje, String falla, String fecha, double servicioPago) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.idVehiculo = idVehiculo;
        this.tipoMantenimiento = tipoMantenimiento;
        this.kilometraje = kilometraje;
        this.falla = falla;
        this.fecha = fecha;
        this.servicioPago = servicioPago;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getServicioPago() {
        return servicioPago;
    }

    public void setServicioPago(double servicioPago) {
        this.servicioPago = servicioPago;
    }

    @Override
    public String toString() {
        return "PlanMantenimientoDTO{" +
                "id=" + id +
                ", idTecnico=" + idTecnico +
                ", idVehiculo=" + idVehiculo +
                ", tipoMantenimiento='" + tipoMantenimiento + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
