package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    private String OBTENER_CAJON = "SELECT idTarjeta, numeroCajon, piso, tipoVehiculo, esReservado, tarjetas.idEstadoCajon, "
            + "estados_cajon.nombreEstadoCajon "
            + "FROM tarjetas INNER JOIN estados_cajon ON tarjetas.idEstadoCajon = estados_cajon.idEstadoCajon "
            + "WHERE numeroCajon = ? and piso = ?;";
    private String CAMBIAR_ESTADO ="Update tarjetas set idEstadoCajon = ? where idTarjeta = ?";
    
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
                tarjeta.setTipoVehiculo(resultado.getString("tipoVehiculo"));
                tarjeta.setEsReservado(resultado.getBoolean("esReservado"));
                tarjeta.setIdEstadoCajon(resultado.getInt("idEstadoCajon"));
                tarjeta.setNombreEstadoCajon(resultado.getString("nombreEstadoCajon"));
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Lo sentimos, no pudimos recuperar la informacion de la tarjeta.", Codigos.ERROR_CONSULTA);
        }
        return tarjeta;
    }
    
    public void cambiarEstadoTarjeta(int estadoNuevo, int idTarjeta) throws DAOException{
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(CAMBIAR_ESTADO);
            sentencia.setInt(1, estadoNuevo);
            sentencia.setInt(2, idTarjeta);
            sentencia.executeUpdate();
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
           throw new DAOException("Lo sentimos, no pudimos cambiar el estado de la tarjeta.", Codigos.ERROR_CONSULTA);
        }
    }
}
