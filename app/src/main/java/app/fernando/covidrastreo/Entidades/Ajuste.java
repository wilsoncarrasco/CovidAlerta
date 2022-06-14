package app.fernando.covidrastreo.Entidades;

import java.io.Serializable;

public class Ajuste implements Serializable {
    public int idAjuste;
    public int escaneoAutomatico;
    public int horaActualizacion;
    public int minActualizacion;
    public int botonCovid;
    public int semanaBotonCovid;
    public int idUsuario;

    public Ajuste() {
    }

    public Ajuste(int idAjuste, int escaneoAutomatico, int horaActualizacion, int minActualizacion, int botonCovid, int semanaBotonCovid, int idUsuario) {
        this.idAjuste = idAjuste;
        this.escaneoAutomatico = escaneoAutomatico;
        this.horaActualizacion = horaActualizacion;
        this.minActualizacion = minActualizacion;
        this.botonCovid = botonCovid;
        this.semanaBotonCovid = semanaBotonCovid;
        this.idUsuario = idUsuario;
    }

    public int getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(int idAjuste) {
        this.idAjuste = idAjuste;
    }

    public int getEscaneoAutomatico() {
        return escaneoAutomatico;
    }

    public void setEscaneoAutomatico(int escaneoAutomatico) {
        this.escaneoAutomatico = escaneoAutomatico;
    }

    public int getHoraActualizacion() {
        return horaActualizacion;
    }

    public void setHoraActualizacion(int horaActualizacion) {
        this.horaActualizacion = horaActualizacion;
    }

    public int getMinActualizacion() {
        return minActualizacion;
    }

    public void setMinActualizacion(int minActualizacion) {
        this.minActualizacion = minActualizacion;
    }

    public int getBotonCovid() {
        return botonCovid;
    }

    public void setBotonCovid(int botonCovid) {
        this.botonCovid = botonCovid;
    }

    public int getSemanaBotonCovid() {
        return semanaBotonCovid;
    }

    public void setSemanaBotonCovid(int semanaBotonCovid) {
        this.semanaBotonCovid = semanaBotonCovid;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
