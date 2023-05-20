/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 16/05/2023
 * Descripción: POJO de Tarjeta para modelar su informacion
 */

package javafxsigees.modelos.pojo;

//TODO
public class Tarjeta {
    
    private int idTarjeta;
    private int numeroCajon;
    private int piso;
    private boolean esReservado;
    private int idEstadoCajon;
    private String nombreEstadoCajon;
    private int idTipoVehiculo;
    private String tipoVehiculo;

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, int numeroCajon, int piso, boolean esReservado, int idEstadoCajon, String nombreEstadoCajon, int idTipoVehiculo, String tipoVehiculo) {
        this.idTarjeta = idTarjeta;
        this.numeroCajon = numeroCajon;
        this.piso = piso;
        this.esReservado = esReservado;
        this.idEstadoCajon = idEstadoCajon;
        this.nombreEstadoCajon = nombreEstadoCajon;
        this.idTipoVehiculo = idTipoVehiculo;
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public int getNumeroCajon() {
        return numeroCajon;
    }

    public void setNumeroCajon(int numeroCajon) {
        this.numeroCajon = numeroCajon;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public boolean isEsReservado() {
        return esReservado;
    }

    public void setEsReservado(boolean esReservado) {
        this.esReservado = esReservado;
    }

    public int getIdEstadoCajon() {
        return idEstadoCajon;
    }

    public void setIdEstadoCajon(int idEstadoCajon) {
        this.idEstadoCajon = idEstadoCajon;
    }

    public String getNombreEstadoCajon() {
        return nombreEstadoCajon;
    }

    public void setNombreEstadoCajon(String nombreEstadoCajon) {
        this.nombreEstadoCajon = nombreEstadoCajon;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    
}
