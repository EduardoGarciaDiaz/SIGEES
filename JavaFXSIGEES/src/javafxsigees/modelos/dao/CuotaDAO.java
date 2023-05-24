package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.modelos.pojo.Cuota;
import javafxsigees.utils.Codigos;

/**
 *
 * @author Daniel García Arcos
 */
public class CuotaDAO {
    
    private final String OBTENER_CUOTA_VIGENTE = "Select * from sigees.cuotas WHERE esVigente = 1";
    
    public Cuota obtenerCuotaVigente() throws DAOException {
        Cuota cuota = new Cuota();
        cuota.setIdCuota(-1);
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_CUOTA_VIGENTE);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                cuota.setIdCuota(resultado.getInt("idCuota"));
                cuota.setCantidad(resultado.getDouble("cantidad"));
                cuota.setEsVigente(resultado.getBoolean("esVigente"));
                cuota.setFechaCreacion(resultado.getDate("fechaCreacion"));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de consulta", Codigos.ERROR_CONSULTA);
        }
        return cuota;
    }
      
}
