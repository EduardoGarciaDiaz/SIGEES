package javafxsigees.modelos.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
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
    private String Obtener_Cajon = "Select idAlquilerCajon, Date(fechaHoraEntrada) as fechaEntrada, Time(fechaHoraEntrada) as horaEntrada,\n" +
                                   "montoPago, alquileres_cajon.idCuota, \n" +
                                   "alquileres_cajon.idTarjeta, alquileres_cajon.idUsuario,  \n" +
                                   "cuotas.cantidad as cuota,  usuarios.nombreUsuario as nombreUsuario from sigees.alquileres_cajon\n" +
                                   "inner join cuotas on alquileres_cajon.idCuota = cuotas.idCuota\n" +
                                   "inner join usuarios on alquileres_cajon.idUsuario = usuarios.idUsuario\n" +
                                   "inner join tarjetas on alquileres_cajon.idTarjeta = tarjetas.idTarjeta\n" +
                                   "Where tarjetas.numeroCajon = ? and tarjetas.piso = ? and fechaHoraSalida is null ;";
    private String Registrar_Cajon = "Update sigees.alquileres_cajon set fechaHoraSalida = ?, montoPago = ? "+
                                     " where idAlquilerCajon = ?";
    
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
    
     public AlquilerCajon obtenerCajonAlquilado(int numeroCajon, int piso) throws DAOException{
        AlquilerCajon alquiler= new AlquilerCajon();
        alquiler.setIdAlquilerCajon(-1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Obtener_Cajon);
            sentencia.setInt(1, numeroCajon);
            sentencia.setInt(2, piso);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
               alquiler.setIdAlquilerCajon(resultado.getInt("idAlquilerCajon"));               
               Date fechaInicio = ((Date)resultado.getDate("fechaEntrada"));
               Time horaInicio =((Time)resultado.getTime("horaEntrada"));
                try {                   
                    alquiler.setFechaHoraInicio(formatter.parse(fechaInicio+" "+ horaInicio));                     
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
               alquiler.setMonto(resultado.getDouble("montoPago"));
               alquiler.setIdCuota(resultado.getInt("idCuota"));
               alquiler.setIdTarjeta(resultado.getInt("idTarjeta"));
               alquiler.setIdUsuario(resultado.getInt("idUsuario"));
               alquiler.setCuota(resultado.getString("cuota"));
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        String fechaHoraFin = formatter.format(pagoAlquilerNuevo.getFechaHoraSalida());
        try {            
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Registrar_Cajon);            
            sentencia.setString(1,fechaHoraFin);           
            sentencia.setDouble(2,pagoAlquilerNuevo.getMonto());
            sentencia.setInt(3, pagoAlquilerNuevo.getIdAlquilerCajon());
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
