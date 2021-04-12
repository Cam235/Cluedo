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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Anilz
 */
public class AccusationPanel {

    String accusingPlayersName;

    private String accusedSuspect;
    private String accusedRoom;
    private String accusedWeapon;

    String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    String[] rooms = {"Bathroom", "Diningroom", "Kitchen", "Hallway","Office", "Ballroom", "Conservatory", "Gamesroom", "Library"};
    String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};

    Button submitButton; // The submit button of th game
    Button restartByWinButton;
    Button spectateButton;

    public Parent createAccusationContent(String accusingPlayerName) {
        Label playerAccusationText = new Label("Player "+ accusingPlayerName+" is making accusation!");

        //For Room       
        Label roomLabel = new Label("Room :   ");
        ComboBox<String> roomBox = new ComboBox<>(FXCollections.observableArrayList(rooms));
        HBox roomSelection = new HBox(roomLabel, roomBox);
        roomSelection.setSpacing(50);
        roomBox.setOnAction(e -> accusedRoom = (String) roomBox.getValue());

        //For Suspect
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);
        suspectBox.setOnAction(e -> accusedSuspect = (String) suspectBox.getValue());

        //For Weapon
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);
        weaponBox.setOnAction(e -> accusedWeapon = (String) weaponBox.getValue());
        // A submit button
        submitButton = new Button("Submit");

        VBox accusationContent = new VBox();
        accusationContent.getChildren().addAll(playerAccusationText, suspectSelection, roomSelection, weaponSelection, submitButton);
        accusationContent.setAlignment(Pos.CENTER);
        accusationContent.setSpacing(25);
        return accusationContent;
    }

    public Alert createCorrectAccusationContent(String wonPlayerName,String succeededEnvelopeCheck) {
        Alert correctAccusationAlert = new Alert(Alert.AlertType.INFORMATION);
        correctAccusationAlert.setTitle("Player "+wonPlayerName+" wins!!!");
        correctAccusationAlert.setHeaderText("Accusation Confirmed!!!");
        correctAccusationAlert.setContentText(succeededEnvelopeCheck);
        return correctAccusationAlert;
    }

    public Alert createFalseAccusationContent(String lostPlayerName, String failedEnvelopeCheck) {
        Alert falseAccusationAlert = new Alert(Alert.AlertType.ERROR);
        falseAccusationAlert.setTitle("Player " + lostPlayerName + " loses!!!");
        falseAccusationAlert.setHeaderText("Accusation Fails!!!");
        falseAccusationAlert.setContentText(failedEnvelopeCheck);
        return falseAccusationAlert;
    }

    public String getAccusedSuspectName() {
        return accusedSuspect;
    }

    public String getAccusedRoomName() {
        return accusedRoom;
    }

    public String getAccusedWeaponName() {
        return accusedWeapon;
    }

}
