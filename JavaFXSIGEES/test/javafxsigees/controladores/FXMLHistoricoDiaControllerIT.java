/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsigees.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
public class FXMLHistoricoDiaControllerIT {
    
    public FXMLHistoricoDiaControllerIT() {
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

    
    /**
     * Test of formatearValor method, of class FXMLHistoricoDiaController.
     */
    @Test
    public void testFormatearValor() {
        System.out.println("formatearValor");
        double valor = 0.0;
        FXMLHistoricoDiaController instance = new FXMLHistoricoDiaController();
        String expResult = "0.00";
        String expResult2 = "23.34";
        String result = instance.formatearValor(0);
        String result2 = instance.formatearValor(23.341641);
        assertTrue("Prueba sin exito, no convierte el 0 a 0.00 ",expResult == result);
        assertTrue("Prueba sin exito, no convierte el numero al formato requerido "+result2,expResult2.equals(result2));
        System.err.println("Prueba exitosa convierte: 0 A "+result+"\n y 23.341641 A "+result2 );
        
    }

    /**
     * Test of validarFecha method, of class FXMLHistoricoDiaController.
     */
    @Test
    public void testValidarFecha() {
        System.out.println("validarFecha");
        FXMLHistoricoDiaController instance = new FXMLHistoricoDiaController();
        boolean expResult = true;
        boolean result = instance.validarFecha();
        assertEquals(expResult, result);
        assertTrue("Prueba sin exito,la fecha  es nula o posteriro a la actual",expResult == result);         
        System.err.println("Prueba exitosa, fecha valida: "+result );
    }

    /**
     * Test of obtenerFecha method, of class FXMLHistoricoDiaController.
     */
    @Test
    public void testObtenerFecha() {
        System.out.println("obtenerFecha");
        FXMLHistoricoDiaController instance = new FXMLHistoricoDiaController();
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.obtenerFecha();    
        assertTrue("Prueba sin exito,la fecha regresa nula", result != null); 
        assertTrue("Prueba sin exito,la fecha no regresa un LocalDate", expResult.getClass() == result.getClass());         
        System.err.println("Prueba exitosa, fecha valida: "+result );
    }
    
}
