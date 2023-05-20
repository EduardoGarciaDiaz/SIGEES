/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 17/05/2023
 * Descripción: Clase para manejar métodos de utilidad y que sean reutilizados
 */

package javafxsigees.utils;


import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafxsigees.modelos.dao.DAOException;

public class Utilidades {
    
    public static void mostrarDialogoSimple(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }
    
    public static Scene inicializarEscena(String ruta) {
        Scene escena = null;
        try {
            Parent vista = FXMLLoader.load(javafxsigees.JavaFXSIGEES.class.getResource(ruta));
            escena = new Scene(vista);
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return escena;
    }
    
    public static String convertirDateToString(Date fechaConvertir) {
        String fechaString = null;
        SimpleDateFormat conver = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;            
        try {
            fechaString = conver.format(fechaConvertir);
        } catch (Exception ex) {
           mostrarDialogoSimple("Error", "Error al convertir las fechas ", Alert.AlertType.WARNING);
        }
        return fechaString;
    }
    
    public static String convertirTimeToString(Time horaConvertir) {
        String horaString = null;
        SimpleDateFormat conver = new SimpleDateFormat("HH:mm:ss");
        Date fecha;            
        try {
            horaString = conver.format(horaConvertir);
        } catch (Exception ex) {
            mostrarDialogoSimple("Error", "Error al convertir las fechas ", Alert.AlertType.WARNING);
        }
        return horaString;
    }
    
    public static Date convertirStringToDate(String fechaConvertir) throws DAOException  {
        LocalDate fechaLocal = LocalDate.parse(fechaConvertir, DateTimeFormatter.ofPattern("yyyy-M-d"));
        Date fechaConvertida = Date.valueOf(fechaLocal);
        return fechaConvertida;
    }
    
    public static Time convertirStringToTime(String horaActual) {
         LocalTime horaLocal = LocalTime.parse(horaActual, DateTimeFormatter.ofPattern("H:m:s"));
         Time horaConvertida = Time.valueOf(horaLocal);
         return horaConvertida;
    }
    
}
