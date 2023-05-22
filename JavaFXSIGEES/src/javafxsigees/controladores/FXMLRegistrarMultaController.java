/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsigees.controladores;


import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsigees.modelos.dao.DAOException;
import javafxsigees.modelos.dao.MultaDAO;
import javafxsigees.modelos.pojo.Multa;
import javafxsigees.modelos.pojo.Tarjeta;
import javafxsigees.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author tristan
 */
public class FXMLRegistrarMultaController implements Initializable {

    @FXML
    private Label lbMontoMulta;
    @FXML
    private ComboBox<Multa> cmbxMulta;
    
    private ObservableList<Multa> multas = FXCollections.observableArrayList();
    private boolean tarjetaPerida;
    private int idUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          cmbxMulta.valueProperty().addListener((ObservableValue<? extends Multa> observable, Multa oldValue, Multa newValue) -> {
              if (newValue != null) {
                  cargarMonto();
              }
        });
    }
    
    public void inicializarInformacion(boolean tarjetaPerida, Tarjeta tarjeta, int idUsuario) {
        this.tarjetaPerida = tarjetaPerida;
        this.idUsuario=idUsuario;
        cargarMonto();
        MultaDAO multaDao = new MultaDAO();
        cargarComboMultas(multaDao);
    }
    
    private void cargarComboMultas(MultaDAO multaDao) {       
        try {           
            multas.addAll(multaDao.concultarTiposMultas());
        }catch (DAOException ex) {
            ex.printStackTrace();
        }
        if(tarjetaPerida) {
            cmbxMulta.setItems(multas); 
            cmbxMulta.setValue(multas.get(0));
            cmbxMulta.setDisable(true);
        }else {                      
            cmbxMulta.setItems(multas);    
        }
        cargarMonto();  
    }
    
    private void cargarMonto() {
        Multa multa = cmbxMulta.getValue();
        if(multa != null) {
            Double monto = multa.getCantidad();
            lbMontoMulta.setText("$ "+Double.toString(monto));
        }else {
            lbMontoMulta.setText("$ 0.00");
        }        
    }
    
    private void registrarMulta(int idUsuario) {
        cmbxMulta.setDisable(false);
        Multa multaNueva = new Multa();       
        LocalDate horaActual = LocalDate.now();
        Date date = Date.from(horaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
        multaNueva.setFechaHora(date);
        multaNueva.setIdTipoMulta(cmbxMulta.getValue().getIdTipoMulta());
        multaNueva.setIdUsuario(idUsuario);
        MultaDAO multaDao = new MultaDAO();
        try {
           int respuesta = multaDao.registrarPagoDeMulta(multaNueva);
            if(respuesta != -1) {
                Utilidades.mostrarDialogoSimple("Registro Exitoso", "Se ha registrado el pago correctamente", Alert.AlertType.INFORMATION);
                cerrarVentanaMultas();
            }else {
                Utilidades.mostrarDialogoSimple("Error Registro", "Ocurrio un error al hacer el registro de la multa", Alert.AlertType.ERROR);
                cerrarVentanaMultas();
            }
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error Registro", "Ocurrio un error al hacer el registro de la multa", Alert.AlertType.ERROR);
            cerrarVentanaMultas();
        }
        
    } 
    
    private void cerrarVentanaMultas() {
        Stage escenarioPrincnipal = (Stage) cmbxMulta.getScene().getWindow();
        Scene escena = cmbxMulta.getScene();
        escenarioPrincnipal.close(); 
    }

    @FXML
    private void clicConfirmar(MouseEvent event) {
        if(lbMontoMulta.getText().equals("$ 0.00")) {
            Utilidades.mostrarDialogoSimple("Registro no valido", "Elige un concepto", Alert.AlertType.WARNING);
        }else {
            registrarMulta(idUsuario);
        }        
    }

    @FXML
    private void clciCancelar(MouseEvent event) {        
           cerrarVentanaMultas();
    }

}
