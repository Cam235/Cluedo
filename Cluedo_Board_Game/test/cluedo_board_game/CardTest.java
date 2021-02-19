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
 * @author 
 */
public class CardTest {
    
    public CardTest() {
    }
    
    private Card cardInstance1;
    private Card cardInstance2;
    private Card cardInstance3;
    
    @Before
    public void setUp() {
        cardInstance1 = new Card(CardType.Person, "Miss Scarlett");
        cardInstance2 = new Card(CardType.Room, "Kitchen");
        cardInstance3 = new Card(CardType.Weapon, "Pipe");
    }

    /**
     * Test of getName method, of class Card.
     */
    @Test
    public void testGetName() {
        assertEquals("Miss Scarlett", cardInstance1.getName());
        assertEquals("Kitchen", cardInstance2.getName());
        assertEquals("Pipe", cardInstance3.getName());
    }

    /**
     * Test of setName method, of class Card.
     */
    @Test
    public void testSetName() {
        cardInstance1.setName("Mrs White");
        cardInstance2.setName("Lounge");
        cardInstance3.setName("Candle");
        assertEquals("Mrs White", cardInstance1.getName());
        assertEquals("Lounge", cardInstance2.getName());
        assertEquals("Candle", cardInstance3.getName());
    }

    /**
     * Test of getType method, of class Card.
     */
    @Test
    public void testGetType() {
        assertEquals(CardType.Person, cardInstance1.getType());
        assertEquals(CardType.Room, cardInstance2.getType());
        assertEquals(CardType.Weapon, cardInstance3.getType());
    }

    /**
     * Test of setType method, of class Card.
     */
    @Test
    public void testSetType() {
        cardInstance1.setType(CardType.Room);
        cardInstance2.setType(CardType.Person);
        cardInstance3.setType(CardType.Weapon);
        assertEquals(CardType.Room, cardInstance1.getType());
        assertEquals(CardType.Person, cardInstance2.getType());
        assertEquals(CardType.Weapon, cardInstance3.getType());
    }
    
}
