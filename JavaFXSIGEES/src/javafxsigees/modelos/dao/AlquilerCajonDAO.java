package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.modelos.pojo.AlquilerCajon;
import javafxsigees.utils.Codigos;

/**
 *
 * @author Daniel García Arcos
 */
public class AlquilerCajonDAO {
    private String GUARDAR_ALQUILER_CAJON = "INSERT INTO sigees.alquileres_cajon "
            + "(fechaHoraEntrada, fechaHoraSalida, montoPago, idCuota, idTarjeta, idUsuario) values (?, ?, ?, ?, ?, ?);";
    
    public int guardarAlquilerCajon(AlquilerCajon alquilerCajon) throws DAOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHoraInicio = formatter.format(alquilerCajon.getFechaHoraInicio());
        //String fechaHoraFin = formatter.format(alquilerCajon.getFechaHoraSalida());
        String fechaHoraFin = null;
        int respuesta = -1;
        try{
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(GUARDAR_ALQUILER_CAJON,
                    Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, fechaHoraInicio);
            sentencia.setString(2, fechaHoraFin);
            sentencia.setDouble(3, alquilerCajon.getMontoPago());
            sentencia.setInt(4, alquilerCajon.getIdCuota());
            sentencia.setInt(5, alquilerCajon.getIdTarjeta());
            sentencia.setInt(6, alquilerCajon.getIdUsuario());
            sentencia.executeUpdate();
            ResultSet resultado = sentencia.getGeneratedKeys();
            while (resultado.next()) {
                respuesta = resultado.getInt(1);
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException("Error en la consulta", Codigos.ERROR_CONSULTA);
        }
        return respuesta;
    }
}
