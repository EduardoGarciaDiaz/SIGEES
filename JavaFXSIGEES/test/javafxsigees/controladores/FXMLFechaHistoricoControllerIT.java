/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsigees.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tristan
 */
public class FXMLFechaHistoricoControllerIT {
    
    public FXMLFechaHistoricoControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    public DatePicker dpFecha;

  
    
    /**
     * Test of validarFecha method, of class FXMLFechaHistoricoController.
     */
    @Test
    public void testValidarFecha() {
        System.out.println("validarFecha");
        FXMLFechaHistoricoController instance = new FXMLFechaHistoricoController();
        boolean expResult = true;
        boolean result = instance.validarFecha();
        assertTrue("Prueba sin exito,la fecha  es nula o posteriro a la actual",expResult == result);         
        System.err.println("Prueba exitosa, fecha valida: "+result );
       
    } 
  

   
    
}
