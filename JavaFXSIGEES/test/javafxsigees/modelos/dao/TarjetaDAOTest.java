package javafxsigees.modelos.dao;

import java.util.ArrayList;
import javafxsigees.modelos.pojo.Tarjeta;
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
public class TarjetaDAOTest {
    
    public TarjetaDAOTest() {
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
     * Test of obtenerCajon method, of class TarjetaDAO.
     */
    @Test
    public void testObtenerCajon() throws Exception {
        System.out.println("obtenerCajon");
        int numeroCajon = 1;
        int piso = 1;
        TarjetaDAO instance = new TarjetaDAO();
        Tarjeta expResult = new Tarjeta();
        expResult.setNumeroCajon(1);
        expResult.setPiso(1);
        expResult.setEsReservado(true);
        expResult.setIdEstadoCajon(4);
        expResult.setNombreEstadoCajon("No disponible");
        expResult.setIdTipoVehiculo(1);
        expResult.setTipoVehiculo("Vehiculo");
        Tarjeta result = instance.obtenerCajon(numeroCajon, piso);
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto",expResult.getNombreEstadoCajon().equals(result.getNombreEstadoCajon()));
        assertTrue("Prueba no exitosa, Piso distinto", expResult.getPiso() == result.getPiso());
        assertTrue("Prueba no exitosa, esReservado distinto", expResult.isEsReservado() == result.isEsReservado());
        assertTrue("Prueba no exitosa, idEstadoCajon distinto", expResult.getIdEstadoCajon() == result.getIdEstadoCajon());
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto", expResult.getNombreEstadoCajon().equals(result.getNombreEstadoCajon()));
        assertTrue("Prueba no exitosa, idTipoVehiculo distinto",expResult.getIdTipoVehiculo() == result.getIdTipoVehiculo());
        assertTrue("Prueba no exitosa, tipoVehiculo distinto", expResult.getTipoVehiculo().equals(result.getTipoVehiculo()));
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto",expResult.getNombreEstadoCajon().equals(result.getNombreEstadoCajon()));
    }

    /**
     * Test of actualizarTarjeta method, of class TarjetaDAO.
     */
    @Test
    public void testActualizarTarjeta() throws Exception {
        System.out.println("actualizarTarjeta");
        Tarjeta tarjeta = new Tarjeta(1,1,1,true,4,"No disponible",1,"Vehiculo");
        TarjetaDAO instance = new TarjetaDAO();
        int expResult = 1;
        int result = instance.actualizarTarjeta(tarjeta);
        assertEquals("Prueba no exitosa, no se realizó ninguna actualización",expResult, result);
    }

    /**
     * Test of obtenerTarjetas method, of class TarjetaDAO.
     */
    @Test
    public void testObtenerTarjetas() throws Exception {
        System.out.println("obtenerTarjetas");
        Tarjeta tarjeta = new Tarjeta(3,3,1,true,1,"Disponible",1,"Vehiculo");
        TarjetaDAO instance = new TarjetaDAO();
        ArrayList<Tarjeta> expResult = new ArrayList<>();
        expResult.add(tarjeta);
        ArrayList<Tarjeta> result = instance.obtenerTarjetas();
        assertTrue("Prueba no exitosa, no obtiene tarjetas", result.size() > -1);
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto",expResult.get(0).getNombreEstadoCajon().equals(result.get(0).getNombreEstadoCajon()));
        assertTrue("Prueba no exitosa, Piso distinto", expResult.get(0).getPiso() == result.get(0).getPiso());
        assertTrue("Prueba no exitosa, esReservado distinto", expResult.get(0).isEsReservado() == result.get(0).isEsReservado());
        assertTrue("Prueba no exitosa, idEstadoCajon distinto", expResult.get(0).getIdEstadoCajon() == result.get(0).getIdEstadoCajon());
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto", expResult.get(0).getNombreEstadoCajon().equals(result.get(0).getNombreEstadoCajon()));
        assertTrue("Prueba no exitosa, idTipoVehiculo distinto",expResult.get(0).getIdTipoVehiculo() == result.get(0).getIdTipoVehiculo());
        assertTrue("Prueba no exitosa, tipoVehiculo distinto", expResult.get(0).getTipoVehiculo().equals(result.get(0).getTipoVehiculo()));
        assertTrue("Prueba no exitosa, nombreEstadoCajon distinto",expResult.get(0).getNombreEstadoCajon().equals(result.get(0).getNombreEstadoCajon()));
        
    }

    /**
     * Test of obtenerPisos method, of class TarjetaDAO.
     */
    @Test
    public void testObtenerPisos() throws Exception {
        System.out.println("obtenerPisos");
        TarjetaDAO instance = new TarjetaDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(0, "1");
        expResult.add(1, "2");
        expResult.add(2, "3");
        expResult.add(3, "4");
        ArrayList<String> result = instance.obtenerPisos();
        assertEquals("Prueba no exitosa, no obtiene los pisos correctos",expResult, result);
    }
    
}
