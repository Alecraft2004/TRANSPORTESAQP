package DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) para vehículos
 */
public class VehiculoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int codigo;
    private String placa;
    private String numeroSerie;
    private int anhoFabricacion;
    private String color;
    private int cantidadPuertas;
    private double cilindrada;
    private String duenho;

    // Constructor vacío
    public VehiculoDTO() {}

    // Constructor con parámetros
    public VehiculoDTO(int codigo, String placa, String numeroSerie, int anhoFabricacion,
                       String color, int cantidadPuertas, double cilindrada, String duenho) {
        this.codigo = codigo;
        this.placa = placa;
        this.numeroSerie = numeroSerie;
        this.anhoFabricacion = anhoFabricacion;
        this.color = color;
        this.cantidadPuertas = cantidadPuertas;
        this.cilindrada = cilindrada;
        this.duenho = duenho;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getAnhoFabricacion() {
        return anhoFabricacion;
    }

    public void setAnhoFabricacion(int anhoFabricacion) {
        this.anhoFabricacion = anhoFabricacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getDuenho() {
        return duenho;
    }

    public void setDuenho(String duenho) {
        this.duenho = duenho;
    }

    @Override
    public String toString() {
        return "VehiculoDTO{" +
                "codigo=" + codigo +
                ", placa='" + placa + '\'' +
                ", duenho='" + duenho + '\'' +
                '}';
    }
}
