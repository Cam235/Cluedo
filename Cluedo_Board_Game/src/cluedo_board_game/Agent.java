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
 * @author Mazon
 */
public class Agent extends Player{
    
    public Agent(int playerId, String name) {
        super(playerId, name);
        this.token = super.getToken();
    }
    
    @Override
    public Boolean isAgent(){
        return true;
    }
    
    @Override
    public void moveToken(Tile tile) {
        this.token.setTokenLocation(tile);
    }
    
    /**
     * generates a random set of coords adjacent to the coords x y for agent movement
     * @param x x axis coord
     * @param y y axis coord
     * @return random coords adjacent to x y
     */
    @Override
    public Integer[] getMove(int x, int y){
        Integer[] coords = new Integer[2];
        Random random = new Random();
        int movement = random.nextInt(4);
        switch (movement) { 
            case 0:
                coords[0] = x;
                coords[1] = y-1;
                break;
            case 1:
                coords[0] = x;
                coords[1] = y+1;
                break;
            case 2:
                coords[0] = x-1;
                coords[1] = y;
                break;
            default:
                coords[0] = x+1;
                coords[1] = y;
                break;
        }
        return coords;           
    }
    
}
