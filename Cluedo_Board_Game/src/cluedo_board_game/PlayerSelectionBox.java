/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class put in the pre-game content, and allows players to write their name,
 * and choose their character.
 *
 * @author Anilz
 */
public class PlayerSelectionBox {

    private String playerName; // player Name
    private char selectedPlayerType; // whether player registered is human or agent
    private String selectedCharacter; //players character

    //Textfield for player name inputs
    private TextField playerTextField;

    //Fields for character via combobox, show character display 
    private ComboBox<String> characterSelectionCombobox;
    //String array of character names,which make up the characterSelectionCombobox
    public String characters[] = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};

    //Field of image and view of selected character
    private Image selectedCharacterImage;
    private ImageView selectedCharacterView;

    //Radio buttons to toggle between agent and human player selection
    private RadioButton agentButton;
    private RadioButton humanButton;

    /**
     * Creates HBox for character selection
     *
     * @return characterSelectBox
     */
    public Parent selectionContent() {
        HBox characterSelectBox = new HBox();
        characterSelectBox.setSpacing(10);
        //While character is not selected, unknown card is displayed for selectionContent
        selectedCharacterImage = new Image("/CharacterCards/unknownCard.png", 130, 200, true, false);
        selectedCharacterView = new ImageView(selectedCharacterImage);
        //Textfield Value to get player name
        playerTextField = new TextField();
        playerTextField.setPrefColumnCount(14);
        playerTextField.setPromptText("Player Name...");
        playerTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerName = playerTextField.getText();
            }
        });
        //Once character is selected from combobox, selected character image is found
        characterSelectionCombobox = new ComboBox<>(FXCollections.observableArrayList(characters));
        characterSelectionCombobox.setPromptText("Choose Character...");
        characterSelectionCombobox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedCharacter = (String) characterSelectionCombobox.getValue();
                selectedCharacterImage = new Image("/CharacterCards/" + selectedCharacter + ".jpg", 130, 200, false, false);
                selectedCharacterView = new ImageView(selectedCharacterImage);
            }
        });
        //When clicked player type is set to agent
        agentButton = new RadioButton("Agent");
        agentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedPlayerType = 'a';
            }
        });

        //When clicked player type is set to human
        humanButton = new RadioButton("Human");
        humanButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedPlayerType = 'h';
            }
        });

        //Put the radio buttons into toggle group to deselect the radio button when alternative button is chosen
        ToggleGroup radioGroup = new ToggleGroup();
        agentButton.setToggleGroup(radioGroup);
        humanButton.setToggleGroup(radioGroup);

        //Puts all input fields to character selection box
        characterSelectBox.getChildren().addAll(playerTextField, characterSelectionCombobox, agentButton, humanButton);
        return characterSelectBox;
    }

    /**
     * Gets player name.
     *
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets player type (agent or human).
     *
     * @return selectedPlayerType
     */
    public char getPlayerType() {
        return selectedPlayerType;
    }

    /**
     * Gets player character.
     *
     * @return selectedCharacter
     */
    public String getPlayerCharacter() {
        return selectedCharacter;
    }

    /**
     * Gets game characters.
     *
     * @return characters
     */
    public String[] getCharacters() {
        return characters;
    }

    /**
     * Gets text field.
     *
     * @return playerTextField
     */
    public TextField getPlayerTextField() {
        return playerTextField;
    }

    /**
     * Gets comboBox of character selection.
     *
     * @return characterSelectionCombobox
     */
    public ComboBox<String> getCharacterSelectionCombobox() {
        return characterSelectionCombobox;
    }

    /**
     * Gets cardView of selected character.
     *
     * @return selectedCharacterView
     */
    public ImageView getSelectedCharacterView() {
        return selectedCharacterView;
    }

    /**
     * Gets the name of selected character.
     *
     * @return selectedCharacter
     */
    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    /**
     * Sets the character to be played with.
     *
     * @param selectedCharacter
     */
    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    /**
     * Gets the selected characters image.
     *
     * @return selectedCharacterImage
     */
    public Image getSelectedCharacterImage() {
        return selectedCharacterImage;
    }

    /**
     * Sets the image of selected character.
     *
     * @param selectedCharacterImage
     */
    public void setSelectedCharacterImage(Image selectedCharacterImage) {
        this.selectedCharacterImage = selectedCharacterImage;
    }

    /**
     * Sets the imageView of selected character.
     *
     * @param selectedCharacterView
     */
    public void setSelectedCharacterView(ImageView selectedCharacterView) {
        this.selectedCharacterView = selectedCharacterView;
    }

    /**
     * Gets the agent button.
     *
     * @return agentButton
     */
    public RadioButton getAgentButton() {
        return agentButton;
    }

    /**
     * Gets the human button.
     *
     * @return humanButton
     */
    public RadioButton getHumanButton() {
        return humanButton;
    }

}
