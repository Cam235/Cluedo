/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cwood
 */
public class TokenTest {
    
    public TokenTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getName method, of class Token.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Token instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Token.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Token instance = null;
        instance.setName(name);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTokenLocation method, of class Token.
     */
    @Test
    public void testGetTokenLocation() {
        System.out.println("getTokenLocation");
        Token instance = null;
        Tile expResult = null;
        Tile result = instance.getTokenLocation();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTokenLocation method, of class Token.
     */
    @Test
    public void testSetTokenLocation() {
        System.out.println("setTokenLocation");
        Tile tokenLocation = null;
        Token instance = null;
        instance.setTokenLocation(tokenLocation);
        fail("The test case is a prototype.");
    }
    
}
