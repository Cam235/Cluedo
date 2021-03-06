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
    public Token initializeToken(String tokenName, int x, int y);
    public void initializeRoom(String name, ArrayList<Tile> roomSpace, 
            ArrayList<Tile> roomDoors);
    @Override
    public String toString();
    public void setCardDistributor(List<Card> cardList);
    public void addPlayers(List<String> playerNames);
    public Map<Integer, Player> getPlayerList();
}
