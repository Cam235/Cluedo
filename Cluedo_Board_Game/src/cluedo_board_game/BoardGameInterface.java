package cluedo_board_game;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mazon
 */
public interface BoardGameInterface {
    public VBox setUpBoard();
    public void spawnTokenInRoom(Pawn pawn, Room room);
    public void movePawn(Pawn pawn, int x, int y);
    public void positionUpdateAI();
    public void positionUpdatePlayer();
    public void updateView();
    public void start(Stage primaryStage);
}
