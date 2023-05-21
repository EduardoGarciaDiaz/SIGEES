/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsigees.modelos.pojo;

import java.util.Date;

/**
 *
 * @author tristan
 */
public class Multa {
    private int IdMultas;
    private Date fechaHora;
    private int idTipoMulta;
    private String nombreTipoMulta;
    private double cantidad;
    private int idUsuario;
    private String nombreUsuario;

    public Multa() {
    }

    public Multa(int IdMultas, Date fechaHora, int idTipoMulta, String nombreTipoMulta, double cantidad, int idUsuario, String nombreUsuario) {
        this.IdMultas = IdMultas;
        this.fechaHora = fechaHora;
        this.idTipoMulta = idTipoMulta;
        this.nombreTipoMulta = nombreTipoMulta;
        this.cantidad = cantidad;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdMultas() {
        return IdMultas;
    }

    public void setIdMultas(int IdMultas) {
        this.IdMultas = IdMultas;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdTipoMulta() {
        return idTipoMulta;
    }

    public void setIdTipoMulta(int idTipoMulta) {
        this.idTipoMulta = idTipoMulta;
    }

    public String getNombreTipoMulta() {
        return nombreTipoMulta;
    }

    public void setNombreTipoMulta(String nombreTipoMulta) {
        this.nombreTipoMulta = nombreTipoMulta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
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
    
    @Override 
    public String toString() {
        return nombreTipoMulta;
    }
    
}
