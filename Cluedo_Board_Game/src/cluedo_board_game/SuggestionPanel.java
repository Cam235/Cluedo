/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Anilz
 */
public class SuggestionPanel {

    private String suggestedSuspect;
    private String suggestedRoom;
    private String suggestedWeapon;

    private String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    //String[] rooms = {"Bathroom", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Gamesroom", "Library"};

    private Button submitButton; //The submit button of th game

    public VBox createSuggestionContent(String roomName, String suggestingPlayer) {
        Label playerSuggestext = new Label("Player " + suggestingPlayer + " is making suggestion!");

        //For Room--Static , will display the name room entered
        suggestedRoom = roomName;
        Label roomLabel = new Label("Room :   ");
        //ComboBox roomBox = new ComboBox(FXCollections.observableArrayList(rooms));
        Text roomText = new Text(suggestedRoom);
        HBox roomSelection = new HBox(roomLabel, roomText);
        roomSelection.setSpacing(50);
        //Suggested room card image
        Image roomImage = new Image("/RoomCards/" + roomName + ".jpg", 130, 200, false, false);
        ImageView roomCardView = new ImageView(roomImage);

        //For Suspect
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);

        Image suspectImage = new Image("/CharacterCards/unknownCard.png", 130, 200, false, false);
        ImageView suspectCardView = new ImageView(suspectImage);

        suspectBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                suggestedSuspect = (String) suspectBox.getValue();
                suspectCardView.setImage(new Image("/CharacterCards/" + suggestedSuspect + ".jpg", 130, 200, false, false));
            }
        });

        //For Weapon
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);

        Image weaponImage = new Image("/weaponCards/unknownCard.png", 130, 200, false, false);
        ImageView weaponCardView = new ImageView(weaponImage);

        weaponBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                suggestedWeapon = (String) weaponBox.getValue();
                weaponCardView.setImage(new Image("/weaponCards/" + suggestedWeapon + ".jpg", 130, 200, false, false));
            }
        });

        // A submit button
        submitButton = new Button("Submit");
        HBox cardDisplays = new HBox(roomCardView, suspectCardView, weaponCardView);
        VBox suggestionContent = new VBox();
        suggestionContent.getChildren().addAll(playerSuggestext, roomSelection, suspectSelection, weaponSelection, submitButton, cardDisplays);
        suggestionContent.setAlignment(Pos.CENTER);
        suggestionContent.setSpacing(10);
        //Sets background 
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        suggestionContent.setBackground(bg);
        return suggestionContent;
    }

    public ChoiceDialog createSuggestionResponderContent(String responderName, ArrayList<String> suggestedCardPossessions) {
        // create a choice dialog
        ChoiceDialog<String> postSuggestionContent = new ChoiceDialog<>("", suggestedCardPossessions);
        postSuggestionContent.setTitle("Player " + responderName + " has a card to show!");
        postSuggestionContent.setHeaderText("Please choose a card to show!");
        //Removes Cancel button
        postSuggestionContent.getDialogPane().getButtonTypes().remove(1);
        postSuggestionContent.setContentText(responderName + " shows you");

        return postSuggestionContent;
    }

    public Alert createPostSuggestionAlert(String responderPlayer, String responseCardName) {
        Alert correctAccusationAlert = new Alert(Alert.AlertType.INFORMATION);
        correctAccusationAlert.setTitle("Card is Shown!");
        correctAccusationAlert.setHeaderText("Player " + responderPlayer + " shows you a " + responseCardName + " card!!!");
        correctAccusationAlert.setContentText("Please make accusation or end your turn!");
        //Gets the correct image view of suggested card!
        try {
            ImageView ShownCardIsWeapon = new ImageView(new Image("/weaponCards/" + responseCardName + ".jpg", 130, 200, false, false));
            correctAccusationAlert.setGraphic(ShownCardIsWeapon);
        } catch (IllegalArgumentException e) {
            try {
                ImageView ShownCardIsSuspect = new ImageView(new Image("/CharacterCards/" + responseCardName + ".jpg", 130, 200, false, false));
                correctAccusationAlert.setGraphic(ShownCardIsSuspect);
            } catch (IllegalArgumentException t) {
                ImageView ShownCardIsSuspect = new ImageView(new Image("/RoomCards/" + responseCardName + ".jpg", 130, 200, false, false));
                correctAccusationAlert.setGraphic(ShownCardIsSuspect);
            }
        }
        return correctAccusationAlert;
    }

    public Alert createCardNotFoundAlert() {
        Alert NoPlayerHaveCardAlert = new Alert(Alert.AlertType.ERROR);
        NoPlayerHaveCardAlert.setHeaderText("Other players do not have suggested cards");
        NoPlayerHaveCardAlert.setTitle("Cards not found");
        NoPlayerHaveCardAlert.setContentText("Please make accusation or end your turn!");
        return NoPlayerHaveCardAlert;
        
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

    public Button getSubmitButton() {
        return submitButton;
    }

    
}
