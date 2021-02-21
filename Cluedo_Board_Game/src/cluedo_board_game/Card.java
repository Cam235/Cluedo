/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

/**
 *
 * @author 
 */
public class Card {
    
    private CardType type;
    private String name; 
    
    public Card(CardType cardType, String cardName){
        type = cardType;
        name = cardName;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public CardType getType(){
        return type;
    }
    
    public void setType(CardType newType){
        type = newType;
    }

}