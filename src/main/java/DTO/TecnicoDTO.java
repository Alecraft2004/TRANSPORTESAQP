package DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) para técnicos
 */
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int codigo;
    private String nombres;
    private String apellidos;
    private String direccion;
    private char sexo;
    private String correo;
    private String celular;
    private String especialidad;
    private int tiempoServicio;

    // Constructor vacío
    public TecnicoDTO() {}

    // Constructor con parámetros
    public TecnicoDTO(int codigo, String nombres, String apellidos, String direccion,
                      char sexo, String correo, String celular, String especialidad, int tiempoServicio) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.sexo = sexo;
        this.correo = correo;
        this.celular = celular;
        this.especialidad = especialidad;
        this.tiempoServicio = tiempoServicio;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    @Override
    public String toString() {
        return "TecnicoDTO{" +
                "codigo=" + codigo +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
