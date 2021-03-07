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
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Seb
 */
public class PopUpMenu extends Application {
    
    private boolean isRoomLocked;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "./src/cluedo_board_game/popUpMenu.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
         
        // Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
         
        // Create the Scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Suggestion Menu");
        primaryStage.show();
    }
    
    public boolean getRoomLocked(){
        return isRoomLocked;
    }
    public void setRoomLocked(){
        isRoomLocked = true;
    }
    
    @FXML
    MenuButton suspectIconMenu;
    @FXML
    MenuButton roomIconMenu;
    @FXML
    MenuButton weaponIconMenu;
    
    
    public void onClick(){
        suspectIconMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("opens drop-down selection menu for suspects");
                
            }   
        });
        roomIconMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("opens drop-down selection menu for rooms");
            }   
        });  
        weaponIconMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("opens drop-down selection menu for weapons");
            }   
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
