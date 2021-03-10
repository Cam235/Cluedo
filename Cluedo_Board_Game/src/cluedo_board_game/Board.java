/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;
//Hey

import java.util.ArrayList;
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
public class Board implements BoardInterface{

    //These should not be on board 
    private Random idGenerator; //used to generate unique player Ids
    private ArrayList<Player> playerList; //map of players and their Ids
    private CardDistributor cardDistributor; //card distributor for board
    //To measure steps not surpassing value of dice
    private int counter = 0;

    Tile[][] tileMap;   //Map of Tiles
    private int columns, rows;  // Parameters
    private Token boardToken;     // the boardToken
    private ArrayList<Room> rooms = new ArrayList<Room>(); // Sets Up the rooms
    // private ArrayList<Token> tokens;
    
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
    
    @Override
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Initialize token on specific locations
     *
     * @param tokenName
     * @param x
     * @param y
     * @return
     */
    @Override
    public Token initializeToken(String tokenName, int x, int y) {
        try {
            boardToken = new Token(tokenName);
            boardToken.setTokenLocation(tileMap[x][y]);
            return boardToken;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't initialize here ");
            return null;
        }
    }
    
    @Override
    public void initializeRoom(String name, ArrayList<Tile> roomSpace, ArrayList<Tile> roomDoors) {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't initialize here ");
            //return null;
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
    public void addPlayers(List<String> playerNames) {
        boolean playerAdded; //represents if a player is added successfully
        int potentialId; //stores potential ids for players
        Set<Integer> playerIds = new HashSet<>();
        for (String s : playerNames) { //iterate through a list of player names and generate players for each name
            playerAdded = false;
            while (!playerAdded) { //generate new player ID until a unique one is found so player can be added
                potentialId = idGenerator.nextInt(1000);//ids can be in range 0,999
                if (!playerIds.contains(potentialId)) { //if id is unique add player to playerList
                    playerList.add(new Player(potentialId, s));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initializeWeapons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * increments the current Player
     */
    @Override
    public void incrementCurrentPlayer(){
        if(!playerList.contains(currentPlayer)){
            System.out.println("Current Player Not Initialised");
        }
        else if(playerList.indexOf(currentPlayer) == playerList.size()-1){
            currentPlayer = playerList.get(0);
        }
        else{
            currentPlayer = playerList.get(playerList.indexOf(currentPlayer)+1);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    
    
    @Override
    public void moveToken(Token token, int x, int y) {
        if (diceRoller.isDiceRolled() && (counter < diceRoller.getDiceTotal())) {
            try {
                //If the tile to be moved is not Wall,confirm movement
                if (!board.getTileMap()[x][y].getIsWall()) {
                    //If the board tile to be moved is door then,loop through rooms
                    if (board.getTileMap()[x][y].getIsDoor()) {
                        for (Room room : board.getRooms()) {
                            //if room contains the door to be moved
                            if (room.getRoomDoors().contains(board.getTileMap()[x][y])) {
                                //And if the player has not in room yet
                                if (!room.getRoomSpace().contains(token.getTokenLocation())) {
                                    //Prints which room of entry
                                    System.out.println("You are at entering the " + room.getRoomName());
                                    //Hit the door
                                    token.getTokenLocation().setOccupied(false);
                                    token.setTokenLocation(board.getTileMap()[x][y]);
                                    //And spawn token outside the door
                                    spawnTokenInRoom(token, room);
                                    //Ends token movements
                                    counter = diceRoller.getDiceTotal();
                                } else {
                                    //If it is already in room, then do not spawn in room
                                    token.getTokenLocation().setOccupied(false);
                                    token.setTokenLocation(board.getTileMap()[x][y]);
                                }
                            }
                        }
                    } else {
                        //if neither wall nor door, make just a movement
                        token.getTokenLocation().setOccupied(false);
                        token.setTokenLocation(board.getTileMap()[x][y]);
                        counter++;
                    }
                    //if tile to be moved is wall , then cannot move    
                } else {
                    System.out.println("You cannot go through Wall");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You cant go here");
            }
        } else {
            System.out.println("Please Roll the Dice");
            //Sets Counter to 0
            counter = 0;
            //Set Dice Rolled to false and Enables DiceRoller
            diceRoller.setDiceRolled(false);
            diceRoller.enableDiceRollerButton();
        }
    
}
