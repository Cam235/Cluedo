package cluedo_board_game;

import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author Mazon
 */
public interface PopUpMenuInterface {
    public void start(Stage primaryStage) throws IOException;
    public boolean getRoomLocked();
    public void setRoomLocked();
    public void onClick();
}
