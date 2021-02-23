/*
 * 
 */
package cluedo_board_game;

import java.util.ArrayList;

/**
 * Represents the Players of the board game
 * @author cwood
 * @version 1.0
 */
public class Player {
    
    private int playerId; //uniquely identifies a player
    private String name; //display name for player, doesn't have to be unique
    private ArrayList<Card> hand; //the players hand of cards
    
    /**
     * Constructor for objects of class Player
     * 
     * @param playerId a number which uniquely identifies a player during a game
     * @param name the display name for the player
     */
    public Player(int playerId, String name){
        this.playerId = playerId;
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
      
}
