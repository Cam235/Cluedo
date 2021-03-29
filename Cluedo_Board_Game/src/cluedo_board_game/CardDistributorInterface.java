package cluedo_board_game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mazon
 */
public interface CardDistributorInterface {
    public void setEnvelope();
    public void shuffleCards();
    public Card getMurderer();
    public Card getMurderWeapon();
    public Card getMurderRoom();
    public ArrayList<Card> getFinalDeck();
    public void dealCards(ArrayList<Player> pList);
}
