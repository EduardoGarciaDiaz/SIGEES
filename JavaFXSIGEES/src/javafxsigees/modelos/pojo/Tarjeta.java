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
    private String tipoVehiculo;
    private boolean esReservado;
    private int idEstadoCajon;
    private String nombreEstadoCajon;

    public Tarjeta(int idTarjeta, int numeroCajon, int piso, String tipoVehiculo, boolean esReservado, int idEstadoCajon, String nombreEstadoCajon) {
        this.idTarjeta = idTarjeta;
        this.numeroCajon = numeroCajon;
        this.piso = piso;
        this.tipoVehiculo = tipoVehiculo;
        this.esReservado = esReservado;
        this.idEstadoCajon = idEstadoCajon;
        this.nombreEstadoCajon = nombreEstadoCajon;
    }
    
    public Tarjeta() {
        
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

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public boolean getEsReservado() {
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
    
    
}
