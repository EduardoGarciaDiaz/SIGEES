package javafxsigees.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsigees.JavaFXSIGEES;
import javafxsigees.modelos.dao.DAOException;
import javafxsigees.modelos.dao.UsuarioDAO;
import javafxsigees.modelos.pojo.Usuario;
import javafxsigees.utils.Utilidades;


/**
 * FXML Controller class
 *
 * @author tristan
 */
public class FXMLInicioSesionEstacionamientoController implements Initializable {

    @FXML
    private TextField Txf_Usuario;
    @FXML
    private Label Jl_ErrorUsuario;
    @FXML
    private Label Jl_ErrorCOntraseña;
    @FXML
    private PasswordField Pwf_Contraseña;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
    
    private void validarCampos(){
        String usuario = Txf_Usuario.getText();
        String password = Pwf_Contraseña.getText();
        boolean sonValidos =true;
        if(usuario.isEmpty()){
            sonValidos = false;
            Jl_ErrorUsuario.setText("El campo Usuario es requerido");
        }
        if(password.length()==0){
            sonValidos= false;
            Jl_ErrorCOntraseña.setText("El campo Contraseña es requerido");
        }
        if(sonValidos){
            validarCredencialesUsuario(password, usuario);
        }
    }
    
    private void validarCredencialesUsuario(String password, String username){
        UsuarioDAO usuarioDao = new UsuarioDAO();                
        Usuario usuarioRespuesta = null;
        try {
            usuarioRespuesta = usuarioDao.consultarUsuario(password,username);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("ERROR DE PETICION","Intentelo mas tarde",Alert.AlertType.WARNING);
        }
        if (usuarioRespuesta != null){   
            if (usuarioRespuesta.getIdUsuario() > 0){
                seleccionarTipoUsuario(usuarioRespuesta);
            } else{
                Utilidades.mostrarDialogoSimple("Credenciales incorrect", 
             "El usuario y/o constraseña  son incorrectos, profavor verifica informacion",
             Alert.AlertType.WARNING);
            }
        } else {
            Utilidades.mostrarDialogoSimple("ERROR DE PETICION","Intentelo mas tarde",Alert.AlertType.WARNING);
        }     
    }
    
    private void seleccionarTipoUsuario(Usuario usuarioTipo) {
        switch (usuarioTipo.getIdTipoUsuario()) {
            case 1:
                irPantallaPrincipal("vistas/FXMLInicioAdministrador.fxml", "Administracion", usuarioTipo);
                break;
            case 2:
                irPantallaPrincipal("vistas/FXMLAsignarCajon.fxml", "Control de Vehiculos",usuarioTipo);
                break;
            case 3:
                irPantallaPrincipal("vistas/FXMLAsignarCajon.fxml", "Recepcion de Pagos",usuarioTipo);
                break;
            default:
                throw new AssertionError();
        }
    }
    
      private void irPantallaPrincipal(String ruta, String titulo, Usuario usuarioRol){                       
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSIGEES.class.getResource(ruta));
            Parent vista = accesoControlador.load();
            Stage escenarioUsuarios = new Stage();            
            escenarioUsuarios.setScene(new Scene(vista));
            
            
           if (usuarioRol.getIdTipoUsuario() == 1) {
               FXMLInicioAdministradorController asignarCajon= accesoControlador.getController();            
               escenarioUsuarios.setTitle(titulo);
            }else {
               FXMLAsignarCajonController asignarCajon= accesoControlador.getController();            
               asignarCajon.inicializarRol(usuarioRol);
               escenarioUsuarios.setTitle(titulo);
            }
            escenarioUsuarios.initModality(Modality.NONE);
            escenarioUsuarios.setResizable(false);
            escenarioUsuarios.show();
            
            Stage escenarioPrincnipal = (Stage) Txf_Usuario.getScene().getWindow();        
            escenarioPrincnipal.close(); 
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
      
    @FXML
    private void iniciarSesion(ActionEvent event) {
        Jl_ErrorUsuario.setText("");
        Jl_ErrorCOntraseña.setText("");
        validarCampos();
    }
    
}
