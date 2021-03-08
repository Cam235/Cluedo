/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cwood
 */
public class PlayerTest {
    
    private Player humanPlayer;
    private Player agentPlayer;
    private ArrayList<Card> hPHand;
    private ArrayList<Card> aPHand;
    private Token hPToken;
    private Token aPToken;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        humanPlayer = new Human(1,"hP");
        agentPlayer = new Agent(2,"aP");
        hPHand = new ArrayList<>();
        aPHand = new ArrayList<>();
        hPHand.add(new Card(CardType.Person, "Miss Scarlett"));
        aPHand.add(new Card(CardType.Room, "Kitchen"));
        hPToken = new Token("t1");
        aPToken = new Token("t2");
        
    }

    /**
     * Test of getPlayerId method, of class Player.
     */
    @Test
    public void testGetPlayerId() {
        assertEquals(1, humanPlayer.getPlayerId());
        assertEquals(2, agentPlayer.getPlayerId());
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        assertEquals("hP", humanPlayer.getName());
        assertEquals("aP", agentPlayer.getName());
    }

    /**
     * Test of getHand method, of class Player.
     */
    @Test
    public void testGetHand() {
        assertEquals(null, humanPlayer.getHand());
        assertEquals(null, agentPlayer.getHand());
    }

    /**
     * Test of setHand method, of class Player.
     */
    @Test
    public void testSetHand() {
        humanPlayer.setHand(hPHand);
        agentPlayer.setHand(aPHand);
        assertEquals("Miss Scarlett", humanPlayer.getHand().get(0).getName());
        assertEquals("Kitchen", agentPlayer.getHand().get(0).getName());
    }

    /**
     * Test of getToken method, of class Player.
     */
    @Test
    public void testGetToken() {
        assertEquals(null, humanPlayer.getToken());
        assertEquals(null, agentPlayer.getToken());
    }

    /**
     * Test of setToken method, of class Player.
     */
    @Test
    public void testSetToken() {
        humanPlayer.setToken(hPToken);
        agentPlayer.setToken(aPToken);
        assertEquals("t1", humanPlayer.getToken().getName());
        assertEquals("t2", agentPlayer.getToken().getName());
    }

    /**
     * Test of moveToken method, of class Player.
     */
    @Test
    public void testMoveToken() {
        fail("Method not supported yet");
    }

    /**
     * Test of isAgent method, of class Player.
     */
    @Test
    public void testIsAgent() {
        assertEquals(false, humanPlayer.isAgent());
        assertEquals(true, agentPlayer.isAgent());
    }
    
}
