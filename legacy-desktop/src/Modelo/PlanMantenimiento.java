
package Modelo;

public class PlanMantenimiento {
    private int codigo;
    private int idTecnico;
    private int idVehiculo;
    private String tipoMantenimiento;
    private int kilometraje;
    private String falla;
    private String fecha;
    private double servicioPago;

    // Constructor vacío
    public PlanMantenimiento() {}

    // Constructor con parámetros
    public PlanMantenimiento(int codigo, int idTecnico, int idVehiculo, String tipoMantenimiento, int kilometraje, String falla, String fecha, double servicioPago) {
        this.codigo = codigo;
        this.idTecnico = idTecnico;
        this.idVehiculo = idVehiculo;
        this.tipoMantenimiento = tipoMantenimiento;
        this.kilometraje = kilometraje;
        this.falla = falla;
        this.fecha = fecha;
        this.servicioPago = servicioPago;
    }

    // Getters y Setters para cada campo
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    // Método toString opcional para depuración
    @Override
    public String toString() {
        return "PlanMantenimiento{" +
                "codigo=" + codigo +
                ", idTecnico=" + idTecnico +
                ", idVehiculo=" + idVehiculo +
                ", tipoMantenimiento='" + tipoMantenimiento + '\'' +
                ", kilometraje=" + kilometraje +
                ", falla='" + falla + '\'' +
                ", fecha='" + fecha + '\'' +
                ", servicioPago=" + servicioPago +
                '}';
    }
}
