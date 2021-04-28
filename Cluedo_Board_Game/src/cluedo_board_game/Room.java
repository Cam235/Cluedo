package cluedo_board_game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Anilz
 */
public class Room {

    private String name; // room Name
    private ArrayList<Tile> roomSpace; //List of tiles which constitutes room space
    private ArrayList<Tile> doors = new ArrayList<Tile>(); // room doors
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>(); // Weapons in room
    private Room passageExit; //Passage exit to transport other linked room

    /**
     * Constructor for room taking name and roomSpace
     *
     * @param roomName
     * @param roomSpace
     */
    public Room(String roomName, ArrayList<Tile> roomSpace) {
        this.name = roomName;
        this.roomSpace = roomSpace;
    }

    /**
     * Gets name of room
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets list of tiles that creates the room space
     *
     * @return roomSpace
     */
    public ArrayList<Tile> getRoomSpace() {
        return roomSpace;
    }

    /**
     * Gets room doors
     *
     * @return doors
     */
    public ArrayList<Tile> getDoors() {
        return doors;
    }

    /**
     * Gets weapons currently in the room
     *
     * @return roomWeapon
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Places selected weapon on random tile within roomSpace ,which is not
     * occupied
     *
     * @param roomWeapon
     */
    public void addRoomWeapon(Weapon roomWeapon) {
        Random r = new Random();
        Tile weaponTile = roomSpace.get(r.nextInt(roomSpace.size()));
        if (!weaponTile.isOccupied()) {
            weapons.add(roomWeapon);
            roomWeapon.setWeaponLocation(weaponTile);
        } else {
            addRoomWeapon(roomWeapon);
        }
    }

    /**
     * Checks tile if it is in room
     *
     * @param tile
     * @return
     */
    public boolean checkTileInRoom(Tile tile) {
        return roomSpace.contains(tile);
    }

    /**
     * Adds door to room, The door must be part of wall tiles that covers
     * roomspace. Once the tile is set to be door, tile will no longer be wall
     *
     * @param roomDoor
     */
    public void addRoomDoor(Tile roomDoor) {
        //firstly if the tile is wall,initiate for loop to see if the wall covers the correct room
        if (roomDoor.isWall()) {
            for (int i = 0; i < roomSpace.size(); i++) {
                // Do the for loop,to check if roomDoor tile is part of the walls surrounding the current room
                if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex()) {
                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex()) {
                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex()
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {
                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex()
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {

                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() + 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() - 1) {

                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                } else if (roomDoor.getColIndex() == roomSpace.get(i).getColIndex() - 1
                        && roomDoor.getRowIndex() == roomSpace.get(i).getRowIndex() + 1) {

                    roomDoor.setDoor(true);
                    if (!doors.contains(roomDoor)) {
                        doors.add(roomDoor);
                    }
                }

            }
        }

    }

    /**
     * Gets all the non-occupied tiles in roomSpace
     *
     * @return freeSpace
     */
    public ArrayList<Tile> getFreeSpace() {
        ArrayList<Tile> freeSpace = new ArrayList<>();
        for (Tile t : roomSpace) {
            if (!t.isOccupied()) {
                freeSpace.add(t);
            }
        }
        return freeSpace;
    }

    /**
     * Gets the room which passage transfers(exits) to
     *
     * @return passageExit
     */
    public Room getPassageExit() {
        return passageExit;
    }

    /**
     * Sets the room which passage transfers(exists) to
     *
     * @param passageExit
     */
    public void setPassageExit(Room passageExit) {
        this.passageExit = passageExit;
    }

}
