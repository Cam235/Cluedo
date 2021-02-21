/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Anilz
 */
public class Tile {

    private boolean isOccupied;// see if pawn on Tile    
    //When part of room
    private boolean isWall; // see if the tile is wall
    private boolean isDoor; // if the tile is doorway of the room 

    // Constructor
    public Tile() {

    }

    //Occupied
    public boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    //For Wall
    public boolean getWall() {
        return isWall;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }

    //For the Doors
    public boolean getDoor() {
        return isDoor;
    }

    public void setDoor(boolean isDoor) {
        this.isDoor = isDoor;
    }

}
