/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;


import cluedo_board_game.Board;
import cluedo_board_game.Tile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Board class, doesn't test tile or tile map related methods which
 * require the javaFx thread
 *
 * @author Anilz
 */
public class BoardTest {

    Board board;
    String[] characterNames = {"Colonel Mustard", "Professor Plum", "Miss Scarlett", "Mr.Green", "Mrs.Peacock", "Mrs.White"};
    
    @Before
    public void setUp() {
        board = new Board(10, 10);
        String[] playerNames = {"p1", "p2", "p3", "p4", "p5", "p6"};
        Character[] playerTypes = {'h', 'h', 'h', 'h', 'h', 'h'};
        ArrayList<String> playerNamesList = new ArrayList<>();
        ArrayList<Character> playerTypesList = new ArrayList<>();
        Collections.addAll(playerNamesList, playerNames);
        Collections.addAll(playerTypesList, playerTypes);
        board.addPlayers(playerNamesList, playerTypesList);
        for(int i = 0; i < board.getPlayerList().size(); i++){
            board.initialisePlayerToken(board.getPlayerList().get(i), characterNames[i]);
        }
    }
    
    /**
     * Test of addPlayers method
     */
    @Test
    public void testAddPlayers() {
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Character> playerTypes = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            playerTypes.add('h');
        }
        playerNames.add("p7");
        playerNames.add("p8");
        playerNames.add("p9");
        board.addPlayers(playerNames,playerTypes);
        assertEquals(9, board.getPlayerList().size());       
    }
    
    /**
     * Test of initialiseWeapon method
     */
    @Test
    public void testInitialiseWeapon() {
        assertEquals("Dagger", board.initialiseWeapon("Dagger").getName());
    }
    
    /**
     * Test of initialisePlayerToken method
     */
    @Test
    public void testInitialisePlayerToken() {
        Player p = new Player(0,"testPlayer");
        board.initialisePlayerToken(p, "testToken");
        assertEquals("testToken", p.getToken().getName());
    }
    
    /**
     * Test of orderPlayerList method
     */
    @Test
    public void testOrderPlayerList() {
        String[] orderedCharacterNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};
        assertEquals("Colonel Mustard", board.getPlayerList().get(0).getToken().getName());
        board.orderPlayerList();
        for(int i = 0; i < orderedCharacterNames.length; i++){
            assertEquals(orderedCharacterNames[i], board.getPlayerList().get(i).getToken().getName());
        }
    }
    
    /**
     * test of incrementCurrentPlayer method
     */
    @Test
    public void testIncrementCurrentPlayer() {
        board.setCurrentPlayer(board.getPlayerList().get(0));
        board.getPlayerList().get(1).setIsPlaying(false);
        board.incrementCurrentPlayer();
        Player p = board.getCurrentPlayer();
        assertEquals(2, board.getPlayerList().indexOf(p));
    }
    
    /**
     * test of getNextActivePlayer method
     */
    @Test
    public void testGetNextActivePlayer() {
        board.setCurrentPlayer(board.getPlayerList().get(0));
        board.getPlayerList().get(1).setIsPlaying(false);
        board.incrementCurrentPlayer();
        Player p = board.getNextActivePlayer(board.getPlayerList().get(0));
        assertEquals(2, board.getPlayerList().indexOf(p));
    }
    
    /**
     * test of getPlayerByCharacterName method
     */
    @Test
    public void testGetPlayerByCharacterName() {
        assertEquals("p1", board.getPlayerByCharacterName("Colonel Mustard").getName());
    }
    
    /**
     * test of distributeCards method
     */
    @Test
    public void testDistributeCards() {
        board.distributeCards();
        assertEquals(3, board.getPlayerList().get(0).getHand().size());
    }
    
    /**
     * test of initialisePlayerDetectiveCards method
     */
    @Test
    public void testInitialisePlayerDetectiveCards() {
        board.initialisePlayerDetectiveCards();
        assertEquals(21, board.getPlayerList().get(0).getCheckList().keySet().size());
    }
}

