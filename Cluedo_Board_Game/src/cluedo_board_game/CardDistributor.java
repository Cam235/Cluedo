/*
 * 
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Used to distribute cards among players and the envelope 
 * 
 * @author cwood
 * @version 1.01
 */
public class CardDistributor implements CardDistributorInterface{
    //Array lists for person, weapon and room cards
    private ArrayList<Card> pCards;
    private ArrayList<Card> wCards;
    private ArrayList<Card> rCards;
    
    //map for envelope of murder cards
    private Map<String, Card> envelope;
    
    //Array list for cards that are handed to players
    private ArrayList<Card> finalDeck;
    
    /**
     * Constructor for objects of class CardDistributor
     * 
     * @param cards a list of the cards in the game to be distributed 
     */
    public CardDistributor(List<Card> cards){
        //intialise class variables
        pCards = new ArrayList<>();
        wCards = new ArrayList<>();
        rCards = new ArrayList<>();
        envelope = new HashMap<>();
        finalDeck = new ArrayList<>();
        
        //for each card in cards parameter add each card to corresponding list
        //ignores cards with invalid type
        for(Card c:cards){
            switch (c.getType()){
                case Person:
                    pCards.add(c);
                    break;
                case Weapon:
                    wCards.add(c);
                    break;
                case Room:
                    rCards.add(c);
                    break;
                default:
                    break;
            }          
        }
    }
    
    /**
     * Sets the envelope for the board game with one card from each of the three types
     */
    @Override
    public void setEnvelope(){
        //create new empty envelope
        envelope = new HashMap<>();
        //remove one random element each from pCards, wCards and rCards and add them to envelope
        Random randInt = new Random();
        int randomIndex = randInt.nextInt(pCards.size());
        envelope.put("Person", pCards.remove(randomIndex));
        randomIndex = randInt.nextInt(wCards.size());
        envelope.put("Weapon", wCards.remove(randomIndex));
        randomIndex = randInt.nextInt(rCards.size());
        envelope.put("Room", rCards.remove(randomIndex)); 
    }
    
    /**
     * Puts all the cards into one deck and shuffles them into a random order
     */
    @Override
    public void shuffleCards(){
        //add all elements from pCards, wCards and rCards to finalDeck
        finalDeck.addAll(pCards);
        finalDeck.addAll(wCards);
        finalDeck.addAll(rCards);
        
        //shuffle finalDeck
        Collections.shuffle(finalDeck);
    }
    
    @Override
    public Card getMurderer(){
        return envelope.get("Person");
    }
    
    @Override
    public Card getMurderWeapon(){
        return envelope.get("Weapon");
    }
    
    /**
     * getter for murder room
     * 
     * @return returns murder room
     */
    @Override
    public Card getMurderRoom(){
        return envelope.get("Room");
    }
    
    /*used only for testing*/
    @Override
    public ArrayList<Card> getFinalDeck(){
        return finalDeck;
    }
    
    /**
     * Deals the cards in the deck to a given list of players
     * 
     * @param pList
     */
    @Override
    public void dealCards(ArrayList<Player> pList){
        
        ArrayList<ArrayList<Card>> playerHands = new ArrayList<>(); //list of list for the hand of each player
        int i = 0; //incrementor
        
        pList.forEach((p) -> { //add a new list to player hands for each player
            if(p.getIsPlaying()){
                playerHands.add(new ArrayList<>());
            }
        });
        
        while(!finalDeck.isEmpty()){ //while there are still cards to deal
            playerHands.get(i).add(finalDeck.remove(0)); //remove a card from final deck and add it to a hand 
            //wrap i around to first player if i=last player index
            if(i == pList.size()-1){ 
                i=0;
            }
            //else increment i
            else{
                i++;
            }
        }
        
        //for each player deal them their hand
        for(int j=0; j < pList.size(); j++){
            if(pList.get(j).getIsPlaying()){
                pList.get(j).setHand(playerHands.remove(0));
            }
        }
    }
    
}
