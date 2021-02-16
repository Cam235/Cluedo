/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_gameTest;

import cluedo_board_game.Board;
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
public class BoardTest {

    int h = 5;
    int w = 5;
    Board instance;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        instance = new Board(w, h);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTileMap method, of class Board.
     */
    @Test
    public void testNonOccupiedMap() {
        System.out.println("TestMap");
        String testNonOccupiedMap = "";
        for (int _h = 0; _h < h; _h++) {
            for (int _w = 0; _w < w; _w++) {
                testNonOccupiedMap += "F";
            }
            testNonOccupiedMap+="\n";
        }
        assertEquals(instance.toString(),testNonOccupiedMap); 
    }

}
