package javafxsigees.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TextField TxfUsuario;
    @FXML
    private PasswordField pwfContraseña;
    @FXML
    private Label lbMensajeError;
    @FXML
    private Label lbusuarioVacio;
    @FXML
    private Label lbContraseñaVacia;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onFocus(TxfUsuario);
        onFocus(pwfContraseña);
    }  
    
    private void validarCampos(){
        String usuario = TxfUsuario.getText();
        String password = pwfContraseña.getText();
        boolean sonValidos =true;
        if(usuario.isEmpty()){
            sonValidos = false;
            lbusuarioVacio.setText("Olvidaste ingresar tu usuario.");
        }
        if(password.length()==0){
            sonValidos= false;
            lbContraseñaVacia.setText("Olvidaste ingresar tu contraseña");
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
            if (usuarioRespuesta.getIdUsuario() > 0){
                seleccionarTipoUsuario(usuarioRespuesta);
            } else{
                lbMensajeError.setText("¡Usuario o contraseña incorrectos!");
            }       
        } catch (DAOException ex) {
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
            
            Stage escenarioPrincnipal = (Stage) TxfUsuario.getScene().getWindow();        
            escenarioPrincnipal.close(); 
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
      
    @FXML
    private void iniciarSesion(ActionEvent event) {
        lbContraseñaVacia.setText("");
        lbusuarioVacio.setText("");
        validarCampos();
    }
    
    private void onFocus(TextField campo){
        campo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    lbContraseñaVacia.setText("");
                    lbusuarioVacio.setText("");
                    lbMensajeError.setText("");
                }
            }
        });
    }
    
}
