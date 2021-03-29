package fxml_docs;

import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author Mazon
 */
public interface HUDInterface {
    public void start(Stage primaryStage) throws IOException; 
    public void setSuggestionLocked();
    public void setEndTurnLocked();
    public boolean getEndTurnLocked();
    public boolean getSuggestionLocked();
    public boolean getAccusationLocked();
    public void onClick();
}
