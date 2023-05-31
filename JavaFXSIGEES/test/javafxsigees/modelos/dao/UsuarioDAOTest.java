package javafxsigees.modelos.dao;

import javafxsigees.modelos.pojo.Usuario;
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
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
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
     * Test of consultarUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testConsultarUsuario() throws Exception {
        System.out.println("consultarUsuario");
        String password = "abcd";
        String username = "despachador";
        UsuarioDAO instance = new UsuarioDAO();
        Usuario result = instance.consultarUsuario(password, username);
        Usuario resultDos = instance.consultarUsuario("a1a2a3a4#", "incorrecto");
        assertTrue("Prueba de usuario correcto no exitosa", result.getIdUsuario() >= 1);
        assertTrue("Prueba de usuario incorrecto no exitosa", resultDos.getIdUsuario() < 1);
    }
    
}
