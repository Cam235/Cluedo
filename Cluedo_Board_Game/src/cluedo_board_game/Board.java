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
public class Board {

    private Tile[][] tileMap;   //Map of Tiles
    private int width, height;  // Parameters
    private Pawn boardPawn;     // Sets the boardPawn

    //Sets Up the Board of Tiles
    public Board(int width, int height) {
        // fields determining width and height of map
        this.width = width;
        this.height = height;
        // initiate tileMap 
        tileMap = new Tile[width][height];
        for (int _height = 0; _height < height; _height++) {
            for (int _width = 0; _width < width; _width++) {
                tileMap[_width][_height] = new Tile();
            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //Puts pawn on board
    public Pawn initializePawn(String pawnName, int x, int y) {
        if (x >= 0 && x < width && y < height && y >= 0) {
            boardPawn = new Pawn(pawnName);
            boardPawn.setPawnLocation(tileMap[x][y]);
            return boardPawn;
        } else {
            System.out.println("You can't initialize here bro");
            return null;
        }

    }

    public void movePawn(int x, int y) {
        try {
            if ((tileMap[x][y].getOccupied() == false)
                    && ((boardPawn.getPawnLocation().equals(tileMap[x + 1][y]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x - 1][y]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x][y + 1]))
                    || (boardPawn.getPawnLocation().equals(tileMap[x][y - 1])))) {
                //If the pawn above below, right and left or above,of existing place,AND  not occupied, then move the pawn 
                boardPawn.getPawnLocation().setOccupied(false);
                boardPawn.setPawnLocation(tileMap[x][y]);
            } else {
                System.out.println("Cannot Move Here!");
            }
        } catch(ArrayIndexOutOfBoundsException e)  {
            System.out.println("Dont try again");           
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int _h = 0; _h < height; _h++) {
            for (int _w = 0; _w < width; _w++) {
                s += tileMap[_w][_h].getOccupied() ? "O" : "X";
            }
            s += "\n";
        }
        return s;
    }
}