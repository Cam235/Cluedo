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
    private String accusedSuspect;
    private String accusedRoom;
    private String accusedWeapon;

    private String envelopeSuspect;
    private String envelopeRoom;
    private String envelopeWeapon;

    private String[] suspects = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private String[] rooms = {"Lounge", "Diningroom", "Kitchen", "Hall", "Study", "Ballroom", "Conservatory", "Billiardroom", "Library"};
    private String[] weapons = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};

    private Button submitButton; // The submit button of th game

    private HBox cardDisplays;

    public Parent createAccusationContent(String accusingPlayerName) {
        Label playerAccusationText = new Label("Player " + accusingPlayerName + " is making accusation!");

        //For Room       
        Label roomLabel = new Label("Room :   ");
        ComboBox<String> roomBox = new ComboBox<>(FXCollections.observableArrayList(rooms));
        HBox roomSelection = new HBox(roomLabel, roomBox);
        roomSelection.setSpacing(50);

        ImageView roomCardView = new ImageView(new Image("/RoomCards/unknownCard.png", 130, 200, false, false));
        roomBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedRoom = (String) roomBox.getValue();
                roomCardView.setImage(new Image("/RoomCards/" + accusedRoom + ".jpg", 130, 200, false, false));
            }
        });
        //For Suspect
        Label suspectLabel = new Label("Suspect :");
        ComboBox<String> suspectBox = new ComboBox<>(FXCollections.observableArrayList(suspects));
        HBox suspectSelection = new HBox(suspectLabel, suspectBox);
        suspectSelection.setSpacing(50);

        ImageView suspectCardView = new ImageView(new Image("/CharacterCards/unknownCard.png", 130, 200, false, false));

        suspectBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedSuspect = (String) suspectBox.getValue();
                suspectCardView.setImage(new Image("/CharacterCards/" + accusedSuspect + ".jpg", 130, 200, false, false));
            }
        });

        //For Weapon
        Label weaponLabel = new Label("Weapon :");
        ComboBox<String> weaponBox = new ComboBox<>(FXCollections.observableArrayList(weapons));
        HBox weaponSelection = new HBox(weaponLabel, weaponBox);
        weaponSelection.setSpacing(45);

        ImageView weaponCardView = new ImageView(new Image("/weaponCards/unknownCard.png", 130, 200, false, false));

        weaponBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accusedWeapon = (String) weaponBox.getValue();
                weaponCardView.setImage(new Image("/weaponCards/" + accusedWeapon + ".jpg", 130, 200, false, false));
            }
        });
        // A submit button
        submitButton = new Button("Submit");
        cardDisplays = new HBox(suspectCardView, roomCardView, weaponCardView);
        VBox accusationContent = new VBox();
        accusationContent.getChildren().addAll(playerAccusationText, suspectSelection, roomSelection, weaponSelection, submitButton, cardDisplays);
        accusationContent.setAlignment(Pos.CENTER);
        accusationContent.setSpacing(10);
        //Background color
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        accusationContent.setBackground(bg);
        return accusationContent;
    }

    public Alert createCorrectAccusationContent(String wonPlayerName, String envelopeSuspect, String envelopeRoom, String envelopeWeapon) {
        this.envelopeSuspect = envelopeSuspect;
        this.envelopeRoom = envelopeRoom;
        this.envelopeWeapon = envelopeWeapon;
        Alert correctAccusationAlert = new Alert(Alert.AlertType.INFORMATION);
        correctAccusationAlert.setTitle("Player " + wonPlayerName + " wins!");
        correctAccusationAlert.setHeaderText("Accusation Correct!");
        correctAccusationAlert.setGraphic(cardDisplays);
        correctAccusationAlert.setContentText(envelopeSuspect + " in the " 
                + envelopeRoom + " with a " 
                + envelopeWeapon + " is correct!\n\n"
        +"*** "+ wonPlayerName +" accused correctly and won the game! ***\n");
        return correctAccusationAlert;
    }

    public Alert createFalseAccusationContent(String lostPlayerName, String envelopeSuspect, String envelopeRoom, String envelopeWeapon) {
        this.envelopeSuspect = envelopeSuspect;
        this.envelopeRoom = envelopeRoom;
        this.envelopeWeapon = envelopeWeapon;

        Alert falseAccusationAlert = new Alert(Alert.AlertType.ERROR);
        falseAccusationAlert.setTitle("Player " + lostPlayerName + " loses!");
        falseAccusationAlert.setHeaderText("Accusation Incorrect!");
        ImageView murderer = new ImageView(new Image("/CharacterCards/" + envelopeSuspect + ".jpg", 130, 200, false, false));
        ImageView murderRoom = new ImageView(new Image("/RoomCards/" + envelopeRoom + ".jpg", 130, 200, false, false));
        ImageView murderWeapon = new ImageView(new Image("/weaponCards/" + envelopeWeapon + ".jpg", 130, 200, false, false));
        HBox murderCardsDisplay = new HBox(murderer, murderRoom, murderWeapon);
        falseAccusationAlert.setGraphic(murderCardsDisplay);
        falseAccusationAlert.setContentText("It was " + envelopeSuspect + " in the " + envelopeRoom + " with a " + envelopeWeapon + "!" + "\n"
                + "Player " + lostPlayerName + " is out of the game!");
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

    public String getEnvelopeSuspect() {
        return envelopeSuspect;
    }

    public void setEnvelopeSuspect(String envelopeSuspect) {
        this.envelopeSuspect = envelopeSuspect;
    }

    public String getEnvelopeRoom() {
        return envelopeRoom;
    }

    public void setEnvelopeRoom(String envelopeRoom) {
        this.envelopeRoom = envelopeRoom;
    }

    public String getEnvelopeWeapon() {
        return envelopeWeapon;
    }

    public void setEnvelopeWeapon(String envelopeWeapon) {
        this.envelopeWeapon = envelopeWeapon;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
