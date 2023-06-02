package javafxsigees.modelos.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import javafxsigees.modelos.pojo.AlquilerCajon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel García Arcos
 */
public class AlquilerCajonDAOTest {
    
    public AlquilerCajonDAOTest() {
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
     * Test of guardarAlquilerCajon method, of class AlquilerCajonDAO.
     */
    @Test
    public void testGuardarAlquilerCajon() throws Exception {
        System.out.println("guardarAlquilerCajon");
        AlquilerCajon alquilerCajon = new AlquilerCajon();
        alquilerCajon.setFechaHoraInicio(new Date());
        alquilerCajon.setFechaHoraSalida(new Date());
        alquilerCajon.setIdCuota(1);
        alquilerCajon.setIdTarjeta(45);
        alquilerCajon.setIdUsuario(1);
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        int result = instance.guardarAlquilerCajon(alquilerCajon);
        assertTrue("Prueba no exitosa.", result>0);
    }

    /**
     * Test of obtenerUsosPorPiso method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerUsosPorPiso() throws Exception {
        System.out.println("obtenerUsosPorPiso");
        int piso = 1;
        String fechaEntrada = LocalDate.of(2023, Month.MAY, 15).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        int result = instance.obtenerUsosPorPiso(piso, fechaEntrada);
        assertTrue("Prueba no exitosa.", result>0);
    }

    /**
     * Test of obtenerUsosPorTipo method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerUsosPorTipo() throws Exception {
        System.out.println("obtenerUsosPorTipo");
        String nombreTipo = "Vehiculo";
        String fechaEntrada = LocalDate.of(2023, Month.MAY, 19).toString();
        int esReservado = 0;
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        int result = instance.obtenerUsosPorTipo(nombreTipo, fechaEntrada, esReservado);
        assertTrue("Prueba no exitosa", result>=0);
    }

    /**
     * Test of obtenerTotalGananciasDiarias method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerTotalGananciasDiarias() throws Exception {
        System.out.println("obtenerTotalGananciasDiarias");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        double result = instance.obtenerTotalGananciasDiarias(fechaConsultar);
        assertTrue("Prueba no exitosa", result>=0);
    }

    /**
     * Test of obtenerTotalVehiculosDiarios method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerTotalVehiculosDiarios() throws Exception {
        System.out.println("obtenerTotalVehiculosDiarios");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        int result = instance.obtenerTotalVehiculosDiarios(fechaConsultar);
        assertTrue("Prueba no exitosa", result>=0);
    }

    /**
     * Test of obtenerTotalMultasDiarias method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerTotalMultasDiarias() throws Exception {
        System.out.println("obtenerTotalMultasDiarias");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        int result = instance.obtenerTotalMultasDiarias(fechaConsultar);
        assertTrue("Prueba no exitosa", result>=0);
    }

    /**
     * Test of obtenerTotalGananciasPorTipoVehiculo method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerTotalGananciasPorTipoVehiculo() throws Exception {
        System.out.println("obtenerTotalGananciasPorTipoVehiculo");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        int esReservado = 0;
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        double resultUno = instance.obtenerTotalGananciasPorTipoVehiculo(fechaConsultar, 1, esReservado);
        double resultDos = instance.obtenerTotalGananciasPorTipoVehiculo(fechaConsultar, 2, esReservado);
        assertTrue("Prueba no exitosa", resultUno >= 0 && resultDos >= 0);
    }

    /**
     * Test of obtenerHorasEntradas method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerHorasEntradas() throws Exception {
        System.out.println("obtenerHorasEntradas");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        ArrayList<AlquilerCajon> result = instance.obtenerHorasEntradas(fechaConsultar);
        assertTrue("Prueba no exitosa", result != null);
    }

    /**
     * Test of obtenerPromedioHorasEstancia method, of class AlquilerCajonDAO.
     */
    @Test
    public void testObtenerPromedioHorasEstancia() throws Exception {
        System.out.println("obtenerPromedioHorasEstancia");
        String fechaConsultar = LocalDate.of(2023, Month.MAY, 19).toString();
        AlquilerCajonDAO instance = new AlquilerCajonDAO();
        Double result = instance.obtenerPromedioHorasEstancia(fechaConsultar);
         assertTrue("Prueba no exitosa", result >= 0);
    }
    
}
