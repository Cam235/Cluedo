/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;


import cluedo_board_game.Board;
import cluedo_board_game.Tile;
import java.util.ArrayList;
import java.util.List;
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
    Board board;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        board = new Board(w, h);
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
                testNonOccupiedMap += "X";
            }
            testNonOccupiedMap += "\n";
        }
        assertEquals(board.toString(), testNonOccupiedMap);
    }

    @Test
    public void testOccupiedMap() {
        System.out.println("TestOccupiedMap");
        board.initializePawn("Z", 0, 0);
        String testOccupiedMap = "";
        for (int _h = 0; _h < h; _h++) {
            for (int _w = 0; _w < w; _w++) {
                //tests if at 0,0 it is occupied)
                testOccupiedMap += (_h == 0 && _w == 0) ? "O" : "X";
            }
            testOccupiedMap += "\n";
        }
        assertEquals(board.toString(), testOccupiedMap);
    }

    @Test
    public void testFailedPawnInitiation() {
        System.out.println("TestFailedInitiation");
        assertEquals(board.initializePawn("Z", -1, 0), null);
    }

    /*@Test
    public void testPawnMovementToSide() {
        System.out.println("TestSideMovement");
        board.initializePawn("Z", 0, 0);
        board.movePawn(1, 0);
        String testOccupiedMap = "";
        for (int _h = 0; _h < h; _h++) {
            for (int _w = 0; _w < w; _w++) {
                //tests if at 0,0 it is occupied)
                testOccupiedMap += (_w == 1 && _h == 0) ? "O" : "X";
            }
            testOccupiedMap += "\n";
        }
        assertEquals(board.toString(), testOccupiedMap);
        System.out.println(testOccupiedMap);
    }

    @Test
    public void testPawnMovementToLower() {
        System.out.println("TestLowerMovement");
        board.initializePawn("Z", 1, 0);
        board.movePawn(0, 0);
        String testOccupiedMap = "";
        for (int _h = 0; _h < h; _h++) {
            for (int _w = 0; _w < w; _w++) {
                //tests if at 0,0 it is occupied)
                testOccupiedMap += (_w == 0 && _h == 0) ? "O" : "X";
            }
            testOccupiedMap += "\n";
        }
        assertEquals(board.toString(), testOccupiedMap);
    }

    @Test
    public void testFailedMovement() {
        System.out.println("TestFailedMovement");
        board.initializePawn("Z", 0, 0);
        //See it failss
        board.movePawn(2,0);
        board.movePawn(-1,3);
        String testOccupiedMap = "";
        for (int _h = 0; _h < h; _h++) {
            for (int _w = 0; _w < w; _w++) {
                //tests if at 0,0 it is occupied)
                testOccupiedMap += (_w == 0 && _h == 0) ? "O" : "X";
            }
            testOccupiedMap += "\n";
        }
        assertEquals(board.toString(),testOccupiedMap);
    }
    
    */
    
    /**
     * Test of addPlayers method, of class Board.
     */
    @Test
    public void testaddPlayers() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("p1");
        playerNames.add("p2");
        playerNames.add("p3");
        board.addPlayers(playerNames);
        assertEquals(3, board.getPlayerList().keySet().size());       
    }
}

