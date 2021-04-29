/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

/**
 * Represents a human player in the board game.
 *
 * @author Mazon
 * @version 4.0
 */
public class Human extends Player {

    /**
     * Constructor for human player Creates a human player and assigns specified
     * token to it.
     *
     * @param playerId
     * @param name
     */
    public Human(int playerId, String name) {
        super(playerId, name);
        this.token = super.getToken();
    }

    /**
     * Sets the player as not agent.
     *
     * @return false
     */
    @Override
    public Boolean isAgent() {
        return false;
    }

    /**
     * A method to move player token to specified tile.
     *
     * @param tile
     */
    @Override
    public void moveToken(Tile tile) {
        this.token.setTokenLocation(tile);
    }

}
