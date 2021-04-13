/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
        //Get longest string to reference space between label and checkbox(Colonel Mustard)
        int longestStringLength = characterNames[1].length();

        VBox suspectList = new VBox();
        Label suspects = new Label("------Suspects------");
        suspectList.getChildren().add(suspects);
        suspects.setScaleX(1);

        for (String characterName : characterNames) {
            int spaceCountAfterLabel = (longestStringLength - characterName.length());

            Label suspectText = new Label(characterName);
            CheckBox checkBox = new CheckBox();
            HBox suspectCheckListLine = new HBox(checkBox, suspectText);
            //Sets position 
            suspectCheckListLine.setAlignment(Pos.TOP_LEFT);
            suspectList.setAlignment(Pos.TOP_LEFT);
            //Spacings 
            suspectCheckListLine.setSpacing(10);

            suspectList.getChildren().add(suspectCheckListLine);

        }
        //For rooms
        VBox roomList = new VBox();
        Label rooms = new Label("------Rooms------");
        roomList.getChildren().add(rooms);
        rooms.setScaleX(1);
        //Iterate and throw all
        for (String roomName : roomNames) {
            Label roomLabel = new Label(roomName);
            CheckBox roomCheckBox = new CheckBox();
            HBox roomCheckListLine = new HBox(roomCheckBox, roomLabel);
            roomCheckListLine.setSpacing(10);
            //Sets position 
            roomCheckListLine.setAlignment(Pos.TOP_LEFT);
            roomList.setAlignment(Pos.TOP_LEFT);

            roomList.getChildren().add(roomCheckListLine);
        }

        //For Weapons
        VBox weaponList = new VBox();
        Label weapons = new Label("------Weapons------");
        roomList.getChildren().add(weapons);
        weapons.setScaleX(1);
        //Iterate and throw all
        for (String weapon : weaponNames) {
            Label roomLabel = new Label(weapon);
            CheckBox weaponCheckBox = new CheckBox();
            HBox weaponCheckListLine = new HBox(weaponCheckBox, roomLabel);

            weaponCheckListLine.setSpacing(10);
            //Sets position 
            weaponCheckListLine.setAlignment(Pos.TOP_LEFT);
            weaponList.setAlignment(Pos.TOP_LEFT);

            weaponList.getChildren().add(weaponCheckListLine);
        }
        VBox completeCheckList = new VBox(suspectList, roomList, weaponList);

        //NOTES AREA
        TextArea detectiveNotes = new TextArea("Please write notes here!!!");
        detectiveNotes.setWrapText(true);
        detectiveNotes.setPrefSize(200, 500);
        Pane detectiveNotesPane = new Pane(detectiveNotes);

        HBox detectiveCheckList = new HBox(completeCheckList, detectiveNotesPane);

        Scene scene = new Scene(detectiveCheckList);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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
