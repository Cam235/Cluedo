/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;


/**
 *
 * @author Anilz
 */
public class Room implements RoomInterface{

    private String roomName;
    private ArrayList<Tile> roomSpace;
    private String weapon = null;
    private ArrayList<Tile> doorTiles = new ArrayList<Tile>();

    public Room(String roomName, ArrayList<Tile> roomSpace) {
        this.roomName = roomName;
        this.roomSpace = roomSpace;
    }

    @Override
    public String getRoomName() {
        return roomName;
    }

    @Override
    public ArrayList<Tile> getRoomSpace() {
        return roomSpace;
    }

    @Override
    public ArrayList<Tile> getDoorTiles() {
        return doorTiles;
    }
    

    @Override
    public boolean checkTileInRoom(Tile tile) {
        return roomSpace.contains(tile);
    }

    @Override
    public void addRoomDoor(Tile doorTile) {
            //firstly if the tile is wall,initiate for loop to see if the wall covers the correct room
            if (doorTile.getIsWall()) {
                for (int i = 0; i < roomSpace.size(); i++) {
                    if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() + 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex()) {                       
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() - 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex()) {                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex()
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex()
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {
                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() + 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {
                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() - 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {
                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() + 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {
                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else if (doorTile.getColIndex() == roomSpace.get(i).getColIndex() - 1
                            && doorTile.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {
                        
                        doorTile.setDoor(true);
                        doorTiles.add(doorTile);
                    } else {
                        //System.out.println("Door is not near this tile of "+ roomName);
                    }

                }
            }

    }

}
