/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 19/05/2023
 * Descripción: Controlador de la vista de la generación del Historico del Día
 */

package javafxsigees.controladores;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsigees.modelos.dao.AlquilerCajonDAO;
import javafxsigees.modelos.dao.DAOException;
import javafxsigees.modelos.dao.MultaDAO;
import javafxsigees.modelos.dao.TarjetaDAO;
import javafxsigees.modelos.dao.TipoVehiculoDAO;
import javafxsigees.utils.Utilidades;

public class FXMLHistoricoDiaController implements Initializable {

    @FXML
    private Label lbFecha;
    @FXML
    private Label lbTotalGanancias;
    @FXML
    private Label lbTotalVehiculos;
    @FXML
    private Label lbTotalMultas;
    @FXML
    private Label lbGananciasMultas;
    @FXML
    private Label lbTotalTarjetasPerdidas;
    @FXML
    private Label lbGananciasAutomoviles;
    @FXML
    private Label lbGananciasMotocicletas;
    @FXML
    private Label lbGananciasReservados;
    @FXML
    private BarChart<String, Integer> barChUsoPorPiso;
    @FXML
    private BarChart<String, Integer> barChUsoTipoVehiculo;
    @FXML
    private Label lbPiso1;
    @FXML
    private Label lbPiso2;
    @FXML
    private Label lbPiso3;
    @FXML
    private Label lbPiso4;
    @FXML
    private Label lbTipoVehiculo1;
    @FXML
    private Label lbTipoVehiculo2;
    @FXML
    private Label lbTipoVehiculo3;
    @FXML
    private DatePicker dpFechaNueva;
    @FXML
    private Label lbErrorFecha;
    
    private String fechaHistorico;
    private ArrayList<String> datosEjeX;
    private ArrayList<Integer> datosEjeY;
    private DecimalFormat formatoDecimal = new DecimalFormat("#.00");
    private LocalDate nuevaFecha;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void llenarDatosHistorico(LocalDate fechaConsultar) {
        fechaHistorico = fechaConsultar.toString();
        llenarFecha(fechaConsultar);
        iniciarGraficoUsoPorPiso();
        iniciarGraficoUsoTipoVehiculo();
        iniciarDatosGenerales();
        iniciarDatosEspecificos();
    }
    
    public void llenarFecha(LocalDate fechaConsultar) {
        dpFechaNueva.setValue(fechaConsultar);
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        String fecha = fechaConsultar.format(formatoFecha);
        lbFecha.setText(fecha);
    }
    
    private void iniciarGraficoUsoPorPiso() {
        try {
            String colorGrafico = "#82A8F0";
            datosEjeX = new TarjetaDAO().obtenerPisos();
            datosEjeY = new ArrayList<>();
            for(String piso : datosEjeX) {
                int numeroPiso = Integer.parseInt(piso);
                int usoPiso = new AlquilerCajonDAO().obtenerUsosPorPiso(numeroPiso, fechaHistorico);
                datosEjeY.add(usoPiso);
            }
            lbPiso1.setText(String.valueOf(datosEjeY.get(0)));
            lbPiso2.setText(String.valueOf(datosEjeY.get(1)));
            lbPiso3.setText(String.valueOf(datosEjeY.get(2)));
            lbPiso4.setText(String.valueOf(datosEjeY.get(3)));
            iniciarGrafico(barChUsoPorPiso, datosEjeX, datosEjeY, colorGrafico);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void iniciarGraficoUsoTipoVehiculo() {
        try {
            String colorGrafico = "#B4C8EF";
            datosEjeX = new TipoVehiculoDAO().obtenerTiposDeVehiculos();
            datosEjeY = new ArrayList<>();
            for(String tipo : datosEjeX) {
                int usoPorTipo = new AlquilerCajonDAO().obtenerUsosPorTipo(tipo, fechaHistorico, 1);
                datosEjeY.add(usoPorTipo);
            }
            datosEjeX.add("Reservados");
            datosEjeY.add(new AlquilerCajonDAO().obtenerUsosPorTipo("Vehiculo", fechaHistorico, 0));
            lbTipoVehiculo1.setText(String.valueOf(datosEjeY.get(0)));
            lbTipoVehiculo2.setText(String.valueOf(datosEjeY.get(1)));
            lbTipoVehiculo3.setText(String.valueOf(datosEjeY.get(2)));
            iniciarGrafico(barChUsoTipoVehiculo, datosEjeX, datosEjeY, colorGrafico);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void iniciarGrafico(BarChart<String, Integer> graficoBarras, ArrayList<String> datosEjeX, ArrayList<Integer> datosEjeY,
            String colorGrafico) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(int i = 0 ; i < datosEjeX.size() ; i++) {
            XYChart.Data<String, Integer> dato = new XYChart.Data<>(datosEjeX.get(i),datosEjeY.get(i));
            series.getData().add(dato);
        }
        ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
        data.addAll(series);
        graficoBarras.setData(data);
        for(Node nodo : graficoBarras.lookupAll(".default-color0.chart-bar")) {
           nodo.setStyle("-fx-bar-fill: "+ colorGrafico);   
        }   
    }

    private void iniciarDatosGenerales() {
        Double ganancias;
        try {
            ganancias = new AlquilerCajonDAO().obtenerTotalGananciasDiarias(fechaHistorico);
            String totalGanancias = formatearValor(ganancias);
            String totalVehiculos = String.valueOf(new AlquilerCajonDAO().obtenerTotalVehiculosDiarios(fechaHistorico));
            String totalMultas = String.valueOf(new AlquilerCajonDAO().obtenerTotalMultasDiarias(fechaHistorico));
            String totalTarjetasPerdidas = String.valueOf(new MultaDAO().obtenerTotalTarjetasPerdidasDiarias(fechaHistorico));
            lbTotalGanancias.setText("$ " + totalGanancias);
            lbTotalVehiculos.setText(totalVehiculos);
            lbTotalMultas.setText(totalMultas);
            lbTotalTarjetasPerdidas.setText(totalTarjetasPerdidas);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Oh, ou", "Ocurrió un error inesperado, porfavor inténtelo de nuevo", Alert.AlertType.ERROR);
        }
    }

    private void iniciarDatosEspecificos() {
        Double ganancias;
        try {
            ganancias = new MultaDAO().obtenerGananciasPorMultas(fechaHistorico);
            String gananciasPorMultas = formatearValor(ganancias);
            ganancias = new AlquilerCajonDAO().obtenerTotalGananciasPorTipoVehiculo(fechaHistorico, 1, 1);
            String gananciasPorAutomoviles = formatearValor(ganancias);
            ganancias = new AlquilerCajonDAO().obtenerTotalGananciasPorTipoVehiculo(fechaHistorico, 2, 1);
            String gananciasPorMotocicletas = formatearValor(ganancias);
            ganancias = new AlquilerCajonDAO().obtenerTotalGananciasPorTipoVehiculo(fechaHistorico, 1, 0);
            String gananciasPorReservados = formatearValor(ganancias);
            lbGananciasMultas.setText("$ " + gananciasPorMultas);
            lbGananciasAutomoviles.setText("$ " + gananciasPorAutomoviles);
            lbGananciasMotocicletas.setText("$ " + gananciasPorMotocicletas);
            lbGananciasReservados.setText("$ " + gananciasPorReservados);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Oh, ou", "Ocurrió un error inesperado, porfavor inténtelo de nuevo", Alert.AlertType.ERROR);
        }
    }
    
    private String formatearValor(double valor) {
        if (valor == 0) {
            return "0.00";
        } else {
             return formatoDecimal.format(valor);
        }
    }

    @FXML
    private void clicRegresar(MouseEvent event) {
        Stage escenarioBase = (Stage) lbFecha.getScene().getWindow();
        escenarioBase.close();
    }

    @FXML
    private void clicEscogerFecha(ActionEvent event) {
        lbErrorFecha.setText("");
        if (validarFecha()) {
            llenarDatosHistorico(nuevaFecha);
        }
        
    }
    
    private boolean validarFecha() {
        LocalDate hoy = LocalDate.now();
        nuevaFecha = obtenerFecha();        
        if (nuevaFecha != null && nuevaFecha.isBefore(hoy)) {
            return true;
        }else{
            lbErrorFecha.setText("Seleccione una fecha válida");
            return false;
        }
    }
    
    private LocalDate obtenerFecha() {
        return dpFechaNueva.getValue();
    }

    
    
}
