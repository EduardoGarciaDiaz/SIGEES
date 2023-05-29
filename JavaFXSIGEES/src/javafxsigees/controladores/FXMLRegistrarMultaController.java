/*
 * Autor: Tristan Eduardo Suarez Santiago
 * Fecha de creación: 20/05/2023
 * Descripción: Contorlador de la vista RegistrarMultas
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsigees.modelos.INotificacionOperacionMulta;
import javafxsigees.modelos.dao.DAOException;
import javafxsigees.modelos.dao.MultaDAO;
import javafxsigees.modelos.dao.TarjetaDAO;
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
    private Tarjeta tarjetaGeneral;
    private INotificacionOperacionMulta interfazNotificaicon;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          cmbxMulta.valueProperty().addListener((ObservableValue<? extends Multa> observable, Multa oldValue, Multa newValue) -> {
              if (newValue != null) {
                  cargarMonto();
              }
        });
    }
    
    
    public void inicializarInformacion(boolean tarjetaPerida, Tarjeta tarjeta, int idUsuario, INotificacionOperacionMulta interfazNotificacion) {
        this.interfazNotificaicon = interfazNotificacion;        
        this.tarjetaPerida = tarjetaPerida;
        this.idUsuario=idUsuario;
        this.tarjetaGeneral = tarjeta;
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
            cmbxMulta.getItems().remove(0);
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
    
    private void verififcarConcepto() {
        if(cmbxMulta.getValue().getIdTipoMulta() ==  1) {
            if(tarjetaGeneral != null){
                desactivarCajon();
                registrarMulta(idUsuario);
                continuarRegistroUsoCajon();                             
            } else {
                Utilidades.mostrarDialogoSimple("Error", "Elija un cajon u otro concepto", Alert.AlertType.ERROR);
            }
        }else {
            registrarMulta(idUsuario);
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
                Utilidades.mostrarDialogoSimple("Registro Exitoso", "Se registro de forma exitosa la multa", Alert.AlertType.INFORMATION);
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
        escenarioPrincnipal.close(); 
    }
    
    private void desactivarCajon() {
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        tarjetaGeneral.setIdEstadoCajon(4);
        try {
            tarjetaDAO.actualizarTarjeta(tarjetaGeneral);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", "Error al desactivar la tarjeta", Alert.AlertType.ERROR);
        }        
    }
    
    private void continuarRegistroUsoCajon() {
       interfazNotificaicon.notitficacionOperacionExitosa();
       cerrarVentanaMultas();  
    }

    @FXML
    private void clicConfirmar(MouseEvent event) {
        if(lbMontoMulta.getText().equals("$ 0.00")) {
            Utilidades.mostrarDialogoSimple("Registro no valido", "Elige un concepto", Alert.AlertType.WARNING);
        }else {
            verififcarConcepto();
        }        
    }

    @FXML
    private void clciCancelar(MouseEvent event) {        
           cerrarVentanaMultas();
    }

}
