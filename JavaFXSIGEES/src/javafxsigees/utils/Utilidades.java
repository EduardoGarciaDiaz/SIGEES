/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 17/05/2023
 * Descripción: Clase para manejar métodos de utilidad y que sean reutilizados
 */

package javafxsigees.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

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
    
}
