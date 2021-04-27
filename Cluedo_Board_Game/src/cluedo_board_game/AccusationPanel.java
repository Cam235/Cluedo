/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

/**
 *
 * @author Anilz
 */
public class AccusationPanel {

    //Accusation fields
    private String accusedSuspect;
    private String accusedRoom;
    private String accusedWeapon;

    // Murder cards which are represented in envelope
    private String envelopeSuspect;
    private String envelopeRoom;
    private String envelopeWeapon;

    //String arrays to be select cards for accusation
    private String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private String[] rooms = {"Lounge", "Diningroom", "Kitchen", "Hall", "Study", "Ballroom", "Conservatory", "Billiardroom", "Library"};
    private String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};

    private Button submitButton; // The submit button of th game

    private HBox cardsDisplay; // HBox to display accusation cards

    /**
     * Create the accusation content where human player can make accusation by
     * selecting weapon,room and suspect card
     *
     * @param accusingPlayerName name of player who accuses
     * @return accusationContent
     */
    public Parent createAccusationContent(String accusingPlayerName) {
        Label playerAccusationText = new Label("Player " + accusingPlayerName + " is making accusation!");

        //Select room of accusation
        Label roomLabel = new Label("Room :   ");
        ComboBox<String> roomBox = new ComboBox<>(FXCollections.observableArrayList(rooms));
        HBox roomSelection = new HBox(roomLabel, roomBox);
        roomSelection.setSpacing(50);
        //If room card is not selected , display unknown card,else ,display the selected(accused) room card 
        ImageView roomCardView = new ImageView(new Image("/RoomCards/unknownCard.png", 130, 200, false, false));
        roomBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedRoom = roomBox.getValue();
                roomCardView.setImage(new Image("/RoomCards/" + accusedRoom + ".jpg", 130, 200, false, false));
            }
        });

        //Select suspect of accusation
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);
        //If suspect card is not selected , display unknown card,else ,display the selected(accused) suspect card         
        ImageView suspectCardView = new ImageView(new Image("/CharacterCards/unknownCard.png", 130, 200, false, false));
        suspectBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedSuspect = suspectBox.getValue();
                suspectCardView.setImage(new Image("/CharacterCards/" + accusedSuspect + ".jpg", 130, 200, false, false));
            }
        });

        //Select weapon of accusation
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);
        //If weapon card is not selected , display unknown card,else ,display the selected(accused) weapon card 
        ImageView weaponCardView = new ImageView(new Image("/weaponCards/unknownCard.png", 130, 200, false, false));
        weaponBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedWeapon = weaponBox.getValue();
                weaponCardView.setImage(new Image("/weaponCards/" + accusedWeapon + ".jpg", 130, 200, false, false));
            }
        });
        //Add submit button
        submitButton = new Button("Submit");
        //Arrange the display of accusation content
        cardsDisplay = new HBox(suspectCardView, roomCardView, weaponCardView);
        VBox accusationContent = new VBox();
        accusationContent.getChildren().addAll(playerAccusationText, suspectSelection, roomSelection, weaponSelection, submitButton, cardsDisplay);
        accusationContent.setAlignment(Pos.CENTER);
        accusationContent.setSpacing(10);
        //Add Background color
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        accusationContent.setBackground(bg);
        //return to content
        return accusationContent;
    }

    /**
     * An alert displayed when a player makes a correct accusation
     *
     * @param wonPlayerName player's name who made correct accusation
     * @param envelopeSuspect
     * @param envelopeRoom
     * @param envelopeWeapon
     * @return correctAccusationAlert
     */
    public Alert createCorrectAccusationContent(String wonPlayerName, String envelopeSuspect, String envelopeRoom, String envelopeWeapon) {
        //Gets the name of envelope(murder) cards
        this.envelopeSuspect = envelopeSuspect;
        this.envelopeRoom = envelopeRoom;
        this.envelopeWeapon = envelopeWeapon;
        //Create alert display stating envelope(murder) cards
        Alert correctAccusationAlert = new Alert(Alert.AlertType.INFORMATION);
        correctAccusationAlert.setTitle("Player " + wonPlayerName + " wins!");
        correctAccusationAlert.setHeaderText("Accusation Correct!");
        correctAccusationAlert.setGraphic(cardsDisplay);
        correctAccusationAlert.setContentText(envelopeSuspect + " in the "
                + envelopeRoom + " with a "
                + envelopeWeapon + " is correct!\n\n"
                + "*** " + wonPlayerName + " accused correctly and won the game! ***\n");
        //Returns to alert
        return correctAccusationAlert;
    }

    /**
     *
     * @param lostPlayerName player's name who made false accusation
     * @param envelopeSuspect
     * @param envelopeRoom
     * @param envelopeWeapon
     * @param isAgent whether player who accused is agent or human
     * @return falseAccusationAlert
     */
    public Alert createFalseAccusationContent(String lostPlayerName, String envelopeSuspect, String envelopeRoom, String envelopeWeapon, boolean isAgent) {
        //Gets the name of envelope(murder) cards
        this.envelopeSuspect = envelopeSuspect;
        this.envelopeRoom = envelopeRoom;
        this.envelopeWeapon = envelopeWeapon;
        //Create alert display
        Alert falseAccusationAlert = new Alert(Alert.AlertType.ERROR);
        falseAccusationAlert.setTitle("Player " + lostPlayerName + " loses!");
        falseAccusationAlert.setHeaderText("Accusation Incorrect!");
        //if player is not agent show the accusation cards
        if (!isAgent) {
            ImageView murderer = new ImageView(new Image("/CharacterCards/" + envelopeSuspect + ".jpg", 130, 200, false, false));
            ImageView murderRoom = new ImageView(new Image("/RoomCards/" + envelopeRoom + ".jpg", 130, 200, false, false));
            ImageView murderWeapon = new ImageView(new Image("/weaponCards/" + envelopeWeapon + ".jpg", 130, 200, false, false));
            HBox murderCardsDisplay = new HBox(murderer, murderRoom, murderWeapon);
            falseAccusationAlert.setGraphic(murderCardsDisplay);
        }
        // returns to false accusation alert
        return falseAccusationAlert;
    }

    /**
     * Gets the name of accused suspect
     *
     * @return accusedSuspect
     */
    public String getAccusedSuspectName() {
        return accusedSuspect;
    }

    /**
     * Gets the name of accused room
     *
     * @return accusedRoom
     */
    public String getAccusedRoomName() {
        return accusedRoom;
    }

    /**
     * Gets the name of accused Weapon
     *
     * @return accusedWeapon
     */
    public String getAccusedWeaponName() {
        return accusedWeapon;
    }

    /**
     * Gets the name of envelope suspect (murderer)
     *
     * @return envelopeSuspect
     */
    public String getEnvelopeSuspect() {
        return envelopeSuspect;
    }

    /**
     * Sets the name of envelope suspect (murderer)
     *
     * @param envelopeSuspect
     */
    public void setEnvelopeSuspect(String envelopeSuspect) {
        this.envelopeSuspect = envelopeSuspect;
    }

    /**
     * Gets the name of envelope room (murder room)
     * @return envelopeRoom
     */
    public String getEnvelopeRoom() {
        return envelopeRoom;
    }

    /**
     * Sets the name of envelope room (murder room)
     * @param envelopeRoom
     */
    public void setEnvelopeRoom(String envelopeRoom) {
        this.envelopeRoom = envelopeRoom;
    }

    /**
     * Gets the name of envelope weapon (murder weapon)
     * @return envelopeWeapon
     */
    public String getEnvelopeWeapon() {
        return envelopeWeapon;
    }

    /**
     * Sets the name of envelope weapon
     * @param envelopeWeapon 
     */
    public void setEnvelopeWeapon(String envelopeWeapon) {
        this.envelopeWeapon = envelopeWeapon;
    }

    /**
     * Gets the submit button
     * @return submitButton
     */
    public Button getSubmitButton() {
        return submitButton;
    }

    /**
     * Creates the display of accusation cards
     * @param suspectName
     * @param roomName
     * @param weaponName 
     */
    public void initialiseCardsDisplay(String suspectName, String roomName, String weaponName) {
        ImageView suspectCardView = new ImageView(new Image("/CharacterCards/" + suspectName + ".jpg", 130, 200, false, false));
        ImageView roomCardView = new ImageView(new Image("/RoomCards/" + roomName + ".jpg", 130, 200, false, false));
        ImageView weaponCardView = new ImageView(new Image("/weaponCards/" + weaponName + ".jpg", 130, 200, false, false));
        cardsDisplay = new HBox(suspectCardView, roomCardView, weaponCardView);
    }
}
