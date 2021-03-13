/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Anilz
 */
public class Room implements RoomInterface {

    private String roomName; 
    private ArrayList<Tile> roomSpace; //for inside of room
    private ArrayList<Tile> roomDoors = new ArrayList<Tile>(); // roomdoors
    private Weapon roomWeapon = null; // for weapons of the room( will convert into arrayList

    /**
     * Constructor for room taking roomName and roomSpace
     * @param roomName
     * @param roomSpace 
     */
    public Room(String roomName, ArrayList<Tile> roomSpace) {
        this.roomName = roomName;
        this.roomSpace = roomSpace;
    }
    
    /**
     * 
     * @return roomName
     */
    @Override
    public String getRoomName() {
        return roomName;
    }
    
    /**
     * returns list of tiles that creates the room space
     * @return roomSpace
     */
    @Override
    public ArrayList<Tile> getRoomSpace() {
        return roomSpace;
    }
    /**
     * returns doors of room
     * @return roomDoors
     */
    @Override
    public ArrayList<Tile> getRoomDoors() {
        return roomDoors;
    }
    
    /**
     * Returns roomWeapons
     * @return roomWeapon
     */
    public Weapon getRoomWeapon() {
        return roomWeapon;
    }
    
    /**
     * Places selected weapon on random tile within roomSpace
     * @param roomWeapon 
     */
    public void setRoomWeapon(Weapon roomWeapon) {
        this.roomWeapon = roomWeapon;
        //Sets weapon to random tile in roomSpace
        Random r = new Random();
        Tile weaponTile = roomSpace.get(r.nextInt(roomSpace.size()));
        roomWeapon.setWeaponLocation(weaponTile);
    }
    
    
    
    /**
     * Checks tile if in room
     * @param tile
     * @return 
     */
    @Override
    public boolean checkTileInRoom(Tile tile) {
        return roomSpace.contains(tile);
    }
    
    
    /**
     * Adds door to rooms,
     * The door must be part of wall tiles that covers roomspace
     * The tile will be set to door, and tile will no longer be wall
     * @param roomDoor 
     */
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
