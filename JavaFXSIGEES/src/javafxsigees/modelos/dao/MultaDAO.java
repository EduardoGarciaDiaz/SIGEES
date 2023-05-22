/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 20/05/2023
 * Descripción: DAO de multas
 */

package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.utils.Codigos;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafxsigees.modelos.pojo.Multa;
import javafxsigees.utils.Utilidades;


public class MultaDAO {
    
    private final String OBTENER_TARJETAS_PERDIDAS_DIARIAS = "SELECT COUNT(idMultas) AS tarjetasPerdidas FROM multas " +
        "WHERE DATE(fechaHora) = ? AND idTipoMulta = 1";
    private final String OBTENER_GANANCIAS_POR_MULTAS = "SELECT SUM(cantidad) AS gananciasMultas FROM multas m " +
        "INNER JOIN tipos_Multa tm ON m.idTipoMulta = tm.idTipoMulta " +
        "WHERE DATE(m.fechaHora) = ?";
    private String Consultar_Multas = "Select idMultas, DATE(fechaHora) as fechaMulta, Time(fechaHora) as horaMulta, "+
        "multas.idTipoMulta, multas.idUsuario,\n" +
        "tipos_multa.nombreTipoMulta as concepto, tipos_multa.cantidad as montoMulta, \n" +
        "usuarios.nombre as nombreUsuario from multas\n" +
        "inner join tipos_multa on multas.idTipoMulta = tipos_multa.idTipoMulta\n" +
        "inner join usuarios on multas.idUsuario = usuarios.idUsuario";
    private String Registrar_Pago_Multa ="Insert into sigees.multas (fechaHora, idTipoMulta, idUsuario) values (?, ?, ?);";

private String Consultar_Tipos_Multas = "Select idTipoMulta, tipos_multa.nombreTipoMulta as concepto, \n" +
              "tipos_multa.cantidad as montoMulta\n" +
              "from tipos_multa";
    
    public int obtenerTotalTarjetasPerdidasDiarias(String fechaConsultar) throws DAOException {
        int totalTarjetasPerdidas = 0;
        try {
           PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_TARJETAS_PERDIDAS_DIARIAS);
           sentencia.setString(1, fechaConsultar);
           ResultSet resultado = sentencia.executeQuery();
           if (resultado.next()) {
               totalTarjetasPerdidas = resultado.getInt(1);
           }
           ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Error en la consulta", Codigos.ERROR_CONSULTA);
        }
        return totalTarjetasPerdidas;
    }
        
    public double obtenerGananciasPorMultas(String fechaConsultar) throws DAOException {
        int totalIngresosMultas = 0;
        try {
           PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_GANANCIAS_POR_MULTAS);
           sentencia.setString(1, fechaConsultar);
           ResultSet resultado = sentencia.executeQuery();
           if (resultado.next()) {
               totalIngresosMultas = resultado.getInt(1);
           }
           ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            throw new DAOException("Error en la consulta", Codigos.ERROR_CONSULTA);
        }
        return totalIngresosMultas;
    }
    
    
    public ArrayList<Multa> consultarMultas() throws DAOException {
        ArrayList<Multa> multas = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Consultar_Multas);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()) {
                Multa multa = new Multa();
                multa.setIdMultas(resultadoConsulta.getInt("idMultas"));
                Date fechaMulta = ((Date)resultadoConsulta.getDate("fechaMulta"));
                Time horaMulta =((Time)resultadoConsulta.getTime("horaMulta"));
                try {                   
                    multa.setFechaHora(formatter.parse(fechaMulta+" "+ horaMulta));                     
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                multa.setIdTipoMulta(resultadoConsulta.getInt("idTipoMulta"));
                multa.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                multa.setNombreTipoMulta(resultadoConsulta.getString("concepto"));
                multa.setCantidad(resultadoConsulta.getDouble("montoMulta"));
                multa.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                multas.add(multa);
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            Utilidades.mostrarDialogoSimple("Error Consulta", "Ocurrio un error al recuperar las multas", Alert.AlertType.ERROR);
        }
        return multas;        
    }
    
    public int registrarPagoDeMulta(Multa multa) throws DAOException{
        int respuestaExito = -1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHoraMulta = formatter.format(multa.getFechaHora());
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Registrar_Pago_Multa,Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, fechaHoraMulta);
            sentencia.setInt(2, multa.getIdTipoMulta());
            sentencia.setInt(3,multa.getIdUsuario());
            respuestaExito = sentencia.executeUpdate();
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            Utilidades.mostrarDialogoSimple("Error Registro", "Ocurrio un error al registrar la multa", Alert.AlertType.ERROR);
        }
        return respuestaExito;
    }
    
    public ArrayList<Multa> concultarTiposMultas() throws DAOException {
        ArrayList<Multa> multas = new ArrayList();
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Consultar_Tipos_Multas);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()) {
                Multa multa = new Multa();                
                multa.setIdTipoMulta(resultadoConsulta.getInt("idTipoMulta"));       
                multa.setNombreTipoMulta(resultadoConsulta.getString("concepto"));
                multa.setCantidad(resultadoConsulta.getDouble("montoMulta"));                
                multas.add(multa);
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            Utilidades.mostrarDialogoSimple("Error Consulta", "Ocurrio un error al recuperar los conceptos de multas", Alert.AlertType.ERROR);
        }
        return multas;        
    }
}
