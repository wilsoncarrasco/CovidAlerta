package app.fernando.covidrastreo.Entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
    public int idUsuario;
    public String nombres;
    public String apellidos;
    public String fechaNacimiento;
    public int sexo;
    public String correo;
    public String contrasenia;
    public String direccionBT;
    public int estadoInfeccion;
    public int semInfUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombres, String apellidos, String fechaNacimiento, int sexo, String correo, String contrasenia, String direccionBT, int estadoInfeccion, int semInfUsuario) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.direccionBT = direccionBT;
        this.estadoInfeccion = estadoInfeccion;
        this.semInfUsuario = semInfUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDireccionBT() {
        return direccionBT;
    }

    public void setDireccionBT(String direccionBT) {
        this.direccionBT = direccionBT;
    }

    public int getEstadoInfeccion() {
        return estadoInfeccion;
    }

    public void setEstadoInfeccion(int estadoInfeccion) {
        this.estadoInfeccion = estadoInfeccion;
    }

    public int getSemInfUsuario() {
        return semInfUsuario;
    }

    public void setSemInfUsuario(int semInfUsuario) {
        this.semInfUsuario = semInfUsuario;
    }
}
