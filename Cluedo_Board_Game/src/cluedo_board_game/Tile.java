/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents each tile of the board.
 *
 * @author Anilz
 * @version 1.0
 */
public class Tile extends Rectangle {

    private int colIndex, rowIndex; //Every tile has row and column index to identification   
    private boolean occupied;       //represents whether tile is occupied by token or weapon

    private boolean wall;   // represents whether tile is wall
    private boolean door;   // represents whether tile is door

    private Text text;      //numerical text , for the use of exiting rooms
    private Image image;    //Image of tile ,each tile has its own image

    /**
     * Constructor of tile object takes row and column index parameters to
     * identify tile from other tiles.
     *
     * @param colIndex
     * @param rowIndex
     */
    public Tile(int colIndex, int rowIndex) {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        text = new Text("");
        text.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 17));
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
    }

    /**
     * Gets column index of tile.
     *
     * @return column index
     */
    public int getColIndex() {
        return colIndex;
    }

    /**
     * Gets Row index of tile.
     *
     * @return rowIndex
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Get the occupied value, returns true if a weapon or token is on tile.
     *
     * @return occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Set the occupied value.
     *
     * @param isOccupied
     */
    public void setOccupied(boolean isOccupied) {
        this.occupied = isOccupied;
    }

    /**
     * Get the wall value.
     *
     * @return wall
     */
    public boolean isWall() {
        return wall;
    }

    /**
     * Set the wall value.
     *
     * @param isWall
     */
    public void setWall(boolean isWall) {
        this.wall = isWall;
    }

    /**
     * Get the door value.
     *
     * @return door
     */
    public boolean isDoor() {
        return door;
    }

    /**
     * Set the door value.
     *
     * @param isDoor
     */
    public void setDoor(boolean isDoor) {
        //if its door, its no longer wall
        setWall(false);
        this.door = isDoor;
    }

    /**
     * Set the tile text.
     *
     * @param tileText
     */
    public void setText(String tileText) {
        text.setText(tileText);
    }

    /**
     * Gets the text value of tile.
     *
     * @return text
     */
    public Text getText() {
        return text;
    }

    /**
     * Gets the tile image.
     *
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the tile image.
     *
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
