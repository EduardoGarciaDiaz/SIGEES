/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 17/05/2023
 * Descripción: Controlador de la vista FechaHistorico
 */

package javafxsigees.controladores;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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
        //dpFecha.setValue(LocalDate.of(2022, Month.MAY,2));
    }    

    @FXML
    private void clicGenerarHistorico(ActionEvent event) {        
        if(validarFecha()) {
            System.out.println("Generar reporte");
            Stage escenarioBase = (Stage) lbErrorFecha.getScene().getWindow();
            escenarioBase.setScene(Utilidades.inicializarEscena("vistas/FXMLHistoricoDia.fxml"));
            escenarioBase.setTitle("Histórico del día");
            escenarioBase.setX(88);
            escenarioBase.setY(49);
            escenarioBase.show();
        }
    }

    private boolean validarFecha() {
        LocalDate hoy = LocalDate.now();
        obtenerFecha();        
        if (fechaConsultar != null && fechaConsultar.isBefore(hoy)) {
            System.out.println("disponible: "+fechaConsultar);
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
