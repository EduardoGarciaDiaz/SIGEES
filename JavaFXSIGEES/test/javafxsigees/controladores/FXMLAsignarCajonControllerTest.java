package javafxsigees.controladores;

import javafx.scene.shape.Rectangle;
import javafxsigees.modelos.dao.CuotaDAO;
import javafxsigees.modelos.pojo.Cuota;
import javafxsigees.modelos.pojo.Tarjeta;
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
public class FXMLAsignarCajonControllerTest {
    
    public FXMLAsignarCajonControllerTest() {
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

    @Test
    public void testObtenerFechaActual(){
        
    }
    
    @Test 
    public void obtenerIdCajon(){
        FXMLAsignarCajonController instance = new FXMLAsignarCajonController();
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setPiso(1);
        tarjeta.setNumeroCajon(1);
        String resultadoEsperado = "cajonA1";
        String resultado = instance.obtenerIdCajon(tarjeta);
        assertTrue("Prueba no exitosa. El id obtenido es incorrecto.", resultadoEsperado.equals(resultado));
    }
    
    @Test
    public void obtenerLetraPiso(){
        FXMLAsignarCajonController instance = new FXMLAsignarCajonController();
        char resultadoEsperado = 'A';
        char resultadoEsperadoDos = 'B';
        char resultadoEsperadoTres = 'C';
        char resultadoEsperadoCuatro = 'D';
        char resultadoUno = instance.obtenerLetraPiso(1);
        char resultadoDos = instance.obtenerLetraPiso(2);
        char resultadoTres = instance.obtenerLetraPiso(3);
        char resultadoCuatro = instance.obtenerLetraPiso(4);
        
        assertTrue("Prueba no exitosa, letras incorrectas.", 
                (resultadoEsperado == resultadoUno)
                && (resultadoEsperadoDos == resultadoDos)
                && (resultadoEsperadoTres == resultadoTres)
                && (resultadoEsperadoCuatro == resultadoCuatro));
        
    }
    
    @Test
    public void obtenerNumeroPiso(){
        FXMLAsignarCajonController instance = new FXMLAsignarCajonController();
        int resultadoEsperado = 2;
        String idCajon = "cajonB2";
        int resultadoObtenido = instance.obtenerNumeroPiso(idCajon);
        assertTrue("Prueba no exitosa: numero de piso no correcto.", resultadoEsperado == resultadoObtenido);
    }
    
    @Test
    public void obtenerInformacionCajon(){
        FXMLAsignarCajonController instance = new FXMLAsignarCajonController();
        Rectangle cajon = new Rectangle();
        cajon.setId("cajonC1");
        Tarjeta tarjetaEsperada = new Tarjeta();
        tarjetaEsperada.setEsReservado(false);
        tarjetaEsperada.setPiso(3);
        tarjetaEsperada.setNumeroCajon(1);
        Tarjeta resultado = instance.obtenerInformacionCajon(cajon);
        boolean bandera = (resultado.isEsReservado() == tarjetaEsperada.isEsReservado()
                && resultado.getPiso() == tarjetaEsperada.getPiso()
                && resultado.getNumeroCajon() == tarjetaEsperada.getNumeroCajon());
        assertTrue("Prueba no exitosa.", bandera);
    }
    
    @Test
    public void obtenerCuotaActual(){
        FXMLAsignarCajonController instance = new FXMLAsignarCajonController();
        Cuota cuotaEsperada = new Cuota();
        cuotaEsperada.setCantidad(20);
        Cuota cuotaResultado = instance.obtenerCuotaActual();
        assertTrue("Prueba no exitosa. Error en la cuota recuperada.",
                cuotaEsperada.getCantidad() == cuotaResultado.getCantidad());
    }
}
