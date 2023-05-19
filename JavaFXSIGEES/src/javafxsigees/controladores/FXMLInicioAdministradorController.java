/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 15/05/2023
 * Descripción: Controlador de la vista del inicio del administrador
 */

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsigees.utils.Utilidades;

public class FXMLInicioAdministradorController implements Initializable {

    @FXML
    private Label lbAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    

    @FXML
    private void clicAdministrarTarjetas(ActionEvent event) {
        Utilidades.mostrarDialogoSimple("Funcionalidad No disponible",
                "Por ahora esta funcionalidad no está disponible. Inténtelo en la próxima actualización",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void clicAdministrarUsuarios(ActionEvent event) {
        Utilidades.mostrarDialogoSimple("Funcionalidad No disponible",
                "Por ahora esta funcionalidad no está disponible. Inténtelo en la próxima actualización",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void clicAdministrarCuotas(ActionEvent event) {
        Utilidades.mostrarDialogoSimple("Funcionalidad No disponible",
                "Por ahora esta funcionalidad no está disponible. Inténtelo en la próxima actualización",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void clicConsultarHistorico(ActionEvent event) {
            Stage  escenarioBase = new Stage();
            escenarioBase.setScene(Utilidades.inicializarEscena("vistas/FXMLFechaHistorico.fxml"));
            escenarioBase.setTitle("Consultar histórico");
            escenarioBase.initModality(Modality.APPLICATION_MODAL);
            escenarioBase.showAndWait();
        
    }
    
    @FXML
    private void sobreBoton(MouseEvent event) {
        Button botonInicioAdmin = (Button) event.getSource();
        botonInicioAdmin.setStyle("-fx-background-color:#dae7f3; -fx-background-radius: 13px;");
        botonInicioAdmin.setScaleX(1.04);
        botonInicioAdmin.setScaleY(1.04);
         
    }

    @FXML
    private void fueraBoton(MouseEvent event) {
        Button botonInicioAdmin = (Button) event.getSource();
        botonInicioAdmin.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 13px;");
        botonInicioAdmin.setScaleX(1);
        botonInicioAdmin.setScaleY(1);
    }

}
