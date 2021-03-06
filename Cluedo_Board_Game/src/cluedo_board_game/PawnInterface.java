package cluedo_board_game;

/**
 *
 * @author Mazon
 */
public interface PawnInterface {
    public String getName();
    public void setName(String name);
    public Tile getPawnLocation();
    public void setPawnLocation(Tile pawnLocation);
    public boolean isAgent();
    public void setAgent(boolean isAgent);
}
