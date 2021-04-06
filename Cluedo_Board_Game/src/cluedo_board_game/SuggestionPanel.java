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
public class SuggestionPanel {

    private String suggestedSuspect;
    private String suggestedRoom;
    private String suggestedWeapon;
    
    

    String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    String[] rooms = {"Bathroom", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Gamesroom", "Library"};
    String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    
    Button submitButton; // Gets the submit button

    public VBox createSuggestionContent() {
        Label playerSuggestext = new Label("Player is making suggestion!");

        //For Suspect
        Text suspectText = new Text("Suspect :");
        ComboBox suspectBox = new ComboBox(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectText, suspectBox);
        suspectSelection.setSpacing(50);
        suspectBox.setOnAction(e -> suggestedSuspect = (String) suspectBox.getValue());

        //For Room
        Text roomText = new Text("Room :   ");
        ComboBox roomBox = new ComboBox(FXCollections.observableArrayList(rooms));
        HBox roomSelection = new HBox(roomText, roomBox);
        roomSelection.setSpacing(50);
        roomBox.setOnAction(e -> suggestedRoom = (String) roomBox.getValue());

        //For Weapon
        Text weaponText = new Text("Weapon :");
        ComboBox weaponBox = new ComboBox(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponText, weaponBox);
        weaponSelection.setSpacing(45);
        weaponBox.setOnAction(e -> suggestedWeapon = (String) weaponBox.getValue());
        // A submit button
        submitButton = new Button("Submit");


        VBox suggestionContent = new VBox();
        suggestionContent.getChildren().addAll(playerSuggestext, suspectSelection, roomSelection, weaponSelection, submitButton);
        suggestionContent.setAlignment(Pos.CENTER);
        suggestionContent.setSpacing(25);

        return suggestionContent;
    }

    public String getSuggestedSuspect() {
        return suggestedSuspect;
    }

    public String getSuggestedRoom() {
        return suggestedRoom;
    }

    public String getSuggestedWeapon() {
        return suggestedWeapon;
    }
    

}
