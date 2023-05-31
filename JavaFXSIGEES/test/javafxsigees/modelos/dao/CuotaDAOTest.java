package javafxsigees.modelos.dao;

import javafxsigees.modelos.pojo.Cuota;
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
public class CuotaDAOTest {
    
    public CuotaDAOTest() {
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
     * Test of obtenerCuotaVigente method, of class CuotaDAO.
     */
    
    @Test
    public void testObtenerCuotaVigente() throws Exception {
        System.out.println("obtenerCuotaVigente");
        CuotaDAO instance = new CuotaDAO();
        Cuota expResult = new Cuota();
        Cuota result = instance.obtenerCuotaVigente(); 
        assertTrue("Prueba no exitosa, la cantidad de la cuota es negativa", result.getCantidad() > -1);
        assertTrue("Prueba no exitosa, la cuota viene nula", result != null);
        assertTrue("Prueba no exitosa, la cuota obtenida no es vigente",result.isEsVigente());
        System.out.println("Es vigente: "+result.isEsVigente());
    }
    
    
    
}
