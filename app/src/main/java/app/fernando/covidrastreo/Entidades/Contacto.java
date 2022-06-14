package app.fernando.covidrastreo.Entidades;

import java.io.Serializable;

public class Contacto implements Serializable {
    public int idContacto;
    public String direccionBT;
    public int estadoInfeccion;
    public int semInfContacto;
    public int idUsuario;

    public Contacto() {
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
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

    public int getSemInfContacto() {
        return semInfContacto;
    }

    public void setSemInfContacto(int semInfContacto) {
        this.semInfContacto = semInfContacto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
