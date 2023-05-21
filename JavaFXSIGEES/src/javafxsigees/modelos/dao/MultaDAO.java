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


public class MultaDAO {
    
    private final String OBTENER_TARJETAS_PERDIDAS_DIARIAS = "SELECT COUNT(idMultas) AS tarjetasPerdidas FROM multas " +
        "WHERE DATE(fechaHora) = ? AND idTipoMulta = 1";
    private final String OBTENER_GANANCIAS_POR_MULTAS = "SELECT SUM(cantidad) AS gananciasMultas FROM multas m " +
        "INNER JOIN tipos_Multa tm ON m.idTipoMulta = tm.idTipoMulta " +
        "WHERE DATE(m.fechaHora) = ?";
    
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
        
        
}
