/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import cluedo_board_game.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anilz
 */
public class TileTest {

    public TileTest() {
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
     * Test of getColIndex method, of class Tile.
     */
    @Test
    public void testGetColIndex() {
        System.out.println("getColIndex");
        Tile instance = null;
        int expResult = 0;
        int result = instance.getColIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowIndex method, of class Tile.
     */
    @Test
    public void testGetRowIndex() {
        System.out.println("getRowIndex");
        Tile instance = null;
        int expResult = 0;
        int result = instance.getRowIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IsOccupied method, of class Tile.
     */
    @Test
    public void testIsOccupied() {
        System.out.println("IsOccupied");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.IsOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOccupied method, of class Tile.
     */
    @Test
    public void testSetOccupied() {
        System.out.println("setOccupied");
        boolean isOccupied = false;
        Tile instance = null;
        instance.setOccupied(isOccupied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsWall method, of class Tile.
     */
    @Test
    public void testGetIsWall() {
        System.out.println("getIsWall");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.getIsWall();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWall method, of class Tile.
     */
    @Test
    public void testSetWall() {
        System.out.println("setWall");
        boolean isWall = false;
        Tile instance = null;
        instance.setWall(isWall);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsDoor method, of class Tile.
     */
    @Test
    public void testGetIsDoor() {
        System.out.println("getIsDoor");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.getIsDoor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoor method, of class Tile.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        boolean isDoor = false;
        Tile instance = null;
        instance.setDoor(isDoor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
    
    /**
    
    
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        Tile instance = new Tile();
        //Test default value being false
        assertEquals(instance.getOccupied(), false);

    }

    
    @Test
    public void testSetOccupied() {
        System.out.println("setOccupied");
        boolean isOccupied = true;
        Tile instance = new Tile();       
        instance.setOccupied(isOccupied);
        //Test the occupied value after set true
        assertTrue(instance.getOccupied());

    }

    
    @Test
    public void testIsWall() {
        System.out.println("isWall");
        Tile instance = new Tile();
        //Test default value of tile being wall
        assertEquals(instance.getWall(),false);


    }

    
    @Test
    public void testSetWall() {
        System.out.println("setWall");
        boolean isWall = true;
        Tile instance = new Tile();
        instance.setWall(isWall);
        //Test wall being set true
        assertTrue(instance.getWall());

    }

    
    @Test
    public void testIsDoor() {
        System.out.println("isDoor");
        Tile instance = new Tile();
        //Test default Door being false
        assertEquals(instance.getDoor(), false);

    }

    
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        boolean isDoor = true;
        Tile instance = new Tile();
        instance.setDoor(isDoor);
        //Test Door set true
        assertTrue(instance.getDoor());
    }
}
* */
