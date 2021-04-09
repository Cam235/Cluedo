/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
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
    String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    //String[] rooms = {"Bathroom", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Gamesroom", "Library"};

    Button submitButton; //The submit button of th game
    Button postSuggestionButton; //Respondent player showing card
    ComboBox<String> postSuggestionCombobox; //holds respondent players possible cards to suggest

    public VBox createSuggestionContent(String roomName) {
        Label playerSuggestext = new Label("Player is making suggestion!");

        //For Room--Static , will display the name room entered
        suggestedRoom = roomName;
        Label roomLabel = new Label("Room :   ");
        //ComboBox roomBox = new ComboBox(FXCollections.observableArrayList(rooms));
        Text roomText = new Text(suggestedRoom);
        HBox roomSelection = new HBox(roomLabel, roomText);
        roomSelection.setSpacing(50);

        //For Suspect
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);
        suspectBox.setOnAction(e -> suggestedSuspect = (String) suspectBox.getValue());

        //For Weapon
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);
        weaponBox.setOnAction(e -> suggestedWeapon = (String) weaponBox.getValue());
        // A submit button
        submitButton = new Button("Submit");

        VBox suggestionContent = new VBox();
        suggestionContent.getChildren().addAll(playerSuggestext, roomSelection, suspectSelection, weaponSelection, submitButton);
        suggestionContent.setAlignment(Pos.CENTER);
        suggestionContent.setSpacing(25);

        return suggestionContent;
    }

    public Parent createPostSuggestionContent(String responderName, ArrayList<String>suggestedCardPossessions ) {
        //Post suggestion panel is created after suggestion panel is submitted properly
        HBox postSuggestionContent = new HBox();
        Label postSuggestionLabel = new Label(responderName + " shows you :");
        postSuggestionCombobox = new ComboBox<>(FXCollections.observableArrayList(suggestedCardPossessions));
        postSuggestionButton = new Button("Show Card");
        postSuggestionContent.getChildren().addAll(postSuggestionLabel, postSuggestionCombobox, postSuggestionButton);
        postSuggestionContent.setSpacing(20);
        return postSuggestionContent;
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
