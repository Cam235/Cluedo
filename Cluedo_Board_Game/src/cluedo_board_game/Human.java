/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;

/**
 *
 * @author Mazon
 */
public class Human extends Player{
    

    public Human(int playerId, String name, Token token) {
        super(playerId, name, token);
    }
    
    @Override
    public Boolean isAgent(){
        return false;
    }
    
    @Override
    public void moveToken(Tile tile) {
       // token.setTokenLocation(Tile tokenLocation);
    }
    
}
