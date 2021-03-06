package cluedo_board_game;

import javafx.scene.layout.VBox;

/**
 *
 * @author Mazon
 */
public interface DiceRollerInterface {
    public VBox createContent();
    public boolean isDiceRolled();
    public void setDiceRolled(boolean diceRolled);
    public void enableDiceRollerButton();
    public int getDiceTotal();
}
