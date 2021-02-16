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

    Tile[][] tileMap;//Map of Tiles
    int width, height;// Parameters

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

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s += tileMap[i][j].isOccupied() ? "O" : "F";
            }
            s += "\n";
        }
        return s;
    }
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board instance = new Board(5,5);
        System.out.println(instance);
        
    }
    

}
