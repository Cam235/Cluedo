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
 * Represents tiles of the board
 *
 * @author Anilz
 */
public class Tile extends Rectangle{

    private int colIndex, rowIndex;
    //to see if pawn on Tile  
    private boolean occupied;   
    
    //Applied when room is established
    private boolean wall;     // see if the tile is wall
    private boolean door;     // if the tile is doorway of the room 
    
    private Text text;
    private Image image;
    /**
     * Constructor of tile object Takes row and column index parameters to
     * identify tile from other tiles
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
     * @return column index
     */
    
    public int getColIndex() {
        return colIndex;
    }

    /**
     * @return row index
     */
    
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * 
     * @return whether or not the tile is occupied by pawn
     */
    
    public boolean isOccupied() {
        return occupied;
    }
    
    /**
     * Set the tile to occupied or not based on parameter 
     * @param isOccupied 
     */
    
    public void setOccupied(boolean isOccupied) {
        this.occupied = isOccupied;
    }
      
    //For Wall
    
    public boolean isWall() {
        return wall;
    }

    
    public void setWall(boolean isWall) {
        this.wall = isWall;
    }

    //For the Doors
    
    public boolean isDoor() {
        return door;
    }

    
    public void setDoor(boolean isDoor) {
        //if its door, its no longer wall
        setWall(false);
        this.door = isDoor;
    }

    public void setText(String s) {
        text.setText(s);
    }
    
    
    public Text getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
    
    
}
