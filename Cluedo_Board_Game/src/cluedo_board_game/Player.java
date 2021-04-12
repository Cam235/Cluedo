/*
 * 
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

/**
 * Represents the Players of the board game
 * @author cwood
 * @version 1.0
 */
public class Player implements PlayerInterface {
    
    protected int playerId; //uniquely identifies a player
    protected String name; //display name for player, doesn't have to be unique
    protected ArrayList<Card> hand; //the players hand of cards
    protected Token token; //represents the players token
    protected Boolean isPlaying; //represents whether the player is currently playing
    protected Boolean isTurn; //represents whether it is currently the players turn
    protected HashMap<String, Pair<Boolean, String>> detectiveCard; //represents the detective card of the player
    
    /**
     * Constructor for objects of class Player
     * 
     * @param playerId a number which uniquely identifies a player during a game
     * @param name the display name for the player
     */
    public Player(int playerId, String name){
        this.playerId = playerId;
        this.name = name;
        this.token = null;
        isPlaying = true;
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

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public void moveToken(Tile tile) {
        throw new UnsupportedOperationException("Unsupported Player class for move token"); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Boolean isAgent(){
        return null;
    }

    public Boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public Boolean getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(Boolean isTurn) {
        this.isTurn = isTurn;
    }
    
    public Integer[] getMove(int x, int y){
        return null;
    }

    /**
     * update the players detective card with a given string key k and pair value
     * v
     * @param k
     * @param v 
     */
    public void updateDetectiveCard(String k, Pair<Boolean, String> v){
        detectiveCard.put(k, v);
    }
    
    public HashMap<String, Pair<Boolean, String>> getDetectiveCard() {
        return detectiveCard;
    }

    public void setDetectiveCard(HashMap<String, Pair<Boolean, String>> detectiveCard) {
        this.detectiveCard = detectiveCard;
    }
    
}
