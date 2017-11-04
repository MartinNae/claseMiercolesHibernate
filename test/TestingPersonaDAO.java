/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.PersonaDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.Persona;

/**
 *
 * @author RigoBono
 */
public class TestingPersonaDAO {
    
    public TestingPersonaDAO() {
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

// con el @test podemos testear el codigo sin encesidad de realizar front-end o cosas mas locas
     @Test
     
     //metodo que modifica el nombre un id especifico
     public void hello() {
         PersonaDAO personita=new PersonaDAO();
         Persona personaProNuevoPablo=new Persona();
         personaProNuevoPablo.setNombre("Juan");
         personita.updateById(4, personaProNuevoPablo);
     }
}
