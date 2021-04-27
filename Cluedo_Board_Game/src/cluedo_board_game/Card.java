/*
 *
 */
package cluedo_board_game;

import javafx.scene.image.Image;

/**
 * Represents cards in the board game which are handed to players and put in
 * the envelope
 * 
 * @author cwood 
 * @version 1.0
 */
public class Card{
    
    private CardType type; //determines card type
    private String name;   //display name of card
    private Image cardImage; // for the image of the card
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
    
    
    public String getName(){
        return name;
    }
    
    /**
     * @param newName 
     */
    
    public void setName(String newName){
        name = newName;
    }
    
    
    public CardType getType(){
        return type;
    }
    
    /**
     * @param newType 
     */
    
    public void setType(CardType newType){
        type = newType;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }
    
    

}
