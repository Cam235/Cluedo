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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Anilz
 */
public class PlayerSelectionBox {
    //To write players Names
    TextField playerTextField;
    //select character via combobox, show character display 
    ComboBox<String> characterSelectionCombobox;
    Image selectedCharacterImage;
    ImageView selectedCharacterView;
    
    RadioButton agentButton;
    RadioButton humanButton;

    //Fields of Selection
    String playerName;
    private char selectedPlayerType;
    String selectedCharacter;

    public String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};

    public Parent selectionContent() {
        HBox characterSelectBox = new HBox();
        characterSelectBox.setSpacing(10);
        
        selectedCharacterImage = new Image("/CharacterCards/unknownCard.png",80,200,false,false);
        selectedCharacterView = new ImageView(selectedCharacterImage);
        //Textfield Value
        playerTextField = new TextField();
        playerTextField.setPromptText("Player Name...");
        playerTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerName = playerTextField.getText();
                System.out.println(playerName);
            }
        });
        characterSelectionCombobox = new ComboBox<>(FXCollections.observableArrayList(characters));
        characterSelectionCombobox.setPromptText("Choose Character...");
        characterSelectionCombobox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedCharacter = (String) characterSelectionCombobox.getValue();
                System.out.println(selectedCharacter);
                selectedCharacterImage = new Image("/CharacterCards/"+selectedCharacter+".jpg");
                selectedCharacterView = new ImageView(selectedCharacterImage);
                
            }
        });

        agentButton = new RadioButton("Agent");
        agentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedPlayerType = 'a';
            }
        });
        
        humanButton = new RadioButton("Human");
        humanButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedPlayerType = 'h';
            }
        });
        
        //Initially select agent button
        //agentButton.setSelected(true);
        //To toggle between radio buttons
        ToggleGroup radioGroup = new ToggleGroup();
        agentButton.setToggleGroup(radioGroup);
        humanButton.setToggleGroup(radioGroup);
        /*
        printSelectionsButton = new Button("ApproveSelections");

        printSelectionsButton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                playerName = playerTextField.getText();
                selectedPlayerType = agentButton.isSelected() ? 'a' : 'h';
                selectedCharacter = (String) characterSelectionCombobox.getValue();
                System.out.println(playerName + "is an " + selectedPlayerType + " and plays with " + selectedCharacter);

            }
        }
        );
         */
        characterSelectBox.getChildren()
                .addAll(playerTextField, characterSelectionCombobox, agentButton, humanButton);

        return characterSelectBox;
    }

    /*
    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(selectionContent());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     */
    public String getPlayerName() {
        return playerName;
    }

    public char getPlayerType() {
        return selectedPlayerType;
    }

    public String getPlayerCharacter() {
        return selectedCharacter;
    }

    public String[] getCharacters() {
        return characters;
    }

    /**
     * @param args the command line arguments
     *
     * public static void main(String[] args) { launch(args); }
     *
     */
}
