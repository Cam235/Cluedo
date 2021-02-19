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
     * Test of isOccupied method, of class Tile.
     */
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        Tile instance = new Tile();
        //Test default value being false
        assertEquals(instance.isOccupied(), false);

    }

    /**
     * Test of setOccupied method, of class Tile.
     */
    @Test
    public void testSetOccupied() {
        System.out.println("setOccupied");
        boolean isOccupied = true;
        Tile instance = new Tile();       
        instance.setOccupied(isOccupied);
        //Test the occupied value after set true
        assertTrue(instance.isOccupied());

    }

    /**
     * Test of isWall method, of class Tile.
     */
    @Test
    public void testIsWall() {
        System.out.println("isWall");
        Tile instance = new Tile();
        //Test default value of tile being wall
        assertEquals(instance.isWall(),false);


    }

    /**
     * Test of setWall method, of class Tile.
     */
    @Test
    public void testSetWall() {
        System.out.println("setWall");
        boolean isWall = true;
        Tile instance = new Tile();
        instance.setWall(isWall);
        //Test wall being set true
        assertTrue(instance.isWall());

    }

    /**
     * Test of isDoor method, of class Tile.
     */
    @Test
    public void testIsDoor() {
        System.out.println("isDoor");
        Tile instance = new Tile();
        //Test default Door being false
        assertEquals(instance.isDoor(), false);

    }

    /**
     * Test of setDoor method, of class Tile.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        boolean isDoor = true;
        Tile instance = new Tile();
        instance.setDoor(isDoor);
        //Test Door set true
        assertTrue(instance.isDoor());
    }
}
