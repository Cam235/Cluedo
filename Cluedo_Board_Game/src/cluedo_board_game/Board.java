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
import java.util.Random;
import java.util.Set;

/**
 * Represents board of the game including players and tokens
 *
 * @author Anilz
 * @version 4.0
 */
public class Board {

    Tile[][] tileMap;   // Map of Tiles that represents gameboard
    private int columns, rows;  // Row and column numbers of the board

    private ArrayList<Room> rooms = new ArrayList<Room>(); // List of rooms on board
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>(); // List of weapons on board 

    private Random idGenerator; // Used to generate unique player Ids
    private ArrayList<Player> playerList; // List of Players
    private CardDistributor cardDistributor; // Card distributor to distribute cards among players

    private Player currentPlayer; //Represents player who has turn currently
    private int counter = 0; //Represents how many times player can move token
    private String alertMsg = "";//Alert message

    /**
     * Constructor to set up the Board Creates a map of tiles as big as column
     * and row size, Also creates player list and IdGenerator
     *
     * @param columns
     * @param rows
     */
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
     * Gets card distributor
     *
     * @return cardDistributor
     */
    public CardDistributor getCardDistributor() {
        return cardDistributor;
    }

    /**
     * Gets tileMap
     *
     * @return tileMap
     */
    public Tile[][] getTileMap() {
        return tileMap;
    }

    /**
     * Gets column size of the board
     *
     * @return columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets row size of the board
     *
     * @return rows
     */
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
     * Initialise a new weapon and add to the list
     *
     * @param weapon
     * @return initialised weapon
     */
    public Weapon initialiseWeapon(String weapon) {
        weapons.add(new Weapon(weapon));
        return weapons.get(weapons.size() - 1);
    }

    /**
     * Moves a selected weapon into a selected room
     *
     * @param room
     * @param weapon
     */
    public void moveWeaponToRoom(Room room, Weapon weapon) {
        //If weapon is already in any room, remove from that room
        for (Room potentialRoom : rooms) {
            if (potentialRoom.getRoomWeapons().contains(weapon)) {
                potentialRoom.getRoomWeapons().remove(weapon);
            }
        }
        //Add the new Room 
        room.addRoomWeapon(weapon);
    }

    /**
     * Returns list of rooms
     *
     * @return rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Initialise a new Room on the board
     *
     * @param name roomName
     * @param roomSpace list of tiles which will create roomSpace
     * @param roomDoors list of doors of room
     * @return newRoom
     */
    public Room initialiseRoom(String name, ArrayList<Tile> roomSpace, ArrayList<Tile> roomDoors) {
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
            System.out.println("You can't initialise here ");
            return null;
        }
    }

    /**
     * Initialise token for each playing and non-playing players on board
     *
     * @param player
     * @param tokenName
     */
    public void initialisePlayerToken(Player player, String tokenName) {
        try {
            Token token = new Token(tokenName);
            player.setToken(token);
        } catch (Exception e) {
            System.out.println("Cannot initialise");
        }
    }

    /**
     * @return textual representation of board
     */
    public String toString() {
        String s = "";
        for (int _h = 0; _h < rows; _h++) {
            for (int _w = 0; _w < columns; _w++) {
                if ((rooms != null) && !tileMap[_w][_h].isOccupied()) {
                    //iterate through each room
                    for (Room room : rooms) {
                        if (room.checkTileInRoom(tileMap[_w][_h])) {
                            s += "R";
                        }
                    }
                    s += "R";
                } else if (tileMap[_w][_h].isOccupied()) {
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

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Moves the current player to coordinates x y as long as dice is rolled and
     * counter is less that dice total, increments counter if move is made, max
     * out counter if player enters room
     *
     * @param x
     * @param y
     * @param diceRolled
     * @param diceTotal
     */
    public void moveCurrentPlayer(int x, int y, boolean diceRolled, int diceTotal) {
        Tile currentPlayerPos = getCurrentPlayer().getToken().getTokenLocation();
        if (diceRolled) {
            if ((counter < diceTotal)) {
                if (getRoomOfPlayer(currentPlayer) != null && !currentPlayer.isAgent()) {
                    alertMsg = "Choose door number to exit";
                } else {
                    movePlayer(currentPlayer, x, y);
                    if (!currentPlayerPos.equals(getCurrentPlayer().getToken().getTokenLocation())) {
                        alertMsg = getCurrentPlayer().getName() + " Moves To " + getCurrentPlayer().getToken().getTokenLocation().getColIndex()
                                + "," + getCurrentPlayer().getToken().getTokenLocation().getRowIndex();
                        counter++;
                    }
                    if ((getRoomOfPlayer(getCurrentPlayer())) != null) {
                        alertMsg = getCurrentPlayer().getName() + " Is In " + getRoomOfPlayer(getCurrentPlayer()).getName();
                        counter = diceTotal;
                    }
                }
            } else {
                alertMsg = "Please End Turn";
            }
        } else {
            alertMsg = "Please Roll the Dice";
        }
    }

    /**
     * Orders the player list based on the name of their character token in the
     * order: "Miss Scarlett","Colonel
     * Mustard","Mrs.White","Mr.Green","Mrs.Peacock","Professor Plum"
     */
    public void orderPlayerList() {
        ArrayList<Player> tempList = new ArrayList<>();
        //creates array of character names in appropriate order
        String[] playerNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};
        //gets each player in the correct order using playerNames and stores them in list
        for (String name : playerNames) {
            if (getPlayerByCharacterName(name) != null) {
                tempList.add(getPlayerByCharacterName(name));
            } //if any player is missing alert console
            else {
                //in actual game this should never happen
                System.out.println("missing character " + name);
            }
        }
        //reset Player list then set it to tempList
        playerList = new ArrayList<>();
        playerList = tempList;
    }

    public void initialiseWeapons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Increments the current Player to the next playing player
     */
    public void incrementCurrentPlayer() {
        currentPlayer = getNextActivePlayer(currentPlayer);
    }

    /**
     * returns the next playing player from a given player p
     *
     * @param p
     * @return the next playing player
     */
    public Player getNextActivePlayer(Player p) {
        if (playerList.contains(p)) {
            int i = 0;
            do {
                if (playerList.indexOf(p) == playerList.size() - 1) {
                    p = playerList.get(0);
                } else {
                    p = playerList.get(playerList.indexOf(p) + 1);
                }
                i++;
            } while (!p.getIsPlaying() && i < playerList.size());
            if (p.getIsPlaying()) {
                return p;
            } else {
                System.out.println("No remaining active players");
                return null;
            }
        } else {
            System.out.println("Player is not in PlayerList");
            return null;
        }
    }

    /**
     * Returns to current player
     *
     * @return currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current Player
     *
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * moves a given player to a random space in a given room
     *
     * @param player
     * @param room
     */
    public void playerEntersRoom(Player player, Room room) {
        //move player to a random free tile in the room
        int i = (int) (Math.random() * room.getFreeSpace().size());
        int newX = room.getFreeSpace().get(i).getColIndex();
        int newY = room.getFreeSpace().get(i).getRowIndex();
        player.getToken().getTokenLocation().setOccupied(false);
        if (player == currentPlayer) {
            for (int j = 0; j < room.getRoomDoors().size(); j++) {
                room.getRoomDoors().get(j).setText("" + (j + 1));
            }
        }
        player.moveToken(getTileMap()[newX][newY]);
    }

    /**
     * move a specified player p to a specified set of coordinates x and y only
     * allows valid moves and handles agent player enter/exit room
     *
     * @param p
     * @param x
     * @param y
     */
    public void movePlayer(Player p, int x, int y) {
        try {
            Room playerRoom = getRoomOfPlayer(p);
            //if agent player is exiting room get a random door from room doors
            if (playerRoom != null && p.isAgent()) {
                Random random = new Random();
                int doorToExit = random.nextInt(playerRoom.getRoomDoors().size());
                int exitX = playerRoom.getRoomDoors().get(doorToExit).getColIndex();
                int exitY = playerRoom.getRoomDoors().get(doorToExit).getRowIndex();
                p.getPreviousPath().add(tileMap[exitX][exitY]);
                //use the coordinates of the door exit to move player
                p.getToken().getTokenLocation().setOccupied(false);
                if (!getDoorExit(tileMap[exitX][exitY]).isOccupied()) {
                    //also remove door text
                    for (Tile t : playerRoom.getRoomDoors()) {
                        t.setText("");
                    }
                    p.moveToken(getDoorExit(tileMap[exitX][exitY]));
                }
            } //If the tile to be moved is not wall or occupied
            else if (!tileMap[x][y].isWall() && !tileMap[x][y].isOccupied()) {
                //if the tile is a door
                if (tileMap[x][y].isDoor()) {
                    //find the room that the door belongs to
                    for (Room room : getRooms()) {
                        if (room.getRoomDoors().contains(tileMap[x][y])) {
                            //allow player to enter room
                            playerEntersRoom(currentPlayer, room);
                        }
                    }
                } else {
                    //if the tile is not a wall or door then just make move
                    p.getToken().getTokenLocation().setOccupied(false);
                    p.moveToken(tileMap[x][y]);
                }
            } else {
                //if the tile to be moved is a wall or occupied don't move 
                System.out.println("You cannot go through Wall or Occupied Tile");
                alertMsg = "You cannot go through walls or occupied tiles!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cant go here");
            alertMsg = "Invalid Move!";
        }
    }

    /**
     * returns the current room of player p if they are in a room, null
     * otherwise
     *
     * @param p
     * @return null or player p's room
     */
    public Room getRoomOfPlayer(Player p) {
        return getRoomOfTile(p.getToken().getTokenLocation());
    }

    /**
     * returns a player p given a character name cName or null if the player
     * doesn't exist
     *
     * @param cName
     * @return null or Player p
     */
    public Player getPlayerByCharacterName(String cName) {
        Boolean found = false;
        int i = 0;
        Player p = null;
        while (!found && i < playerList.size()) {
            if (playerList.get(i).getToken().getName().equals(cName)) {
                p = playerList.get(i);
                found = true;
            }
            i++;
        }
        return p;
    }

    /**
     * Utilises the card distributor object to distribute cards to each playing
     * player fairly, current build uses a predefined list of card names
     */
    public void distributeCards() {
        //use predefined list of card names for now
        Card[] pCards = {new Card(CardType.Person, "Miss Scarlett"), new Card(CardType.Person, "Colonel Mustard"), new Card(CardType.Person, "Mrs.White"),
            new Card(CardType.Person, "Mr.Green"), new Card(CardType.Person, "Mrs.Peacock"), new Card(CardType.Person, "Professor Plum")};
        Card[] wCards = {new Card(CardType.Weapon, "Dagger"), new Card(CardType.Weapon, "Candlestick"), new Card(CardType.Weapon, "Revolver"),
            new Card(CardType.Weapon, "Rope"), new Card(CardType.Weapon, "Leadpiping"), new Card(CardType.Weapon, "Spanner")};
        Card[] rCards = {new Card(CardType.Room, "Lounge"), new Card(CardType.Room, "Diningroom"), new Card(CardType.Room, "Kitchen"),
            new Card(CardType.Room, "Ballroom"), new Card(CardType.Room, "Conservatory"), new Card(CardType.Room, "Billiardroom"),
            new Card(CardType.Room, "Library"), new Card(CardType.Room, "Hall"), new Card(CardType.Room, "Study")};
        ArrayList<Card> cList = new ArrayList<>();
        cList.addAll(Arrays.asList(pCards));
        cList.addAll(Arrays.asList(wCards));
        cList.addAll(Arrays.asList(rCards));

        setCardDistributor(cList); //Sets card distributor with card values above
        cardDistributor.setEnvelope(); // Sets the envelope cards,which will be murder cards for the game
        cardDistributor.shuffleCards();// Shuffles the rest of the cards
        cardDistributor.dealCards(playerList);// Deal the cards between active(playing) players

    }

    /**
     * Gets the counter, how many times player can move token
     *
     * @return counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets the counter value
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Gets the alert message
     * @return alertMsg
     */
    public String getAlertMsg() {
        return alertMsg;
    }

    /**
     * Gets door to exit
     * @param door
     * @return exitDoor tile
     */
    public Tile getDoorExit(Tile door) {
        if (!door.isDoor()) {
            System.out.println("Tile is not a door");
            return null;
        } else {
            if (getRoomOfTile(tileMap[door.getColIndex() + 1][door.getRowIndex()]) == null && !tileMap[door.getColIndex() + 1][door.getRowIndex()].isWall()) {
                return (tileMap[door.getColIndex() + 1][door.getRowIndex()]);
            } else if (getRoomOfTile(tileMap[door.getColIndex() - 1][door.getRowIndex()]) == null && !tileMap[door.getColIndex() - 1][door.getRowIndex()].isWall()) {
                return (tileMap[door.getColIndex() - 1][door.getRowIndex()]);
            } else if (getRoomOfTile(tileMap[door.getColIndex()][door.getRowIndex() + 1]) == null && !tileMap[door.getColIndex()][door.getRowIndex() + 1].isWall()) {
                return (tileMap[door.getColIndex()][door.getRowIndex() + 1]);
            } else if (getRoomOfTile(tileMap[door.getColIndex()][door.getRowIndex() - 1]) == null && !tileMap[door.getColIndex()][door.getRowIndex() - 1].isWall()) {
                return (tileMap[door.getColIndex()][door.getRowIndex() - 1]);
            } else {
                System.out.println("No valid tile to move to");
                return null;
            }
        }
    }

    /**
     * returns the current room of tile t if it is in a room, null otherwise
     *
     * @param t
     * @return null or room t's room
     */
    private Room getRoomOfTile(Tile t) {
        boolean found = false;
        int i = 0;
        Room r = null;
        while (!found && i < rooms.size()) {
            if (rooms.get(i).getRoomSpace().contains(t) || rooms.get(i).getRoomDoors().contains(t)) {
                r = rooms.get(i);
                found = true;
            }
            i++;
        }
        return r;
    }

    /**
     * Function is for player to exit room properly,and when doors are not
     * blocked, and force to stay in room if door is blocked by another player
     *
     * @param i
     * @param diceRolled parameter to identify if the dice is rolled. Player
     * cannot exit room without rolling dice
     * @param diceTotal
     */
    public void currentPlayerExitsRoom(int i, boolean diceRolled, int diceTotal) {
        Tile currentPlayerPos = getCurrentPlayer().getToken().getTokenLocation();
        if (diceRolled) {
            if ((counter < diceTotal)) {
                Room r = getRoomOfPlayer(currentPlayer);
                if (r != null) {
                    if (i <= r.getRoomDoors().size()) {
                        int x = getDoorExit(r.getRoomDoors().get(i - 1)).getColIndex();
                        int y = getDoorExit(r.getRoomDoors().get(i - 1)).getRowIndex();
                        if (!tileMap[x][y].isOccupied()) {
                            movePlayer(currentPlayer, x, y);
                            alertMsg = getCurrentPlayer().getName() + " Moves To " + getCurrentPlayer().getToken().getTokenLocation().getColIndex()
                                    + "," + getCurrentPlayer().getToken().getTokenLocation().getRowIndex();
                            for (Tile t : r.getRoomDoors()) {
                                t.setText("");
                            }
                            if (!currentPlayerPos.equals(getCurrentPlayer().getToken().getTokenLocation())) {
                                counter++;
                            }
                            if ((getRoomOfPlayer(getCurrentPlayer())) != null) {
                                alertMsg = getCurrentPlayer().getName() + " Is In " + getRoomOfPlayer(getCurrentPlayer()).getName();
                                counter = diceTotal;
                            }
                        } else {
                            alertMsg = "Door is blocked!";
                        }
                    } else {
                        alertMsg = "Operation not currently valid";
                    }
                } else {
                    alertMsg = "Operation not currently valid";
                }
            } else {
                alertMsg = "Please End Turn";
            }
        } else {
            alertMsg = "Please Roll The Dice";
        }
    }

    /**
     * Initialises the player detective cards for all playing players, using a
     * predefined list of characters, weapons and rooms, initialises all the
     * check list values as false and case notes values as empty
     */
    public void initialisePlayerDetectiveCards() {
        String[] characterNames = {"Miss Scarlett", "Colonel Mustard", "Mrs.White", "Mr.Green", "Mrs.Peacock", "Professor Plum"};
        String[] weaponNames = {"Dagger", "Candlestick", "Revolver", "Rope", "Leadpiping", "Spanner"};
        String[] roomNames = {"Lounge", "Diningroom", "Kitchen", "Ballroom", "Conservatory", "Billiardroom", "Library", "Hall", "Study"};
        for (Player p : playerList) {
            if (p.getIsPlaying()) {
                HashMap<String, Boolean> currDetectCard = new HashMap<>();
                String currString = "";

                for (String c : characterNames) {
                    currDetectCard.put(c, Boolean.FALSE);
                }
                for (String w : weaponNames) {
                    currDetectCard.put(w, Boolean.FALSE);
                }
                for (String r : roomNames) {
                    currDetectCard.put(r, Boolean.FALSE);
                }
                p.setDetectiveChecklist(currDetectCard);
                p.setDetectiveNotes(currString);

                if (p.isAgent()) {
                    //auto update agent players detective card
                    p.markHandAsSeen();
                }
            }
        }
    }

    /**
     * Returns a decision for what the current players turn should be, assuming
     * the current player is an agent
     *
     * @return string representing turn decision
     */
    public String getAgentTurn() {
        Room currentPlayerRoom = getRoomOfPlayer(currentPlayer);
        if (currentPlayer.getUnseenCards().size() < 4) {
            //make accusation
            return "Accuse";
        } else if (currentPlayerRoom == null) {
            if (isPlayerTrapped(currentPlayer)) {
                //skip turn
                return "Skip";
            } else {
                //move token
                return "Move";
            }
        } else if (currentPlayerRoom.getName().equals(currentPlayer.getMostRecentlySuggestedRoom())) {
            //move token
            return "Move";
        } else {
            //make suggestion
            return "Suggest";
        }
    }

    /**
     * Given a string s return a room with a name equal to s if one exists, else
     * null
     *
     * @param s
     * @return room or null
     */
    public Room getRoomFromName(String s) {
        int i = 0;
        boolean found = false;
        Room r = null;
        while (!found && i < rooms.size()) {
            if (rooms.get(i).getName().equals(s)) {
                r = rooms.get(i);
                found = true;
            }
            i++;
        }
        return r;
    }

    /**
     * Given a player p, returns whether the player is trapped by checking its
     * adjacent tiles to see if there is one that the player can move to
     *
     * @param p
     * @return boolean for whether player is trapped
     */
    private boolean isPlayerTrapped(Player p) {
        boolean trapped = true;
        int x = p.getToken().getTokenLocation().getColIndex();
        int y = p.getToken().getTokenLocation().getRowIndex();
        if ((x - 1) > -1 && (x - 1) < 28) {
            if (!tileMap[x - 1][y].isOccupied() && !tileMap[x - 1][y].isWall()) {
                trapped = false;
            }
        }
        if ((x + 1) > -1 && (x + 1) < 28) {
            if (!tileMap[x + 1][y].isOccupied() && !tileMap[x + 1][y].isWall()) {
                trapped = false;
            }
        }
        if ((y - 1) > -1 && (y - 1) < 28) {
            if (!tileMap[x][y - 1].isOccupied() && !tileMap[x][y - 1].isWall()) {
                trapped = false;
            }
        }
        if ((y + 1) > -1 && (y + 1) < 28) {
            if (!tileMap[x][y + 1].isOccupied() && !tileMap[x][y + 1].isWall()) {
                trapped = false;
            }
        }
        return trapped;
    }
    
    /**
     * given a player p, assumed to be an agent, return whether they must retrace a move
     * in a given turn due to it being the only valid move to take, this method should be run at 
     * some point after having run isPlayerTrapped()
     * 
     * @param p
     * @return boolean for whether player is blocked
     */
    public boolean isPlayerBlockedByPreviousPath(Player p) {
        boolean blocked = true;
        int x = p.getToken().getTokenLocation().getColIndex();
        int y = p.getToken().getTokenLocation().getRowIndex();
        if ((x - 1) > -1 && (x - 1) < 28) {
            if (!tileMap[x - 1][y].isOccupied() && !tileMap[x - 1][y].isWall() && !p.getPreviousPath().contains(tileMap[x - 1][y])) {
                blocked = false;
            }
        }
        if ((x + 1) > -1 && (x + 1) < 28) {
            if (!tileMap[x + 1][y].isOccupied() && !tileMap[x + 1][y].isWall() && !p.getPreviousPath().contains(tileMap[x + 1][y])) {
                blocked = false;
            }
        }
        if ((y - 1) > -1 && (y - 1) < 28) {
            if (!tileMap[x][y - 1].isOccupied() && !tileMap[x][y - 1].isWall() && !p.getPreviousPath().contains(tileMap[x][y - 1])) {
                blocked = false;
            }
        }
        if ((y + 1) > -1 && (y + 1) < 28) {
            if (!tileMap[x][y + 1].isOccupied() && !tileMap[x][y + 1].isWall() && !p.getPreviousPath().contains(tileMap[x][y + 1])) {
                blocked = false;
            }
        }
        return blocked;
    }
}
