package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.modelos.pojo.Tarjeta;
import javafxsigees.utils.Codigos;

/**
 *
 * @author Daniel García Arcos
 */
public class TarjetaDAO {
    
    private String OBTENER_CAJON = "SELECT " +
        "idTarjeta, numeroCajon, piso, esReservado, t.idEstadoCajon, t.idTipoVehiculo, ec.nombreEstadoCajon, nombreTipoVehiculo " +
        "FROM tarjetas t inner join estados_cajon ec on t.idEstadoCajon = ec.idEstadoCajon inner join tipos_vehiculo tv " +
        "on t.idTipoVehiculo = tv.idTipoVehiculo " +
        "WHERE numeroCajon = ? and piso = ?;";
    private final String ACTUALIZAR_TARJETA = "UPDATE tarjetas SET numeroCajon = ?, piso = ?, esReservado = ?, idEstadoCajon = ?, "
                        + "idTipoVehiculo = ? WHERE idTarjeta = ?";
    private final String OBTENER_TARJETAS = "SELECT " +
        "idTarjeta, numeroCajon, piso, esReservado, t.idEstadoCajon, t.idTipoVehiculo, " +
        "ec.nombreEstadoCajon, nombreTipoVehiculo " +
        "FROM tarjetas t " +
        "inner join estados_cajon ec " +
        "on t.idEstadoCajon = ec.idEstadoCajon " +
        "inner join tipos_vehiculo tv " +
        "on t.idTipoVehiculo = tv.idTipoVehiculo ";
    private final String OBTENER_PISOS = "SELECT DISTINCT(piso) FROM tarjetas;";
    
    public Tarjeta obtenerCajon(int numeroCajon, int piso) throws DAOException {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdTarjeta(-1);
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_CAJON);
            sentencia.setInt(1, numeroCajon);
            sentencia.setInt(2, piso);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                tarjeta.setIdTarjeta(resultado.getInt("idTarjeta"));
                tarjeta.setNumeroCajon(resultado.getInt("numeroCajon"));
                tarjeta.setPiso(resultado.getInt("piso"));
                tarjeta.setIdTipoVehiculo(resultado.getInt("idTipoVehiculo"));
                tarjeta.setTipoVehiculo(resultado.getString("nombreTipoVehiculo"));
                tarjeta.setEsReservado(resultado.getBoolean("esReservado"));
                tarjeta.setIdEstadoCajon(resultado.getInt("idEstadoCajon"));
                tarjeta.setNombreEstadoCajon(resultado.getString("nombreEstadoCajon"));
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException("Lo sentimos, no pudimos recuperar la informacion de la tarjeta.", Codigos.ERROR_CONSULTA);
        }
        return tarjeta;
    }
    
    public int actualizarTarjeta(Tarjeta tarjeta) throws DAOException {
        int respuesta = -1;
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(ACTUALIZAR_TARJETA);
            sentencia.setInt(1, tarjeta.getNumeroCajon());
            sentencia.setInt(2, tarjeta.getPiso());
            sentencia.setBoolean(3, tarjeta.isEsReservado());
            sentencia.setInt(4, tarjeta.getIdEstadoCajon());
            sentencia.setInt(5, tarjeta.getIdTipoVehiculo());
            sentencia.setInt(6, tarjeta.getIdTarjeta());
            respuesta = sentencia.executeUpdate();
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Error consulta", Codigos.ERROR_CONSULTA);
        }
        return respuesta;
    }
    
    public ArrayList<Tarjeta> obtenerTarjetas() throws DAOException {
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        try{
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_TARJETAS);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setIdTarjeta(resultado.getInt("idTarjeta"));
                tarjeta.setNumeroCajon(resultado.getInt("numeroCajon"));
                tarjeta.setPiso(resultado.getInt("piso"));
                tarjeta.setIdTipoVehiculo(resultado.getInt("idTipoVehiculo"));
                tarjeta.setTipoVehiculo(resultado.getString("nombreTipoVehiculo"));
                tarjeta.setEsReservado(resultado.getBoolean("esReservado"));
                tarjeta.setIdEstadoCajon(resultado.getInt("idEstadoCajon"));
                tarjeta.setNombreEstadoCajon(resultado.getString("nombreEstadoCajon"));
                tarjetas.add(tarjeta);
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Error de consulta", Codigos.ERROR_CONSULTA);
        }
        return tarjetas;
    }
    
    public ArrayList<String> obtenerPisos() throws DAOException {
        ArrayList<String> pisos = new ArrayList<>();
        try{
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_PISOS);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                pisos.add(resultado.getString("piso"));
            }            
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Error de consulta", Codigos.ERROR_CONSULTA);
        }
        return pisos;
    }
}
