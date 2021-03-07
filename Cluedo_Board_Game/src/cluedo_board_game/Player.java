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
public class Player implements PlayerInterface {
    
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

    @Override
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void moveToken(Token token, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
