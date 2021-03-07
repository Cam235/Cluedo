package cluedo_board_game;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mazon
 */
public interface BoardGUIInterface {
    public VBox setUpBoard();
    
    public void spawnTokenInRoom(Token token, Room room); 
    // Change to initializeTokens() - spawn all tokens onto the board
    
    // Move these to Board class
    public void positionUpdateAI();
    public void positionUpdatePlayer();
    ///
    
    // Move this to Player class (each player needs to move their token)
    public void moveToken(Token token, int x, int y);
    
    public void updateView();
    public void start(Stage primaryStage);
    
    //New methods
    public void selectCharacters(); // Allow user to select number of players, which characters and toggle if AI or not.
    public void displayCardList(); // Displays card list in front of current player, after done, use setter on Board object.
    
}
