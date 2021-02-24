/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;
//Hey

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Anilz
 */
public class Board {
    
    private Random idGenerator; //used to generate unique player Ids
    private Map<Integer,Player> playerList; //map of players and their Ids
    private CardDistributor cardDistributor; //card distributor for board
       
    Tile[][] tileMap;   //Map of Tiles
    private int columns, rows;  // Parameters
    private Pawn boardPawn;     // Sets the boardPawn

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
        
        playerList = new HashMap<>();
        idGenerator = new Random();
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    //Puts pawn on board
    public Pawn initializePawn(String pawnName, int x, int y) {
        try{
            boardPawn = new Pawn(pawnName);
            boardPawn.setPawnLocation(tileMap[x][y]);
            return boardPawn;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't initialize here ");
            return null;
        }
    }
/*
    public boolean movePawn(Pawn pawn,int x, int y) {
        try {
            if ((tileMap[x][y].getIsOccupied() == false)
                    && ((boardPawn.getPawnLocation().equals(tileMap[x + 1][y]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x - 1][y]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x][y + 1]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x][y - 1])))) {
                //If the pawn above below, right and left or above,of existing place,AND  not occupied, then move the pawn 
                boardPawn.getPawnLocation().setOccupied(false);
                boardPawn.setPawnLocation(tileMap[x][y]);
                return true;
            } else {
                System.out.println("Cannot Move Here!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Dont try again");
        }
        return false;
    }
*/

    @Override
    public String toString() {
        String s = "";
        for (int _h = 0; _h < rows; _h++) {
            for (int _w = 0; _w < columns; _w++) {
                s += tileMap[_w][_h].getIsOccupied() ? "O" : "X";
            }
            s += "\n";
        }
        return s;
    }
    
    
    /**
     * Instantiates the cardDistributor for the board
     * @param cardList a list of card Objects used to instantiate the cardDistributor
     */
    public void setCardDistributor(List<Card> cardList){
        cardDistributor = new CardDistributor(cardList);
    }
    
    /**
     * Instantiates Player objects and adds them to the player map of the board game
     * @param playerNames a list of player name Strings to be made into player Objects and added to the player Map
     */
    public void addPlayers(List<String> playerNames){        
        boolean playerAdded; //represents if a player is added successfully
        int potentialId; //stores potential ids for players
        for(String s:playerNames){ //iterate through a list of player names and generate players for each name
            playerAdded = false;
            while(!playerAdded){ //generate new player ID until a unique one is found so player can be added
                potentialId = idGenerator.nextInt(1000);//ids can be in range 0,999
                if(!playerList.containsKey(potentialId)){ //if id is unique add player to playerList
                    playerList.put(potentialId,new Player(potentialId,s));
                    playerAdded = true;
                }
            }
        }
    }

    public Map<Integer, Player> getPlayerList() {
        return playerList;
    }
}