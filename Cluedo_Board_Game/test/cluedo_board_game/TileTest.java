/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import cluedo_board_game.Tile;
import javafx.scene.text.Text;
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

    private Tile testTile;
    int testCol;
    int testRow;
    
    @Before
    public void setUp() throws Exception {
        testRow = 1;
        testCol = 1;
        testTile = new Tile(testCol,testRow);
        
    }
    
    @Test
    public void testGetColIndex(){
        assertEquals(testTile.getColIndex(),testCol);
    }
    @Test
    public void testGetRowIndex(){
        assertEquals(testTile.getRowIndex(),testRow);
    }
    @Test
    public void testIsOccupied(){
        //Test default value is false
        assertEquals(testTile.isOccupied(),false);
    }
    @Test
    public void testSetOccupied(){
        boolean isOccupied = true;
        testTile.setOccupied(isOccupied);
        //Test the occupied value after set true
        assertTrue(testTile.isOccupied());
    }
    @Test
    public void testIsWall(){
        //Test default value of tile being false
        assertEquals(testTile.isWall(),false);
    }
    @Test
    public void testSetWall(){
        boolean isWall = true;
        testTile.setWall(isWall);
        //Test occupied value after set true
        assertTrue(testTile.isWall());
    }
    @Test
    public void testIsDoor(){
        //Test default value of door being false
        assertEquals(testTile.isDoor(),false);
    }
    @Test
    public void testSetDoor(){
        boolean isDoor = true;
        testTile.setDoor(isDoor);
        //Test occupied value after set true
        assertTrue(testTile.isDoor());
    }
    
}
