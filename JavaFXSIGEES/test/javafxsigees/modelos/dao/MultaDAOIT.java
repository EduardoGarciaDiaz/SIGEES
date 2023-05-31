/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsigees.modelos.dao;


import java.util.ArrayList;
import java.util.Date;
import javafxsigees.modelos.pojo.Multa;
import javafxsigees.utils.Utilidades;
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
public class MultaDAOIT {
    
    public MultaDAOIT() {
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
     * Test of obtenerTotalTarjetasPerdidasDiarias method, of class MultaDAO.
     */
    @Test
    public void testObtenerTotalTarjetasPerdidasDiarias() throws Exception {
        System.out.println("obtenerTotalTarjetasPerdidasDiarias");
        String fechaConsultar = "03-03-12";
        MultaDAO instance = new MultaDAO();
        int expResult = 1;
        int result = instance.obtenerTotalTarjetasPerdidasDiarias(fechaConsultar);
        assertTrue("Prueba sin exito", result != 0);
        assertEquals("Prueba no exitosa, no consulta l BD", result ,expResult);        
        System.err.println("Exito, se consultaron las tarjetas perdidas:"+result);        
    }

    /**
     * Test of obtenerGananciasPorMultas method, of class MultaDAO.
     */
    @Test
    public void testObtenerGananciasPorMultas() throws Exception {
        System.out.println("obtenerGananciasPorMultas");
        String fechaConsultar = "03-03-12";
        MultaDAO instance = new MultaDAO();
        double expResult = 400;
        double result = instance.obtenerGananciasPorMultas(fechaConsultar);
        assertTrue("Prueba sin exito", result != 0);
        assertTrue("Prueba sin exito, ",expResult == result);
        System.err.println("Exito, se consultaron las ganancias:"+result); 
    
    }

    /**
     * Test of consultarMultas method, of class MultaDAO.
     */
    @Test
    public void testConsultarMultas() throws Exception {
        System.out.println("consultarMultas");
        MultaDAO instance = new MultaDAO();
        
        Multa multa = new Multa();
        Multa multa1 = new Multa();
        multa.setNombreTipoMulta("Perdida de Tarjeta");
        multa1.setNombreTipoMulta("Falta a la moral");
        ArrayList<Multa> multas2 = new ArrayList();
        multas2.add(0, multa);
        multas2.add(1,multa1);                
                
        ArrayList<Multa> expResult = multas2;
        ArrayList<Multa> result = instance.consultarMultas();
        assertTrue("Prueba no exitosa son distintos",expResult != result);
        assertTrue("Prueba no exitosal, resultado vacio",result != null);
        System.err.println("Si consulta las tarjetas: " + result.get(0)+", "+ result.get(1));
      
    }

    /**
     * Test of registrarPagoDeMulta method, of class MultaDAO.
     */
    @Test
    public void testRegistrarPagoDeMulta() throws Exception {
        System.out.println("registrarPagoDeMulta");
        Multa multa = new Multa();      
        Date fecha = Utilidades.convertirStringToDate("23-05-30 12:12:12");
        multa.setFechaHora(fecha);
        multa.setIdTipoMulta(1);
        multa.setIdUsuario(3);
        MultaDAO instance = new MultaDAO();
        int expResult = 0;
        int result = instance.registrarPagoDeMulta(multa);
        assertTrue("Prueba sin exito, no inserta la multa",result > -1);
        System.err.println("Prueba exitosa, si inserta la multa " + result);
        
    }

    /**
     * Test of concultarTiposMultas method, of class MultaDAO.
     */
    @Test
    public void testConcultarTiposMultas() throws Exception {
        System.out.println("concultarTiposMultas");
        MultaDAO instance = new MultaDAO();
        Multa multa = new Multa();
        Multa multa1 = new Multa();
        Multa multa2 = new Multa();
        multa.setIdTipoMulta(1); multa.setNombreTipoMulta("Perdida de Tarjeta");multa.setCantidad(400);
        multa1.setIdTipoMulta(3);multa1.setNombreTipoMulta("Falta a la moral");multa1.setCantidad(200);
        multa2.setIdTipoMulta(2);multa1.setNombreTipoMulta("Obstruccion");multa1.setCantidad(150);
                
        ArrayList<Multa> multas = new ArrayList();
        multas.add(0, multa);
        multas.add(1,multa1);  
        multas.add(2,multa2);
        ArrayList<Multa> expResult = multas ;
        ArrayList<Multa> result = instance.concultarTiposMultas();
        assertTrue("Prueba no exitosa son distintos",expResult != result);
        assertTrue("Prueba no exitosal, resultado vacio",result != null);
        System.err.println("Si consulta las tarjetas: " + result.get(0)+", "+ result.get(1)+", "+ result.get(2));
        
    }
    
}
