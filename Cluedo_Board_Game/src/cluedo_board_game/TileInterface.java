package cluedo_board_game;

/**
 *
 * @author Mazon
 */
public interface TileInterface {
    public int getColIndex();
    public int getRowIndex();
    public boolean IsOccupied();
    public void setOccupied(boolean isOccupied);
    public boolean getIsWall();
    public void setWall(boolean isWall);
    public boolean getIsDoor();
    public void setDoor(boolean isDoor);
}
