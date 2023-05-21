/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 17/05/2023
 * Descripción: Controlador de la vista FechaHistorico
 */

package javafxsigees.controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxsigees.utils.Utilidades;

public class FXMLFechaHistoricoController implements Initializable {
    
    private LocalDate fechaConsultar;

    @FXML
    private DatePicker dpFecha;
    @FXML
    private Label lbErrorFecha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicGenerarHistorico(ActionEvent event) {        
        if (validarFecha()){
            try {
                FXMLLoader accesoControlador = new FXMLLoader(javafxsigees.JavaFXSIGEES.class.getResource("vistas/FXMLHistoricoDia.fxml"));
                Parent vista = accesoControlador.load();
                FXMLHistoricoDiaController historicoController = accesoControlador.getController();
                historicoController.llenarDatosHistorico(fechaConsultar);
                Stage escenarioBase = (Stage) lbErrorFecha.getScene().getWindow();
                escenarioBase.setScene(new Scene(vista));
                escenarioBase.setTitle("Histórico del día");
                escenarioBase.setX(88);
                escenarioBase.setY(15);
                escenarioBase.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }

    private boolean validarFecha() {
        LocalDate hoy = LocalDate.now();
        obtenerFecha();        
        if (fechaConsultar != null && fechaConsultar.isBefore(hoy)) {
            return true;
        }else{
            lbErrorFecha.setText("Seleccione una fecha válida");
            return false;
        }
    }

    private void obtenerFecha() {
        fechaConsultar = dpFecha.getValue();
    }

    @FXML
    private void clicEscogerFecha(ActionEvent event) {
        lbErrorFecha.setText("");
    }
    
}
