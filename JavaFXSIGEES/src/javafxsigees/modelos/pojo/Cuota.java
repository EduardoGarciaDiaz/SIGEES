package javafxsigees.modelos.pojo;

import java.util.Date;

/**
 *
 * @author Daniel García Arcos
 */
public class Cuota {
    
    private int idCuota;
    private double cantidad;
    private Date fechaCreacion;
    private boolean esVigente;

    public Cuota() {
    }

    public Cuota(int idCuota, double cantidad, Date fechaCreacion, boolean esVigente) {
        this.idCuota = idCuota;
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
        this.esVigente = esVigente;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isEsVigente() {
        return esVigente;
    }

    public void setEsVigente(boolean esVigente) {
        this.esVigente = esVigente;
    }
    
    
}
