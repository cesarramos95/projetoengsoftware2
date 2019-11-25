/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.controller.boutiqueervadoce;

import br.inatel.model.boutiqueervadoceprojeto.Cliente;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cesar
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
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
     * Test of connectToDb method, of class ClienteDAO.
     */
    @Test
    public void testConnectToDb() {
        System.out.println("connectToDb");
        ClienteDAO instance = new ClienteDAO();
        instance.connectToDb();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserirCliente method, of class ClienteDAO.
     */
    @Test
    public void testInserirCliente() {
        
        
        System.out.println("inserirCliente");
        
        Cliente novoCliente = null;
        ClienteDAO ins = new ClienteDAO();
        boolean expResult = false;
        boolean result = ins.inserirCliente(novoCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of buscarClientes method, of class ClienteDAO.
     */
    @Test
    public void testBuscarClientes() {
        System.out.println("buscarClientes");
        ClienteDAO instance = new ClienteDAO();
        ArrayList<Cliente> expResult = null;
        ArrayList<Cliente> result = instance.buscarClientes();
        assertEquals(1, result.size());
        
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarNomeCliente method, of class ClienteDAO.
     */
    @Test
    public void testAtualizarNomeCliente() {
        System.out.println("atualizarNomeCliente");
        String novoNome = "";
        String nCpf = "";
        ClienteDAO instance = new ClienteDAO();
        boolean expResult = false;
        boolean result = instance.atualizarNomeCliente(novoNome, nCpf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deletarCliente method, of class ClienteDAO.
     */
    @Test
    public void testDeletarCliente() {
        System.out.println("deletarCliente");
        int id = 34;
        ClienteDAO instance = new ClienteDAO();
        boolean expResult = false;
        boolean result = instance.deletarCliente(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
