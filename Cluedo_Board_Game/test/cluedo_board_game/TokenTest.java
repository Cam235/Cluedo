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
    private Token testToken;
    private String name;
    private Tile testLocation;
    @Before
    public void setUp() throws Exception {
        name = "test Token";
        testToken = new Token(name);
    }
    
    @Test
    public void testGetName() {
        //Test name is given test name
        assertEquals(testToken.getName(), name);
    }
    @Test
    public void testSetName() {
        String newName = "new Test Token";
        testToken.setName(newName);
        assertEquals(testToken.getName(),newName);
    }
    @Test
    public void testGetTokenLocation() {
        
        //Test location to be equal as set
    }
    @Test
    public void testSetTokenLocation() {
        
    }
    
}
