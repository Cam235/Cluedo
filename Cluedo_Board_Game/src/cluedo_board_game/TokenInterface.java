package cluedo_board_game;

/**
 *
 * @author Mazon
 */
public interface TokenInterface {
    public String getName();
    public void setName(String name);
    public Tile getTokenLocation();
    public void setTokenLocation(Tile pawnLocation);
    //public boolean isAgent();
    //public void setAgent(boolean isAgent);
}