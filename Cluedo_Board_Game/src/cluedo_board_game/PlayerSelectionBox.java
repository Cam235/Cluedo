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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Anilz
 */
public class PlayerSelectionBox extends Application {

    //Fields of Selection
    String playerName;
    String selectedCharacter;
    String selectedPlayerType;

    String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    Button printSelectionsButton;
    RadioButton agentButton;
    RadioButton humanButton;

    public Parent selectionContent() {
        HBox characterSelectBox = new HBox();
        //Textfield Value
        TextField playerTextField = new TextField();
        playerName = playerTextField.getText();

        ComboBox combobox = new ComboBox(FXCollections.observableArrayList(characters));
        combobox.setPromptText("Choose Character...");

        agentButton = new RadioButton("Agent");
        humanButton = new RadioButton("Human");

        //To toggle between radio buttons
        ToggleGroup radioGroup = new ToggleGroup();
        agentButton.setToggleGroup(radioGroup);
        humanButton.setToggleGroup(radioGroup);

        printSelectionsButton = new Button("PrintSelections");
        printSelectionsButton.setOnAction(e -> System.out.println(playerTextField.getText() + "is an " + selectedPlayerType + " and plays with " + combobox.getValue()));
        characterSelectBox.getChildren().addAll(playerTextField, combobox, agentButton, humanButton, printSelectionsButton);

        return characterSelectBox;
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(selectionContent());
        if (agentButton.isSelected()) {
            selectedPlayerType = "Human";
        } else if (humanButton.isSelected()) {
            selectedPlayerType = "Non-Human";
        }

        primaryStage.setTitle("Hello World!");
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
