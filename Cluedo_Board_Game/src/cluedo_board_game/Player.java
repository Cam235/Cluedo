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
 * @version 4.0
 */
public class Player {
    
    protected int playerId; //uniquely identifies a player
    protected String name; //display name for player, doesn't have to be unique
    protected ArrayList<Card> hand; //the players hand of cards
    protected Token token; //represents the players token
    protected Boolean isPlaying; //represents whether the player is currently playing
    protected String mostRecentlySuggestedRoom = ""; //the players last suggested room, used to disable successive suggestions
    protected HashMap<String, Boolean> detectiveCard; //represents the detective card of the player
    protected String detectiveNotes; // represents detective Notes of player
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

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    
    public void moveToken(Tile tile) {
        throw new UnsupportedOperationException("Unsupported Player class for move token");
    }
    
    
    public Boolean isAgent(){
        return null;
    }

    public Boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    public Integer[] getMove(int x, int y){
        throw new UnsupportedOperationException("Unsupported Player class for moving token");
    }

    /**
     * update the players detective card with a given string key k and pair value
     * v
     * @param k
     * @param v 
     */
    public void updateDetectiveCard(String k, Boolean v){
        detectiveCard.put(k, v);
    }
    
    public HashMap<String, Boolean> getDetectiveCard() {
        return detectiveCard;
    }

    public void setDetectiveCard(HashMap<String, Boolean> detectiveCard) {
        this.detectiveCard = detectiveCard;
    }

    public String getDetectiveNotes() {
        return detectiveNotes;
    }

    public void setDetectiveNotes(String detectiveNotes) {
        this.detectiveNotes = detectiveNotes;
    }

    public String getMostRecentlySuggestedRoom() {
        return mostRecentlySuggestedRoom;
    }

    public void setMostRecentlySuggestedRoom(String mostRecentlySuggestedRoom) {
        this.mostRecentlySuggestedRoom = mostRecentlySuggestedRoom;
    }
    
    public ArrayList<String> getUnseenCards() {
        throw new UnsupportedOperationException("Unsupported Player class for detective card operations");
    }
    
    public void markHandAsSeen() {
        throw new UnsupportedOperationException("Unsupported Player class for detective card operations");
    }
    
    public String[] getAccusation(String[] characters, String[] rooms, String[] weapons) {
        throw new UnsupportedOperationException("Unsupported Player class for suggestions");
    }
    
    public String[] getSuggestion(String[] characters, String[] rooms, String[] weapons) {
        throw new UnsupportedOperationException("Unsupported Player class for suggestions");
    }
}
