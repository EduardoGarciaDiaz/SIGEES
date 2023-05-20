
package javafxsigees.modelos.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.modelos.pojo.AlquilerCajon;
import javafxsigees.utils.Codigos;
import javafxsigees.utils.Utilidades;


public class AlquilerCajonDAO {
    
    private String Obtener_Cajon = "Select idAlquilerCajon, Date(fechaHoraEntrada) as fechaEntrada, Time(fechaHoraEntrada) as horaEntrada,\n" +
                                   "Date(fechaHoraSalida) as fechaSalida, Date(fechaHoraSalida) as horaSalida, montoPago, alquileres_cajon.idCuota, \n" +
                                   "alquileres_cajon.idTarjeta, alquileres_cajon.idUsuario,  \n" +
                                   "cuotas.cantidad as cuota,  usuarios.nombreUsuario as nombreUsuario from alquileres_cajon\n" +
                                   "inner join cuotas on alquileres_cajon.idCuota = cuotas.idCuota\n" +
                                   "inner join usuarios on alquileres_cajon.idUsuario = usuarios.idUsuario\n" +
                                   "inner join tarjetas on alquileres_cajon.idTarjeta = tarjetas.idTarjeta\n" +
                                   "Where tarjetas.numeroCajon = ? and tarjetas.piso = ?;";
    private String Registrar_Cajon = "Update alquileres_cajon set fechaHoraSalida = concat(?,' ',?), montoPago = ? "+
                                     " where idAlquilerCajon = ?";
    
    public AlquilerCajon obtenerCajonAlquilado(int numeroCajon, int piso) throws DAOException{
        AlquilerCajon alquiler= new AlquilerCajon();
        alquiler.setIdAlquilerCajon(-1);
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Obtener_Cajon);
            sentencia.setInt(1, numeroCajon);
            sentencia.setInt(2, piso);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
               alquiler.setIdAlquilerCajon(resultado.getInt("idAlquilerCajon"));
               alquiler.setFechaAsignacion((Date)resultado.getDate("fechaEntrada"));
               alquiler.setHoraAsignacion((Time)resultado.getTime("horaEntrada"));
               alquiler.setFechaSalida((Date)resultado.getDate("fechaSalida"));
               alquiler.setHoraSalida((Time)resultado.getTime("horaSalida"));
               alquiler.setMonto(resultado.getDouble("montoPago"));
               alquiler.setIdCuota(resultado.getInt("idCuota"));
               alquiler.setIdTarjeta(resultado.getInt("idTarjeta"));
               alquiler.setIdUsuario(resultado.getInt("idUsuario"));
               alquiler.setCouta(resultado.getDouble("cuota"));
               alquiler.setNombreUsuario(resultado.getString("nombreUsuario"));
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {          
            throw new DAOException("Lo sentimos, no pudimos recuperar la informacion del cajon.", Codigos.ERROR_CONSULTA);
        }
        return alquiler;
    }
    
    public int registrarPagoAlquilerCajon(AlquilerCajon pagoAlquilerNuevo) throws DAOException {
        int respuestaExito = -1;
        try {
            
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Registrar_Cajon);            
            sentencia.setString(1,Utilidades.convertirDateToString((Date)pagoAlquilerNuevo.getFechaSalida()));
            sentencia.setString(2, Utilidades.convertirTimeToString((Time)pagoAlquilerNuevo.getHoraSalida()));
            sentencia.setDouble(3,pagoAlquilerNuevo.getMonto());
            sentencia.setInt(4, pagoAlquilerNuevo.getIdAlquilerCajon());
             System.err.println(sentencia);
            sentencia.executeUpdate();           
            respuestaExito = pagoAlquilerNuevo.getIdCuota();
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DAOException("Lo sentimos, no pudimos registrar el pago.", Codigos.ERROR_CONSULTA);
        }
        return respuestaExito;        
    }
    
}
