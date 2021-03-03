/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Seb
 */
public class HUD extends Application {
    
    
    private Boolean isSuggestionLocked;
    private Boolean isAccusationLocked;
    private Boolean isEndTurnLocked;
    public PopUpMenu suggestionMenu;
    public PopUpMenu accusationMenu;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "C:\\Users\\Seb\\Documents\\NetBeansProjects\\Cluedo-main\\Cluedo_Board_Game\\src\\cluedo_board_game\\hud.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
         
        // Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
         
        // Create the Scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HUD");
        primaryStage.show();
    }
    
    @FXML
    Button suggestionButton;
    @FXML
    Button accusationButton;
    @FXML
    Button endTurnButton;
    
    public void setSuggestionLocked(){
        isSuggestionLocked = true;
    }
    public void setEndTurnLocked(){
        isEndTurnLocked = true;
    }
    public Boolean getEndTurnLocked(){
        return isEndTurnLocked;
    }
    public Boolean getSuggestionLocked(){
        return isSuggestionLocked;
    }
    public Boolean getAccusationLocked(){
        return isAccusationLocked;
    }
    
    
    
    public void onClick(){
        suggestionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("opens pop-up suggestion menu");
                
            }   
        });
        accusationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("opens pop-up accusation menu");
            }   
        });  
        endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Ends the turn.");
            }   
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
