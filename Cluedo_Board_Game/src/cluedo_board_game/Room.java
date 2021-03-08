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
public class Room implements RoomInterface {

    private String roomName;
    private ArrayList<Tile> roomSpace;
    private String weapon = null;
    private ArrayList<Tile> roomDoors = new ArrayList<Tile>();

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
    public ArrayList<Tile> getRoomDoors() {
        return roomDoors;
    }

    @Override
    public boolean checkTileInRoom(Tile tile) {
        return roomSpace.contains(tile);
    }

    @Override
    public void addRoomDoor(Tile roomDoor) {
        //firstly if the tile is wall,initiate for loop to see if the wall covers the correct room
        if (roomDoor.getIsWall()) {
            for (int i = 0; i < roomSpace.size(); i++) {
                if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex()) {
                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex()) {
                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex()
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {
                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex()
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {

                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {

                    roomDoor.setDoor(true);
                    roomDoors.add(roomDoor);
                } else {
                    //System.out.println("Door is not near this tile of "+ roomName);
                }

            }
        }

    }

}
