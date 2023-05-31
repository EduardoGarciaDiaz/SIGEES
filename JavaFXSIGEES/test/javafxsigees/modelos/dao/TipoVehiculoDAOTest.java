package javafxsigees.modelos.dao;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduardo García
 */
public class TipoVehiculoDAOTest {
    
    public TipoVehiculoDAOTest() {
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
     * Test of obtenerTiposDeVehiculos method, of class TipoVehiculoDAO.
     */
    @Test
    public void testObtenerTiposDeVehiculos() throws Exception {
        System.out.println("obtenerTiposDeVehiculos");
        TipoVehiculoDAO instance = new TipoVehiculoDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Motocicleta");
        expResult.add("Vehiculo");
        ArrayList<String> result = instance.obtenerTiposDeVehiculos();
        assertTrue("Prueba no exitosa, no obtiene vehiculos nulo", result != null);
        assertEquals("Prueba no exitosa, no tiene los tipos correctos",expResult, result);
    }
    
}
