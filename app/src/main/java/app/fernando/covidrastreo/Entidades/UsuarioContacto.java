package app.fernando.covidrastreo.Entidades;

public class UsuarioContacto {
    public int idUsuarioContacto;
    public String nombreUsuario;
    public String apellidoUsuario;
    public int sexoUsuario;
    public String direccionBTUsuario;
    public int estadoInfeccionUsuario;
    public int semanaInfeccionUsuario;
    public String direccionBTContacto;
    public int estadoInfeccionContacto;
    public int semanaInfeccionContacto;

    public UsuarioContacto() {
    }

    public int getIdUsuarioContacto() {
        return idUsuarioContacto;
    }

    public void setIdUsuarioContacto(int idUsuarioContacto) {
        this.idUsuarioContacto = idUsuarioContacto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public int getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(int sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getDireccionBTUsuario() {
        return direccionBTUsuario;
    }

    public void setDireccionBTUsuario(String direccionBTUsuario) {
        this.direccionBTUsuario = direccionBTUsuario;
    }

    public int getEstadoInfeccionUsuario() {
        return estadoInfeccionUsuario;
    }

    public void setEstadoInfeccionUsuario(int estadoInfeccionUsuario) {
        this.estadoInfeccionUsuario = estadoInfeccionUsuario;
    }

    public int getSemanaInfeccionUsuario() {
        return semanaInfeccionUsuario;
    }

    public void setSemanaInfeccionUsuario(int semanaInfeccionUsuario) {
        this.semanaInfeccionUsuario = semanaInfeccionUsuario;
    }

    public String getDireccionBTContacto() {
        return direccionBTContacto;
    }

    public void setDireccionBTContacto(String direccionBTContacto) {
        this.direccionBTContacto = direccionBTContacto;
    }

    public int getEstadoInfeccionContacto() {
        return estadoInfeccionContacto;
    }

    public void setEstadoInfeccionContacto(int estadoInfeccionContacto) {
        this.estadoInfeccionContacto = estadoInfeccionContacto;
    }

    public int getSemanaInfeccionContacto() {
        return semanaInfeccionContacto;
    }

    public void setSemanaInfeccionContacto(int semanaInfeccionContacto) {
        this.semanaInfeccionContacto = semanaInfeccionContacto;
    }
}
