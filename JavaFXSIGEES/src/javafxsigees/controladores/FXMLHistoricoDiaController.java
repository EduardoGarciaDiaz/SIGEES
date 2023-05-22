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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
    private NumberAxis usoPisoEjeY;
    @FXML
    private CategoryAxis usoPisoEjeX;
    @FXML
    private NumberAxis tipoVehiculoEjeY;
    @FXML
    private CategoryAxis tipoVehiculoEjeX;
    @FXML
    private Label lbPiso1;
    @FXML
    private Label lbPiso2;
    @FXML
    private Label lbPiso3;
    @FXML
    private Label lbPiso4;
    
    private String fechaHistorico;
    private ArrayList<String> datosEjeX;
    private ArrayList<Integer> datosEjeY;
    private DecimalFormat formatoDecimal = new DecimalFormat("#.00");
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //llenarDatosHistorico();
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
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        String fecha = fechaConsultar.format(formatoFecha);
        lbFecha.setText(fecha); 
        
    }
    
    private void iniciarGraficoUsoPorPiso() {
        try {
            barChUsoPorPiso.setLegendVisible(true);
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
            iniciarGrafico(barChUsoPorPiso, datosEjeX, datosEjeY, "Pisos");
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
        
        
        
    }
    
    private void iniciarGraficoUsoTipoVehiculo() {
        try {
            barChUsoTipoVehiculo.setLegendVisible(true);
            datosEjeX = new TipoVehiculoDAO().obtenerTiposDeVehiculos();
            
            datosEjeY = new ArrayList<>();
            for(String tipo : datosEjeX) {
                int usoPorTipo = new AlquilerCajonDAO().obtenerUsosPorTipo(tipo, fechaHistorico, 1);
                datosEjeY.add(usoPorTipo);
            }
            datosEjeX.add("Reservados");
            datosEjeY.add(new AlquilerCajonDAO().obtenerUsosPorTipo("Vehiculo", fechaHistorico, 0));
            iniciarGrafico(barChUsoTipoVehiculo, datosEjeX, datosEjeY, "Tipos de lugares");
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void iniciarGrafico(BarChart<String, Integer> graficoBarras, ArrayList<String> datosEjeX, ArrayList<Integer> datosEjeY,
            String leyenda) {
        XYChart.Series<String, Integer> pisos = new XYChart.Series<>();
        pisos.setName(leyenda);

        for(int i = 0 ; i < datosEjeX.size() ; i++) {
            XYChart.Data<String, Integer> dato = new XYChart.Data<>(datosEjeX.get(i),datosEjeY.get(i));
            pisos.getData().add(dato);
        }
        
        
        
        
        /*
        XYChart.Data<String, Integer> dato1 = new XYChart.Data<>("Piso 1", 50);
        XYChart.Data<String, Integer> dato2 = new XYChart.Data<>("Piso 2", 10);
        XYChart.Data<String, Integer> dato3 = new XYChart.Data<>("Piso 3", 22);
        XYChart.Data<String, Integer> dato4 = new XYChart.Data<>("Piso 4", 50);
        pisos.getData().addAll(dato1, dato2, dato3, dato4);
        */
        ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
        data.addAll(pisos);
        graficoBarras.setData(data);
        /*
        dato1.getNode().setStyle("-fx-background-color: #2464DE");
        dato2.getNode().setStyle("-fx-background-color: #2464DE");
        dato3.getNode().setStyle("-fx-background-color: #2464DE");
        dato4.getNode().setStyle("-fx-background-color: #2464DE");
        */
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
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
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
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
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
    

    
    
}
