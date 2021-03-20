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
        this.token = super.getToken();
    }
    
    @Override
    public Boolean isAgent(){
        return true;
    }
    
    @Override
    public void moveToken(Tile tile) {
        /**
     * Makes random movements for AI token on the board
     *
     * public void positionUpdateAI() {
        * (token.isAgent()) { Random random = new Random(); int movement =
        * random.nextInt(4); switch (movement) { case 0: moveToken(token,
        * (token.getTokenLocation().getColIndex()),
        * (token.getTokenLocation().getRowIndex() - 1));
        * System.out.println(token.getTokenLocation().getColIndex() + "," +
        * token.getTokenLocation().getRowIndex()); break; case 1: moveToken(token,
        * token.getTokenLocation().getColIndex(),
        * (token.getTokenLocation().getRowIndex() + 1));
        * System.out.println(token.getTokenLocation().getColIndex() + "," +
        * token.getTokenLocation().getRowIndex()); break; case 2: moveToken(token,
        * token.getTokenLocation().getColIndex() - 1,
        * (token.getTokenLocation().getRowIndex()));
        * System.out.println(token.getTokenLocation().getColIndex() + "," +
        * token.getTokenLocation().getRowIndex()); break; case 3: moveToken(token,
        * token.getTokenLocation().getColIndex() + 1,
        * (token.getTokenLocation().getRowIndex()));
        * System.out.println(token.getTokenLocation().getColIndex() + "," +
        * token.getTokenLocation().getRowIndex()); break; }
        * System.out.println(counter); updateView(); } }
     */
        
        throw new UnsupportedOperationException("Not supported yet");
    }
    
}
