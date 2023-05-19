/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 17/05/2023
 * Descripción: Controlador de la vista FechaHistorico
 */

package javafxsigees.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class FXMLFechaHistoricoController implements Initializable {

    @FXML
    private DatePicker dpFecha;
    @FXML
    private Label lbErrorFecha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicGenerarHistorico(ActionEvent event) {
        LocalDate fechaConsultar = validarFecha();
        if(fechaConsultar != null) {
                //TODO GENERAR REPORTE
            System.out.println("Generar reporte");
        }
    }

    private LocalDate validarFecha() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaConsultar = obtenerFecha();
        if (fechaConsultar.isBefore(hoy)){
            System.out.println("disponible: "+fechaConsultar);
            return fechaConsultar;
        }else {
            lbErrorFecha.setText("Seleccione una fecha válida");
            return null;
        } 
    }

    private LocalDate obtenerFecha() {
        return(dpFecha.getValue());  
    }

    @FXML
    private void clicEscogerFecha(ActionEvent event) {
        lbErrorFecha.setText("");
    }
    
}
