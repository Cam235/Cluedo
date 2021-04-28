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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Suggestion panel class that includes displays and fields to fulfil suggestion
 * functions of the game
 *
 * @author Anilz
 */
public class SuggestionPanel {

    //Suggestion fields
    private String suggestedSuspectName;
    private String suggestedRoomName;
    private String suggestedWeaponName;

    //String arrays of suspect and weapon names
    private String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};

    //The submit button for suggestion
    private Button submitButton;

    
    /**
     * Displays the content for user to make suggestion. Includes room field and
     * 2 ComboBox to suggest suspect and weapon
     *
     * @param roomName current room of player's token is in
     * @param suggestingPlayer current player who is making suggestion
     * @return suggestionContent
     */
    public VBox createSuggestionContent(String roomName, String suggestingPlayer) {

        Label playerSuggestext = new Label("Player " + suggestingPlayer + " is making suggestion!");
        //Label and text of suggested room,which current player's token is in
        suggestedRoomName = roomName;
        Label roomLabel = new Label("Room :   ");
        Text roomText = new Text(suggestedRoomName);
        HBox roomSelection = new HBox(roomLabel, roomText);
        roomSelection.setSpacing(50);
        //Suggested room card image
        Image roomImage = new Image("/RoomCards/" + roomName + ".jpg", 130, 200, false, false);
        ImageView roomCardView = new ImageView(roomImage);

        //Label and combobox to suggest suspect
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);
        //Current suspect image,which is unknown
        Image suspectImage = new Image("/CharacterCards/unknownCard.png", 130, 200, false, false);
        ImageView suspectCardView = new ImageView(suspectImage);
        //Assigns suggested suspect Name and image values once selected in combobox
        suspectBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                suggestedSuspectName = (String) suspectBox.getValue();
                suspectCardView.setImage(new Image("/CharacterCards/" + suggestedSuspectName + ".jpg", 130, 200, false, false));
            }
        });

        //Label and combobox to suggest weapon
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);
        //Current weapon image,which is unknown
        Image weaponImage = new Image("/weaponCards/unknownCard.png", 130, 200, false, false);
        ImageView weaponCardView = new ImageView(weaponImage);
        //Assigns suggested weapon Name and image values once selected in combobox
        weaponBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                suggestedWeaponName = (String) weaponBox.getValue();
                weaponCardView.setImage(new Image("/weaponCards/" + suggestedWeaponName + ".jpg", 130, 200, false, false));
            }
        });

        submitButton = new Button("Submit");// A submit button
        //Creates suggestion Content display ,that user will see
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

    /**
     * A ChoiceDialog appears when suggestion is made and some human player
     * possess 1 or more suggested card, and has to show
     *
     * @param responderName first player name after current suggesting player,
     * who has suggested card
     * @param suggestedCardPossessions All card names which are suggested and
     * the responder possess
     * @return postSuggestionContent
     */
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

    /**
     * An alert appears when responder shows a card This includes name and image
     * of suggested and possessed card
     *
     * @param responderPlayer
     * @param responseCardName
     * @return postSuggestionAlert
     */
    public Alert createPostHumanSuggestionAlert(String responderPlayer, String responseCardName) {
        Alert postSuggestionAlert = new Alert(Alert.AlertType.INFORMATION);
        postSuggestionAlert.setTitle("Card is Shown!");
        postSuggestionAlert.setHeaderText("Player " + responderPlayer + " shows you a " + responseCardName + " card!");
        postSuggestionAlert.setContentText("Please make accusation or end your turn!");
        //Gets the correct image view of suggested card through try carch statements!
        try {
            ImageView ShownCardIsWeapon = new ImageView(new Image("/weaponCards/" + responseCardName + ".jpg", 130, 200, false, false));
            postSuggestionAlert.setGraphic(ShownCardIsWeapon);
        } catch (IllegalArgumentException e) {
            try {
                ImageView ShownCardIsSuspect = new ImageView(new Image("/CharacterCards/" + responseCardName + ".jpg", 130, 200, false, false));
                postSuggestionAlert.setGraphic(ShownCardIsSuspect);
            } catch (IllegalArgumentException t) {
                ImageView ShownCardIsSuspect = new ImageView(new Image("/RoomCards/" + responseCardName + ".jpg", 130, 200, false, false));
                postSuggestionAlert.setGraphic(ShownCardIsSuspect);
            }
        }
        return postSuggestionAlert;
    }

    /**
     * Creates suggestion and post suggestion content for agent
     *
     * @param suggestingPlayer
     * @param respondingPlayer
     * @param suggestedCharacter
     * @param suggestedRoom
     * @param suggestedWeapon
     * @return postAgentSuggestionAlert
     */
    public Alert createPostAgentSuggestionAlert(String suggestingPlayer, String respondingPlayer,
            String suggestedCharacter, String suggestedRoom, String suggestedWeapon) {
        Alert postAgentSuggestionAlert = new Alert(Alert.AlertType.INFORMATION);
        postAgentSuggestionAlert.setTitle("Agent " + suggestingPlayer + " Made A Suggestion!");
        postAgentSuggestionAlert.setHeaderText("Agent " + suggestingPlayer + " suggested it was " + suggestedCharacter + " in the \n"
                + suggestedRoom + " with a " + suggestedWeapon + ", " + respondingPlayer + " shows them a card");
        postAgentSuggestionAlert.setContentText("Agent " + suggestingPlayer + " ends their turn");
        return postAgentSuggestionAlert;
    }

    /**
     * Alert appears if suggested card is not found at any players hand,
     * indicating no other players possess the card is not found!
     *
     * @param isAgent
     * @param suggestingPlayer
     * @param suggestedCharacter
     * @param suggestedRoom
     * @param suggestedWeapon
     * @return NoPlayerHaveCardAlert
     */
    public Alert createCardNotFoundAlert(boolean isAgent, String suggestingPlayer,
            String suggestedCharacter, String suggestedRoom, String suggestedWeapon) {
        Alert NoPlayerHaveCardAlert = new Alert(Alert.AlertType.ERROR);
        NoPlayerHaveCardAlert.setHeaderText("Other players do not have suggested cards");
        NoPlayerHaveCardAlert.setTitle("Cards not found");
        if (isAgent) {
            NoPlayerHaveCardAlert.setContentText("Agent " + suggestingPlayer + " suggested it was " + suggestedCharacter + " in the \n"
                    + suggestedRoom + " with a " + suggestedWeapon + ", no one has a card to show");
        } else {
            NoPlayerHaveCardAlert.setContentText("Please make accusation or end your turn!");
        }
        return NoPlayerHaveCardAlert;

    }

    /**
     * Gets suggested suspect name
     *
     * @return suggestedSuspectName
     */
    public String getSuggestedSuspectName() {
        return suggestedSuspectName;
    }

    /**
     * Gets suggested room name
     *
     * @return suggestedRoomName
     */
    public String getSuggestedRoomName() {
        return suggestedRoomName;
    }

    /**
     * Gets suggested weapon name
     *
     * @return suggestedWeaponName
     */
    public String getSuggestedWeaponName() {
        return suggestedWeaponName;
    }

    /**
     * Gets the submit button
     *
     * @return submitButton
     */
    public Button getSubmitButton() {
        return submitButton;
    }

}
