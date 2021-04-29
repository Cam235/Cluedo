/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Represents rolling a dice via button.
 *
 * @author Anilz
 */
public class DiceRoller {

    private boolean diceRolled; //returns whether dice is rolled or not

    //Initial Dice Values are given 1
    private int diceNumber = 1;
    private int diceNumber2 = 1;
    //Button to throw dice
    private Button rollButton;
    //Initial 1st Dice View
    Image diceImage = new Image("/Die/Dice" + diceNumber + ".png", 75, 75, false, false);
    ImageView diceImageView = new ImageView(diceImage);
    //Initial 2nd Dice View
    Image diceImage2 = new Image("/Die/Dice" + diceNumber + ".png", 75, 75, false, false);
    ImageView diceImageView2 = new ImageView(diceImage2);

    /**
     * Creates DiceRoller content including 2 Dices and a roll button GUI.
     *
     * @return diceRollerBox
     */
    public VBox createContent() {
        rollButton = new Button();
        rollButton.setText("Roll the Dice");
        rollButton.setPrefSize(150, 50);
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Rolls the Dice
                diceRolled = true;
                Random randomDice = new Random();
                //Gets Value of First And Second Dice
                diceNumber = randomDice.nextInt(6) + 1;
                diceNumber2 = randomDice.nextInt(6) + 1;
                //Sets Image For First Dice Image and View
                diceImage = new Image("/Die/Dice" + diceNumber + ".png", 75, 75, false, false);
                diceImageView.setImage(diceImage);
                //Sets Second Dice Image and View
                diceImage2 = new Image("/Die/Dice" + diceNumber2 + ".png", 75, 75, false, false);
                diceImageView2.setImage(diceImage2);
                //Disables button after pressed
                rollButton.setDisable(true);
            }
        });

        //Displays dices horizontally
        HBox dicesHbox = new HBox();
        dicesHbox.getChildren().addAll(diceImageView, diceImageView2);
        //Displays roll button above dices
        VBox diceRollerBox = new VBox();
        diceRollerBox.getChildren().addAll(rollButton, dicesHbox);
        //Sets Alignments
        dicesHbox.setAlignment(Pos.CENTER);
        diceRollerBox.setAlignment(Pos.CENTER);
        //Sets background of diceRollerBox
        Background bg = new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        diceRollerBox.setBackground(bg);

        return diceRollerBox;
    }

    /**
     * Returns true if dice is rolled, and false if dice has not been rolled.
     *
     * @return diceRolled
     */
    public boolean isDiceRolled() {
        return diceRolled;
    }

    /**
     * Sets whether or not dice is rolled.
     *
     * @param diceRolled
     */
    public void setDiceRolled(boolean diceRolled) {
        this.diceRolled = diceRolled;
    }

    /**
     * Enables the diceRoller Button to roll the dice.
     */
    public void enableDiceRollerButton() {
        rollButton.setDisable(false);
    }

    /**
     * @return total Dice value
     */
    public int getDiceTotal() {
        return diceNumber + diceNumber2;
    }

    /**
     * Gets the roll button.
     *
     * @return rollButton
     */
    public Button getRollButton() {
        return rollButton;
    }

}
