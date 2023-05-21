/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 20/05/2023
 * Descripción: DAO de los tipos de vehiculos
 */

package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.utils.Codigos;

public class TipoVehiculoDAO {
    
    private final String OBTENER_TIPOS_DE_VEHICULOS = "SELECT nombreTipoVehiculo FROM tipos_vehiculo";
    
    public ArrayList<String> obtenerTiposDeVehiculos() throws DAOException {
        ArrayList<String> tiposVehiculos = new ArrayList<>();
        try {
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(OBTENER_TIPOS_DE_VEHICULOS);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                String tipoVehiculo = resultado.getString("nombreTipoVehiculo");
                tiposVehiculos.add(tipoVehiculo);
            }
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
                        throw new DAOException("Error en la consulta", Codigos.ERROR_CONSULTA);
        }
        return tiposVehiculos;
    }
    
}
