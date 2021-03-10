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
public class Agent extends Player{
    
    Token token;
    
    public Agent(int playerId, String name) {
        super(playerId, name);
        token = super.getToken();
    }
    
    @Override
    public Boolean isAgent(){
        return true;
    }
    
    @Override
    public void moveToken(Tile tile) {
        throw new UnsupportedOperationException("Not supported yet");
    }
    
}
