/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class DetectiveCardPanel extends Application {

    String[] characterNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};
    String[] weaponNames = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    String[] roomNames = {"Bathroom", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Gamesroom", "Library", "Hallway", "Office"};

    @Override
    public void start(Stage primaryStage) {
        VBox suspectList = new VBox();
        Label suspects = new Label("------Suspects------");        
        suspectList.getChildren().add(suspects);
        suspects.setScaleX(2);
        for (String characterName : characterNames) {
            Label suspectText = new Label(characterName);
            CheckBox checkBox = new CheckBox();
            TextField caseNotes = new TextField();
            caseNotes.setPrefWidth(400);
            suspectText.setAlignment(Pos.CENTER_LEFT);
            checkBox.setAlignment(Pos.CENTER);
            caseNotes.setAlignment(Pos.CENTER_LEFT);
            HBox checkListLine = new HBox();
            checkListLine.getChildren().addAll(suspectText, checkBox, caseNotes);
            checkListLine.setAlignment(Pos.TOP_CENTER);
            checkListLine.setSpacing(20);
            suspectList.getChildren().add(checkListLine);
            suspectList.setAlignment(Pos.CENTER);          
        }

        Scene scene = new Scene(suspectList);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Detective CheckList");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
