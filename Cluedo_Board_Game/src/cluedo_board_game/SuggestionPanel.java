/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Anilz
 */
public class SuggestionPanel extends Application {

    private String suggestedSuspect;
    private String suggestedRoom;
    private String suggestedWeapon;

    String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    String[] rooms = {"Bathroom", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Gamesroom", "Library"};
    String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};

    @Override
    public void start(Stage primaryStage) {
        Label playerSuggestext = new Label("Player is making suggestion!");

        //For Suspect
        Text suspectText = new Text("Suspect :");
        ComboBox suspectBox = new ComboBox(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectText, suspectBox);
        suspectSelection.setSpacing(50);

        //For Room
        Text roomText = new Text("Room :   ");
        ComboBox roomBox = new ComboBox(FXCollections.observableArrayList(rooms));
        HBox roomSelection = new HBox(roomText, roomBox);
        roomSelection.setSpacing(50);

        //For Weapon
        Text weaponText = new Text("Weapon :");
        ComboBox weaponBox = new ComboBox(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponText, weaponBox);
        weaponSelection.setSpacing(45);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Assign to fields
                suggestedSuspect = (String) suspectBox.getValue();                
                suggestedRoom = (String) roomBox.getValue();
                suggestedWeapon = (String) weaponBox.getValue();
                
                //Prints the fields
                System.out.println(suggestedSuspect);
                System.out.println(suggestedRoom);
                System.out.println(suggestedWeapon);
                
            }
        });
        VBox root = new VBox();
        root.getChildren().addAll(playerSuggestext, suspectSelection, roomSelection, weaponSelection, submitButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(25);

        Scene scene = new Scene(root, 330, 300);

        primaryStage.setTitle("Cluedo-Suggest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
