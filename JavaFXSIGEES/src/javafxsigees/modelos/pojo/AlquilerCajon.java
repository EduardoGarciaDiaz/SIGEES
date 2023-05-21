package javafxsigees.modelos.pojo;

import java.util.Date;

/**
 *
 * @author Daniel García Arcos
 */
public class AlquilerCajon {
    
    private int idAlquilerCajon;
    private Date fechaHoraInicio;
    private Date fechaHoraSalida;
    private double montoPago;
    private int idCuota;
    private int idTarjeta;
    private int idUsuario;
    private String cuota;
    private double monto;
    private String nombreUsuario;

    public AlquilerCajon() {
    }

    public AlquilerCajon(int idAlquilerCajon, Date fechaHoraInicio, Date fechaHoraSalida, double montoPago, int idCuota, int idTarjeta, int idUsuario, String cuota) {
        this.idAlquilerCajon = idAlquilerCajon;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraSalida = fechaHoraSalida;
        this.montoPago = montoPago;
        this.idCuota = idCuota;
        this.idTarjeta = idTarjeta;
        this.idUsuario = idUsuario;
        this.cuota = cuota;
    }

    public AlquilerCajon(int idAlquilerCajon, Date fechaHoraInicio, Date fechaHoraSalida, double montoPago, int idCuota, int idTarjeta, int idUsuario, String cuota, double monto, String nombreUsuario) {
        this.idAlquilerCajon = idAlquilerCajon;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraSalida = fechaHoraSalida;
        this.montoPago = montoPago;
        this.idCuota = idCuota;
        this.idTarjeta = idTarjeta;
        this.idUsuario = idUsuario;
        this.cuota = cuota;
        this.monto = monto;
        this.nombreUsuario = nombreUsuario;
    }

    
    public int getIdAlquilerCajon() {
        return idAlquilerCajon;
    }

    public void setIdAlquilerCajon(int idAlquilerCajon) {
        this.idAlquilerCajon = idAlquilerCajon;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
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

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
}
