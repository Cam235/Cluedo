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
public class PlayerSelectionBox  {

    //Fields of Selection
    private String playerName;
    private char selectedPlayerType;
    private String selectedCharacter;

    public static String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mrs.Peacock", "Mr.Green", "Professor Plum"};
    private Button printSelectionsButton;
    private RadioButton agentButton;
    private RadioButton humanButton;

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

        printSelectionsButton = new Button("ApproveSelections");
        printSelectionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerName = playerTextField.getText();
                selectedPlayerType = agentButton.isSelected()? 'a' : 'h';
                selectedCharacter = (String) combobox.getValue();
                System.out.println(playerName + "is an " + selectedPlayerType + " and plays with " + selectedCharacter);

            }
        });
        characterSelectBox.getChildren().addAll(playerTextField, combobox, agentButton, humanButton, printSelectionsButton);

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

    public String getCharacter() {
        return selectedCharacter;
    }

    public String[] getCharacters() {
        return characters;
    }
    
    
    
    
    
    

    /**
     * @param args the command line arguments
     
    public static void main(String[] args) {
        launch(args);
    }
    * */

}
