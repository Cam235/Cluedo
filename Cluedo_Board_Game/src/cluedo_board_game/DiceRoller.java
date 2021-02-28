/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *Represents the rolling a dice via button
 * @author Anilz
 */
public class DiceRoller {

    //Button to throw dice
    Button diceRollerButton;
    private boolean diceRolled;
    //Initial Dice Values are given 1
    private int diceNumber = 1;
    private int diceNumber2 = 1;
    //Initial 1st Dice View
    Image diceImage = new Image("/Die/Dice" + diceNumber + ".png", 60, 60, false, false);
    ImageView diceImageView = new ImageView(diceImage);
    //Initial 2nd Dice View
    Image diceImage2 = new Image("/Die/Dice" + diceNumber + ".png", 60, 60, false, false);
    ImageView diceImageView2 = new ImageView(diceImage2);

    /**
     * Creates diceroller content 2 Dices and a roll button is added,with their GUI
     * @return
     */
    public VBox CreateContent() {
        diceRollerButton = new Button();
        diceRollerButton.setText("Roll the Dice");
        diceRollerButton.setPrefWidth(120);
        diceRollerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Rolls the Dice
                diceRolled = true;
                Random randomDice = new Random();
                //Gets Value of First And Second Dice
                diceNumber = randomDice.nextInt(6) + 1;
                diceNumber2 = randomDice.nextInt(6) + 1;
                //Sets Image For First Dice
                diceImage = new Image("/Die/Dice" + diceNumber + ".png", 60, 60, false, false);
                diceImageView.setImage(diceImage);
                //Sets Second Dice
                diceImage2 = new Image("/Die/Dice" + diceNumber2 + ".png", 60, 60, false, false);
                diceImageView2.setImage(diceImage2);
                //Disables button after pressed
                diceRollerButton.setDisable(true);
            }
        });
        //Displays dices horizontally
        HBox dicesHbox = new HBox();
        dicesHbox.getChildren().addAll(diceImageView, diceImageView2);
        //Displays roll button above dices
        VBox vbox = new VBox();
        vbox.getChildren().addAll(diceRollerButton, dicesHbox);
        //SetsAlignments
        dicesHbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     *
     * @return true if dice is rolled, and false if dice has not rolled yet
     */
    public boolean isDiceRolled() {
        return diceRolled;
    }

    /**
     * sets whether or not dice has rolled
     *
     * @param diceRolled
     */
    public void setDiceRolled(boolean diceRolled) {
        this.diceRolled = diceRolled;
    }

    /**
     * Enables the diceRoller Button to roll the dice
     */
    public void enableDiceRollerButton() {
        diceRollerButton.setDisable(false);
    }

    /**
     * @return total Dice value from
     */
    public int getDiceTotal() {
        return diceNumber + diceNumber2;
    }

}
