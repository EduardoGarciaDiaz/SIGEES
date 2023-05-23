
package javafxsigees.modelos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafxsigees.modelos.ConexionBD;
import javafxsigees.modelos.pojo.Usuario;
import javafxsigees.utils.Utilidades;

public class UsuarioDAO {
    
    private String Consultar_Usuario ="Select idUsuario, nombreUsuario, password, nombre, usuarios.idTipoUsuario as idRol, "+            
                                      "tipo_usuarios.nombreTipoUsuario as rol \n" +
                                      "from usuarios\n" +
                                      "inner join tipo_usuarios on usuarios.idTipoUsuario = tipo_usuarios.IdTipoUsuario\n" +
                                      "where usuarios.password = ? and usuarios.nombreUsuario = ?"; 
    
    public Usuario consultarUsuario(String password, String username) throws DAOException {
        Usuario usuario = null;
        try {            
            PreparedStatement sentencia = ConexionBD.obtenerConexionBD().prepareStatement(Consultar_Usuario);            
            sentencia.setString(1, password);
            sentencia.setString(2, username);            
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if(resultadoConsulta.next()) {                
                usuario = new Usuario();
                usuario.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                usuario.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                usuario.setPassword(resultadoConsulta.getString("password"));
                usuario.setNombre(resultadoConsulta.getString("nombre"));
                usuario.setIdTipoUsuario(resultadoConsulta.getInt("idRol"));
                usuario.setNombreTipoUsuario(resultadoConsulta.getString("rol"));                     
            }else {
                usuario = new Usuario();
                usuario.setIdUsuario(0);
            }                     
            ConexionBD.cerrarConexionBD();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Utilidades.mostrarDialogoSimple("Error", "No se pudo realizar la consulta", Alert.AlertType.ERROR);
        }
        return usuario;
    }
    
}
