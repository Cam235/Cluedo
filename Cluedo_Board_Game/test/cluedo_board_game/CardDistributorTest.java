/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cwood
 */
public class CardDistributorTest {

    private CardDistributor cDInstance;
    
    public CardDistributorTest() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Card> testCardList = new ArrayList<>();
        testCardList.add(new Card(CardType.Person, "Mrs White"));
        testCardList.add(new Card(CardType.Person, "Miss Scarlett"));
        testCardList.add(new Card(CardType.Weapon, "Candle"));
        testCardList.add(new Card(CardType.Weapon, "Pipe"));
        testCardList.add(new Card(CardType.Room, "Lounge"));
        testCardList.add(new Card(CardType.Room, "Kitchen"));
        cDInstance = new CardDistributor(testCardList);
    }

    /**
     * Test of setEnvelope method, of class CardDistributor.
     */
    @Test
    public void testSetEnvelope() {
        cDInstance.setEnvelope();
        assertTrue(cDInstance.getMurderer().getName().equals("Mrs White") || cDInstance.getMurderer().getName().equals("Miss Scarlett"));
        assertTrue(cDInstance.getMurderWeapon().getName().equals("Candle") || cDInstance.getMurderWeapon().getName().equals("Pipe"));
        assertTrue(cDInstance.getMurderRoom().getName().equals("Lounge") || cDInstance.getMurderRoom().getName().equals("Kitchen"));
    }

    /**
     * Test of shuffleCards method, of class CardDistributor.
     */
    @Test
    public void testShuffleCards() {
        //run multiple times to see shuffle working
        cDInstance.shuffleCards();
        for (Card c:cDInstance.getFinalDeck()){
            System.out.print(c.getName() + "  ");
        }
    }
    
    
    
}
