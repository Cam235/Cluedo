package cluedo_board_game;

import java.util.ArrayList;

/**
 *
 * @author Mazon
 */
public interface PlayerInterface {
    public int getPlayerId();
    public String getName();
    public ArrayList<Card> getHand();
    public void setHand(ArrayList<Card> hand);
    
    // Moved from Board class
    public void moveToken(Tile tile);
    public Boolean isAgent();
}
