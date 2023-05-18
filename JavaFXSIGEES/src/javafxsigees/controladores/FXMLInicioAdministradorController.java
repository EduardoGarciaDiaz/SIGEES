/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 15/05/2023
 * Descripción: Controlador de la vista del inicio del administrador
 */

package javafxsigees.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class FXMLInicioAdministradorController implements Initializable {

    @FXML
    private Button btnInicio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    

    @FXML
    private void clicAdministrarTarjetas(ActionEvent event) {
        System.out.println("No disponible por el momento");
        
    }

    @FXML
    private void clicAdministrarUsuarios(ActionEvent event) {
        System.out.println("No disponible por el momento");    }

    @FXML
    private void clicAdministrarCuotas(ActionEvent event) {
        System.out.println("No disponible por el momento");
    }

    @FXML
    private void clicConsultarHistorico(ActionEvent event) {
        //TODO
    }
    
    @FXML
    private void sobreTarjeta(MouseEvent event) {
        Button b = (Button) event.getSource();
        b.setStyle("-fx-background-color:#dae7f3;");
        b.setScaleX(1.02);
        b.setScaleY(1.02);
         
    }

    @FXML
    private void fueraTarjeta(MouseEvent event) {
        Button b = (Button) event.getSource();
        b.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 13px;");
        b.setScaleX(1);
        b.setScaleY(1);
    }

    
    
}
