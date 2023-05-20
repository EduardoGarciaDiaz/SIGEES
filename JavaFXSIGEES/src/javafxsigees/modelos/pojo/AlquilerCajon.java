
package javafxsigees.modelos.pojo;

import java.sql.Time;
import java.util.Date;


public class AlquilerCajon {
    
    private int idAlquilerCajon;
    private Date fechaAsignacion;
    private Time horaAsignacion;
    private Date fechaSalida;
    private Time horaSalida;
    private double monto;
    private int idCuota;
    private double couta;
    private int idTarjeta;
    private int idUsuario;
    private String nombreUsuario;

    public AlquilerCajon() {
    }

    public AlquilerCajon(int idAlquilerCajon, Date fechaAsignacion, Time horaAsignacion, Date fechaSalida, Time horaSalida, double monto, int idCuota, double couta, int idTarjeta, int idUsuario, String nombreUsuario) {
        this.idAlquilerCajon = idAlquilerCajon;
        this.fechaAsignacion = fechaAsignacion;
        this.horaAsignacion = horaAsignacion;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.monto = monto;
        this.idCuota = idCuota;
        this.couta = couta;
        this.idTarjeta = idTarjeta;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdAlquilerCajon() {
        return idAlquilerCajon;
    }

    public void setIdAlquilerCajon(int idAlquilerCajon) {
        this.idAlquilerCajon = idAlquilerCajon;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Time getHoraAsignacion() {
        return horaAsignacion;
    }

    public void setHoraAsignacion(Time horaAsignacion) {
        this.horaAsignacion = horaAsignacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public double getCouta() {
        return couta;
    }

    public void setCouta(double couta) {
        this.couta = couta;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    
}
