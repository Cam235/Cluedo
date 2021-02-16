/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

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
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
    
    //For Wall
    public boolean isWall() {
        return isWall;
    }
    
    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }
    
    //For the Doors
    public boolean isDoor() {
        return isDoor;
    }

    public void setDoor(boolean isDoor) {
        this.isDoor = isDoor;
    }
    
}
