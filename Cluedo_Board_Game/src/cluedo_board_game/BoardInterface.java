package cluedo_board_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mazon
 */
public interface BoardInterface {
    public Tile[][] getTileMap();
    public int getColumns();
    public int getRows();
    public ArrayList<Room> getRooms();
    public void initializeRoom(String name, ArrayList<Tile> roomSpace, 
            ArrayList<Tile> roomDoors);
    @Override
    public String toString();
    public void setCardDistributor(List<Card> cardList);
    public void addPlayers(List<String> playerNames, List<Character> playerTypes);
    public ArrayList<Player> getPlayerList();
    public void incrementCurrentPlayer();
    
    // Moved from BoardGUI class
    public void positionUpdateAI();
    public void positionUpdatePlayer();
    
    // New methods
    public void orderPlayerList(); // Orders the player list such that it's ordered in priority of the characters as said in the requirements: 
                                   // Miss Scarlett: Red (top right), 
                                   // Col Mustard: Yellow (right top), 
                                   // Mrs White: White (bottom right), 
                                   // Rev Green: Green (bottom left)
                                   // Mrs Peacock: Blue (left bottom)
                                   // Prof Plum: Purple (left top)
    public void initializeWeapons(); // Initializes each weapon to be in each room, i.e. have their own tile with a room set, 
                                     // no two weapons MUST not occupy the same room initially.
                                     // Tiles for each weapon can be set my iterating through the rooms' roomSpace attribute and randomly assign the tile.

}
