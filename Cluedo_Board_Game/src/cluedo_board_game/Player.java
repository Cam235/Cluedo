/*
 * 
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the Players of the board game
 *
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
    protected HashMap<String, Boolean> checklist; //represents the detective checklist of the player
    protected String detectiveNotes; // represents detective Notes of player
    protected ArrayList<Tile> previousPath; //represents the moves a player has taken in a given turn

    /**
     * Constructor for objects of class Player
     *
     * @param playerId a number which uniquely identifies a player during a game
     * @param name the display name for the player
     */
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.token = null;
        isPlaying = true;
    }

    /**
     * Gets player Id
     *
     * @return playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Gets player name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets player's hand
     *
     * @return hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Sets player's hand
     *
     * @param hand
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Gets player's token
     *
     * @return token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets player's token
     *
     * @param token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * For moving token,implemented in human and agent player
     *
     * @param tile
     */
    public void moveToken(Tile tile) {
        throw new UnsupportedOperationException("Unsupported Player class for move token");
    }

    /**
     * To return whether player is agent or human. Implemented in subclasses
     *
     * @return null
     */
    public Boolean isAgent() {
        return null;
    }

    /**
     * Gets whether the player is actively playing
     *
     * @return isPlaying
     */
    public Boolean getIsPlaying() {
        return isPlaying;
    }

    /**
     * Sets whether the player is actively playing
     *
     * @param isPlaying
     */
    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    /**
     * For the token movement. Implemented in subclasses
     *
     * @param x
     * @param y
     * @return
     */
    public Integer[] getMove(int x, int y) {
        throw new UnsupportedOperationException("Unsupported Player class for moving token");
    }

    /**
     * update the players detective checklist with a given string key k and pair
     * value v
     *
     * @param k
     * @param v
     */
    public void updateDetectiveChecklist(String k, Boolean v) {
        checklist.put(k, v);
    }

    /**
     * Gets the detective checklist
     *
     * @return checklist
     */
    public HashMap<String, Boolean> getChecklist() {
        return checklist;
    }

    /**
     * Sets the detective checklist
     *
     * @param checklist
     */
    public void setChecklist(HashMap<String, Boolean> checklist) {
        this.checklist = checklist;
    }

    /**
     * Gets the detective notes
     *
     * @return detectiveNotes
     */
    public String getDetectiveNotes() {
        return detectiveNotes;
    }

    /**
     * Sets the detective notes
     *
     * @param detectiveNotes
     */
    public void setDetectiveNotes(String detectiveNotes) {
        this.detectiveNotes = detectiveNotes;
    }

    /**
     * Gets the name of most recently suggested room
     *
     * @return mostRecentlySuggestedRoom
     */
    public String getMostRecentlySuggestedRoom() {
        return mostRecentlySuggestedRoom;
    }

    /**
     * Sets the name of most recently suggested room
     *
     * @param mostRecentlySuggestedRoom
     */
    public void setMostRecentlySuggestedRoom(String mostRecentlySuggestedRoom) {
        this.mostRecentlySuggestedRoom = mostRecentlySuggestedRoom;
    }

    /**
     * Returns names of unseen cards, implementation in subclasses
     *
     * @return
     */
    public ArrayList<String> getUnseenCards() {
        throw new UnsupportedOperationException("Unsupported Player class for detective card operations");
    }

    /**
     * Mark hand as seen, implementation in subclasses
     */
    public void markHandAsSeen() {
        throw new UnsupportedOperationException("Unsupported Player class for detective card operations");
    }

    /**
     * Gets Accusation , implementation in subclasses
     * @param characters
     * @param rooms
     * @param weapons
     * @return
     */
    public String[] getAccusation(String[] characters, String[] rooms, String[] weapons) {
        throw new UnsupportedOperationException("Unsupported Player class for suggestions");
    }
    
    /**
     * Gets suggestion , implementation in subclasses
     * @param characters
     * @param rooms
     * @param weapons
     * @return 
     */
    public String[] getSuggestion(String[] characters, String[] rooms, String[] weapons) {
        throw new UnsupportedOperationException("Unsupported Player class for suggestions");
    }

    /**
     * Getter for previous path field
     * @return 
     */
    public ArrayList<Tile> getPreviousPath() {
        return previousPath;
    }

    /**
     * Setter for previous path field
     * @param previousPath 
     */
    public void setPreviousPath(ArrayList<Tile> previousPath) {
        this.previousPath = previousPath;
    }
}
