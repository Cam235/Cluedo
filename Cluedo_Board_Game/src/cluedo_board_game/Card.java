/*
 *
 */
package cluedo_board_game;

/**
 * Represents cards in the board game which are handed to players and put in
 * the envelope
 * 
 * @author cwood 
 * @version 1.0
 */
public class Card implements CardInterface{
    
    private CardType type; //determines card type
    private String name;   //display name of card
    
    /**
     * Constructor for objects of class Card
     * 
     * @param cardType Card type enum, either Person, Weapon or Room
     * @param cardName Display name of the card
     */
    public Card(CardType cardType, String cardName){
        //initialise variables
        type = cardType;
        name = cardName;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    /**
     * @param newName 
     */
    @Override
    public void setName(String newName){
        name = newName;
    }
    
    @Override
    public CardType getType(){
        return type;
    }
    
    /**
     * @param newType 
     */
    @Override
    public void setType(CardType newType){
        type = newType;
    }

}
