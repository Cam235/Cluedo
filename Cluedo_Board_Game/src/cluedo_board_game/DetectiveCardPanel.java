/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * For the creation of detective card where player can take notes and select suspect,weapon,and room items 
 * to help finding murder cards 
 * @author Anilz
 * @version 1.0
 */
public class DetectiveCardPanel {

    //String arrays of suspect ,weapon and room names
    private String[] suspectNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};
    private String[] weaponNames = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
    private String[] roomNames = {"Lounge", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Billiardroom", "Library", "Hall", "Study"};

    //Collection of HBoxes including itemLabels and corresponding ChoiceBoxes,and hashmap representations
    private ArrayList<HBox> boxesAndLabels = new ArrayList<HBox>();
    private HashMap<String, Boolean> detectiveChecklist = new HashMap<String, Boolean>();

    //Detective notes field and text area
    private String detectiveNotes;
    private TextArea detectiveNotesTextArea;
    
    //Save button
    private Button saveButton;

    /**
     * Create content of detective Card
     * @return detectiveCheckList
     */
    public Parent createContent() {
        //VBox to contain suspect names and corresponding boxes
        VBox suspectList = new VBox();
        //Heading label,size of label created and put into VBox
        Label suspects = new Label("------Suspects------");
        suspectList.getChildren().add(suspects);
        suspects.setScaleX(1);
        //Loop through suspect names and for each suspect name;
        for (String suspectName : suspectNames) {
            //Create label and checkbox,then put them into HBox
            Label suspectText = new Label(suspectName);
            CheckBox suspectCheckBox = new CheckBox();
            HBox suspectCheckListLine = new HBox(suspectCheckBox, suspectText);
            //Add the HBox to defined arrayList
            boxesAndLabels.add(suspectCheckListLine);

            //detectiveCard.put(suspectName, suspectCheckBox.isSelected());
            //Set Spacings between label and checkbox 
            suspectCheckListLine.setSpacing(10);
            //Finally add HBox into VBox
            suspectList.getChildren().add(suspectCheckListLine);

        }
        //VBox to contain room names and corresponding boxes
        VBox roomList = new VBox();
        //Heading label,size of label created and put into VBox
        Label rooms = new Label("------Rooms------");
        roomList.getChildren().add(rooms);
        rooms.setScaleX(1);
        //Loop through room names and for each room name;
        for (String roomName : roomNames) {
            //Create label and checkbox,then put them into HBox
            Label roomLabel = new Label(roomName);
            CheckBox roomCheckBox = new CheckBox();
            HBox roomCheckListLine = new HBox(roomCheckBox, roomLabel);
            //Add the HBox to defined arrayList
            boxesAndLabels.add(roomCheckListLine);
            //Set Spacings between label and checkbox 
            roomCheckListLine.setSpacing(10);
            //Finally add HBox into VBox
            roomList.getChildren().add(roomCheckListLine);
        }

        //VBox to contain weapon names and corresponding boxes
        VBox weaponList = new VBox();
        //Heading label,size of label created and put into VBox
        Label weapons = new Label("------Weapons------");
        roomList.getChildren().add(weapons);
        weapons.setScaleX(1);
        //Loop through weapon names and for each room name;
        for (String weaponName : weaponNames) {
            //Create label and checkbox,then put them into HBox
            Label weaponLabel = new Label(weaponName);
            CheckBox weaponCheckBox = new CheckBox();
            HBox weaponCheckListLine = new HBox(weaponCheckBox, weaponLabel);
            //Add the HBox to defined arrayList
            boxesAndLabels.add(weaponCheckListLine);
            //Set Spacings between label and checkbox 
            weaponCheckListLine.setSpacing(10);
            //Finally add HBox into VBox
            weaponList.getChildren().add(weaponCheckListLine);
        }
        
        //Final VBox is created for complete checklist including suspectList,roomList and weaponList
        VBox completeCheckList = new VBox(suspectList, roomList, weaponList);
        //A text area is created for a player to take notes
        detectiveNotesTextArea = new TextArea(detectiveNotes);
        detectiveNotesTextArea.setPromptText("Case Notes...");
        detectiveNotesTextArea.setWrapText(true);
        detectiveNotesTextArea.setPrefSize(200, 500);
        Pane detectiveNotesPane = new Pane(detectiveNotesTextArea);

        saveButton = new Button("Save CheckList!");
        //Finally checklist and detective note area is combined into HBox detective checklist
        HBox detectiveCard = new HBox(completeCheckList, detectiveNotesPane, saveButton);

        return detectiveCard;
    }

    
    /**
     * Selects / deselects the box corresponding to label,
     * therefore saving the data in HashMap
     *
     * @param itemName
     */
    private boolean toggleSelect(String itemName) {
        detectiveChecklist.replace(itemName, !detectiveChecklist.get(itemName));
        return !detectiveChecklist.get(itemName);
    }

    
    /**
     * Returns updated detective card when a checkbox is selected
     *
     * @return detectiveChecklist
     */
    public HashMap getCardUpdates() {
        for (HBox boxAndName : boxesAndLabels) {
            // child 0 is selection box and child 1 is label
            CheckBox selectionBox = (CheckBox) boxAndName.getChildren().get(0);
            Label selectionLabel = (Label) boxAndName.getChildren().get(1);
            //if value not selected not select , if selected select
            if (detectiveChecklist.get(selectionLabel.getText()) == Boolean.TRUE) {
                selectionBox.setSelected(true);
            } else {
                selectionBox.setSelected(false);
            }
            //On click a print is provided
            selectionBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //if selected print selected , else print not selected
                    toggleSelect(selectionLabel.getText());
                }
            });

        }
        return detectiveChecklist;
    }

    /**
     * Gets the detectiveChecklist
     * @return detectiveChecklist
     */
    public HashMap<String, Boolean> getDetectiveChecklist() {
        return detectiveChecklist;
    }

    /**
     * Sets the detective card
     * @param detectiveChecklist 
     */
    public void setDetectiveChecklist(HashMap<String, Boolean> detectiveChecklist) {
        this.detectiveChecklist = detectiveChecklist;
    }

    /**
     * Gets detective notes
     * @return detectiveNotes
     */
    public String getDetectiveNotes() {
        return detectiveNotes;
    }

    /**
     * Sets detective notes
     * @param detectiveNotes 
     */
    public void setDetectiveNotes(String detectiveNotes) {
        this.detectiveNotes = detectiveNotes;
    }

    /**
     * Gets Text area
     * @return detectiveNotesTextArea
     */
    public TextArea getDetectiveNotesTextArea() {
        return detectiveNotesTextArea;
    }

    /**
     * Gets the save button
     * @return saveButton
     */
    public Button getSaveButton() {
        return saveButton;
    }

}
