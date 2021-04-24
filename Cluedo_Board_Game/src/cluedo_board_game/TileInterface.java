package cluedo_board_game;

/**
 *
 * @author Mazon
 */
public interface TileInterface {
    public int getColIndex();
    public int getRowIndex();
    public boolean isOccupied();
    public void setOccupied(boolean isOccupied);
    public boolean isWall();
    public void setWall(boolean isWall);
    public boolean isDoor();
    public void setDoor(boolean isDoor);
}
