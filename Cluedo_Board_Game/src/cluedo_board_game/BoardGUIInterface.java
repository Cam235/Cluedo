package cluedo_board_game;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mazon
 */
public interface BoardGUIInterface {
    public VBox setUpBoard();
    
    
    // Move these to Board class
    //public void positionUpdateAI();
    public void setUpControls();
    ///
    
    public void updateView();
    public void start(Stage primaryStage);
    
    //New methods
    public void selectCharacters(); // Allow user to select number of players, which characters and toggle if AI or not.
    public void displayCardList(Stage pstage); // Displays card list of current player
    
}
