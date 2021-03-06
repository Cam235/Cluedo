package cluedo_board_game;

import java.util.ArrayList;

/**
 *
 * @author Mazon
 */
public interface RoomInterface {
    public String getRoomName();
    public ArrayList<Tile> getRoomSpace();
    public ArrayList<Tile> getDoorTiles();
    public boolean checkTileInRoom(Tile tile);
    public void addRoomDoor(Tile doorTile);
}
