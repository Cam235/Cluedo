/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;
//Hey

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Represents board of the game
 *
 * @author Anilz
 */
public class Board implements BoardInterface {

    //These should not be on board 
    private Random idGenerator; //used to generate unique player Ids
    private ArrayList<Player> playerList; //map of players and their Ids
    private CardDistributor cardDistributor; //card distributor for board
    //To measure steps not surpassing value of dice
    // private int counter = 0;

    Tile[][] tileMap;   //Map of Tiles
    private int columns, rows;  // Parameters
    private ArrayList<Room> rooms = new ArrayList<Room>(); // Rooms are stored in
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>(); //Weapons are stored in 
    //represents the player whos turn it currently is
    private Player currentPlayer;

    //Sets Up the Board of Tiles
    public Board(int columns, int rows) {
        // fields determining columns and rows of map
        this.columns = columns;
        this.rows = rows;
        // initiate tileMap 
        tileMap = new Tile[columns][rows];
        for (int _row = 0; _row < rows; _row++) {
            for (int _column = 0; _column < columns; _column++) {
                //Created TileMap of Tiles,and indexes are provided for each tile created
                tileMap[_column][_row] = new Tile(_column, _row);
            }
        }

        playerList = new ArrayList<>();
        idGenerator = new Random();
    }

    /**
     *
     * @return board
     */
    @Override
    public Tile[][] getTileMap() {
        return tileMap;
    }

    /**
     *
     * @return column size of the board
     */
    @Override
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return row size of the board
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * returns to list of weapons
     *
     * @return weapons
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Initialize a new weapon and add to the list Then returns to the
     * Initialized weapon
     *
     * @param weapon
     * @return
     */
    public Weapon initializeWeapon(String weapon) {
        weapons.add(new Weapon(weapon));
        return weapons.get(weapons.size() - 1);
    }

    /**
     * Places selected weapon into selected room
     *
     * @param room
     * @param weapon
     */
    public void placeWeaponToRoom(Room room, Weapon weapon) {
        //If weapon is already in any room, remove from that room
        for (Room potentialRoom : rooms) {
            if (potentialRoom.getRoomWeapons().contains(weapon)) {
                potentialRoom.getRoomWeapons().remove(weapon);
            }
        }
        //Add to new Room 
        room.addRoomWeapon(weapon);
    }

    /**
     * Returns list of rooms
     *
     * @return rooms
     */
    @Override
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Initilize a new Room taking parameters of
     *
     * @param name
     * @param roomSpace
     * @param roomDoors
     * @return newRoom
     */
    @Override
    public Room initializeRoom(String name, ArrayList<Tile> roomSpace, ArrayList<Tile> roomDoors) {
        try {
            //Creates Room
            Room newRoom = new Room(name, roomSpace);
            rooms.add(newRoom);
            int roomIndex = rooms.indexOf(newRoom);
            Room newAddedRoom = rooms.get(roomIndex);
            //Surrounds Room with Walls
            for (int _row = 0; _row < rows; _row++) {
                for (int _column = 0; _column < columns; _column++) {
                    //If the tile is room
                    if (newAddedRoom.checkTileInRoom(tileMap[_column][_row])) {
                        //Sets up wall if surrounding tiles are not rooms
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column + 1][_row])) {
                            tileMap[_column + 1][_row].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column][_row + 1])) {
                            tileMap[_column][_row + 1].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column][_row - 1])) {
                            tileMap[_column][_row - 1].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column - 1][_row])) {
                            tileMap[_column - 1][_row].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column + 1][_row + 1])) {
                            tileMap[_column + 1][_row + 1].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column - 1][_row + 1])) {
                            tileMap[_column - 1][_row + 1].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column + 1][_row - 1])) {
                            tileMap[_column + 1][_row - 1].setWall(true);
                        }
                        if (!newAddedRoom.checkTileInRoom(tileMap[_column - 1][_row - 1])) {
                            tileMap[_column - 1][_row - 1].setWall(true);
                        }
                    }
                }
            }
            //Creates Door for the room, if only by replacing one of the surrounding wall tile with door Tile
            //set tile makes the tile no longer wall but door 
            for (Tile roomDoor : roomDoors) {
                rooms.get(rooms.size() - 1).addRoomDoor(roomDoor);
            }
            return newRoom;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't initialize here ");
            return null;
        }
    }

    /**
     * Initialize token for each playing and non-playing players of board
     *
     * @param player
     * @param tokenName
     * @param x
     * @param y
     */
    public void initializePlayerToken(Player player, String tokenName) {
        try {
            Token token = new Token(tokenName);
            //Sets tokens location on board
            //token.setTokenLocation(getTileMap()[x][y]);
            //Adds the token last
            player.setToken(token);
        } catch (Exception e) {
            System.out.println("Cannot initialize");

        }
    }

    /**
     * @return textual representation of board
     */
    @Override
    public String toString() {
        String s = "";
        for (int _h = 0; _h < rows; _h++) {
            for (int _w = 0; _w < columns; _w++) {
                if ((rooms != null) && !tileMap[_w][_h].IsOccupied()) {
                    //iterate through each room
                    for (Room room : rooms) {
                        if (room.checkTileInRoom(tileMap[_w][_h])) {
                            s += "R";
                        }
                    }
                    s += "R";
                } else if (tileMap[_w][_h].IsOccupied()) {
                    s += "O";
                } else {
                    s += "X";
                }
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Instantiates the cardDistributor for the board
     *
     * @param cardList a list of card Objects used to instantiate the
     * cardDistributor
     */
    @Override
    public void setCardDistributor(List<Card> cardList) {
        cardDistributor = new CardDistributor(cardList);
    }

    /**
     * Instantiates Player objects and adds them to the player map of the board
     * game
     *
     * @param playerNames a list of player name Strings to be made into player
     * Objects and added to the player Map
     */
    @Override
    public void addPlayers(List<String> playerNames, List<Character> playerTypes) {
        boolean playerAdded; //represents if a player is added successfully
        int potentialId; //stores potential ids for players
        Set<Integer> playerIds = new HashSet<>();
        for (String s : playerNames) { //iterate through a list of player names and generate players for each name
            playerAdded = false;
            while (!playerAdded) { //generate new player ID until a unique one is found so player can be added
                potentialId = idGenerator.nextInt(1000);//ids can be in range 0,999
                if (!playerIds.contains(potentialId)) { //if id is unique add player to playerList
                    if (playerTypes.get(0) == 'a') {
                        playerList.add(new Agent(potentialId, s));
                        playerTypes.remove(0);
                    } else if (playerTypes.get(0) == 'h') {
                        playerList.add(new Human(potentialId, s));
                        playerTypes.remove(0);
                    }
                    playerIds.add(potentialId);
                    playerAdded = true;
                }
            }
        } 
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public void positionUpdateAI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void positionUpdatePlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void orderPlayerList() {
        ArrayList<Player> tempList = new ArrayList<>();
        String[] playerNames = { "Miss Scarlett","Colonel Mustard","Mrs.White","Mr.Green","Mrs.Peacock","Professor Plum" };
        for(String name: playerNames){
            if(getPlayerByCharacterName(name)!=null){
                tempList.add(getPlayerByCharacterName(name));
            }
            else{
                //in actual game this should never happen
                System.out.println("missing character " + name);
            }
        }
        playerList = new ArrayList<>();
        playerList = tempList;
    }

    @Override
    public void initializeWeapons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * increments the current Player
     */
    @Override
    public void incrementCurrentPlayer() {
        currentPlayer = getNextActivePlayer(currentPlayer);
    }

    public Player getNextActivePlayer(Player player) {
        if (playerList.contains(player)) {
            int i = 0;
            do{
                if (playerList.indexOf(player) == playerList.size() - 1) {
                    player = playerList.get(0);
                } else {
                    player = playerList.get(playerList.indexOf(player) + 1);
                }
                i++;
            } while (!player.getIsPlaying() && i < playerList.size());
            if(player.getIsPlaying()){
                return player;
            }
            else{
                System.out.println("No remaining active players");
                return null;
            }
        } else {
            System.out.println("Player is not in PlayerList");
            return null;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void currentPlayerEntersRoom(Room room) {
        //When hits 
        int index = (int) (Math.random() * room.getRoomSpace().size());
        int newX = room.getRoomSpace().get(index).getColIndex();
        int newY = room.getRoomSpace().get(index).getRowIndex();
        positionUpdateCurrentPlayer(newX, newY);

    }
    

    /**
     * Method to move token on specified location on board Method can be
     * replicated as much as total dice value TO make it work, dice has to be
     * re-thrown
     *
     * @param token
     * @param x
     * @param y
     */
    public void positionUpdateCurrentPlayer(int x, int y) {
        try {
            Room currentPlayerRoom = getRoomOfPlayer(currentPlayer);
            if(currentPlayerRoom != null){
                //Spawn at one of the doors
                Random random = new Random();
                int doorToExit = random.nextInt(currentPlayerRoom.getRoomDoors().size());
                //Gets the coordinates of doors 
                int newX = currentPlayerRoom.getRoomDoors().get(doorToExit).getColIndex();
                int newY = currentPlayerRoom.getRoomDoors().get(doorToExit).getRowIndex();
                //Place the player at the door
                currentPlayer.getToken().getTokenLocation().setOccupied(false);
                currentPlayer.moveToken(getTileMap()[newX][newY]);
            }
            //If the tile to be moved is not Wall or occupied,confirm movement
            else if (!getTileMap()[x][y].getIsWall() && !getTileMap()[x][y].IsOccupied()) {
                // Loops through all rooms
                if (getTileMap()[x][y].getIsDoor()) {
                    //Iterate through rooms
                    for (Room room : getRooms()) {
                        //If the board tile to be moved is door then of specified room
                        if (room.getRoomDoors().contains(getTileMap()[x][y])) {
                            //And if the player has not in room yet,then move to room
                            if (!room.getRoomSpace().contains(currentPlayer.getToken().getTokenLocation())) {
                                //Prints which room of entry
                                System.out.println("You are entering the " + room.getRoomName());
                                //Hit the door And spawn player at random location in room
                                currentPlayerEntersRoom(room);
                                //Ends token movements
                            } else {
                                //If is in the player is in the room and about to move to door,let player move
                                //If it is already in room, then do not spawn in room
                                currentPlayer.getToken().getTokenLocation().setOccupied(false);
                                currentPlayer.moveToken(getTileMap()[x][y]);
                            }
                        }
                    }
                } 
                else {
                    //if neither wall nor door, make just a movement
                    currentPlayer.getToken().getTokenLocation().setOccupied(false);
                    currentPlayer.moveToken(getTileMap()[x][y]);
                }
            } else {
                //if tile to be moved is wall , then cannot move 
                System.out.println("You cannot go through Wall or Occupied Tile");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cant go here");
        }
    }
    
    public Room getRoomOfPlayer(Player p){
        Room room = null;
        boolean roomFound = false; //whether a room that contains the player is found
        int i = 0;
        while(!roomFound && i < getRooms().size()){
            room = getRooms().get(i);
            if (room.checkTileInRoom(currentPlayer.getToken().getTokenLocation())) {
                roomFound = true;
            }
            else {
                room = null;
            }
            i++;
        }
        return room;
    }
    
    public Player getPlayerByCharacterName(String cName){
        Boolean found = false;
        int i = 0;
        Player p = null;
        while(!found && i < playerList.size()){
            if(playerList.get(i).getToken().getName() == cName){
                p = playerList.get(i);
                found = true;
            }
            i++;
        }
        return p;
    }
    
    public void distributeCards(){
        //use predefined list of card names for now
        Card[] pCards = {new Card(CardType.Person,"Miss Scarlett"),new Card(CardType.Person,"Colonel Mustard"),new Card(CardType.Person,"Mrs.White"),
            new Card(CardType.Person,"Mr.Green"),new Card(CardType.Person,"Mrs.Peacock"),new Card(CardType.Person,"Professor Plum")};
        Card[] wCards = {new Card(CardType.Weapon,"Dagger"),new Card(CardType.Weapon,"Candlestick"),new Card(CardType.Weapon,"Revolver"),
            new Card(CardType.Weapon,"Rope"),new Card(CardType.Weapon,"Leadpiping"),new Card(CardType.Weapon,"Spanner")};
        Card[] rCards = {new Card(CardType.Room,"Bathroom"),new Card(CardType.Room,"Diningroom"),new Card(CardType.Room,"kitchen"),
            new Card(CardType.Room,"ballroom"),new Card(CardType.Room,"conservatory"),new Card(CardType.Room,"gamesroom"),
            new Card(CardType.Room,"library"),new Card(CardType.Room,"Hallway"),new Card(CardType.Room,"Office")};
        ArrayList<Card> cList = new ArrayList<>();
        cList.addAll(Arrays.asList(pCards));
        cList.addAll(Arrays.asList(wCards));
        cList.addAll(Arrays.asList(rCards));
        setCardDistributor(cList);
        cardDistributor.setEnvelope();
        cardDistributor.shuffleCards();
        cardDistributor.dealCards(playerList);
        
    }

}
