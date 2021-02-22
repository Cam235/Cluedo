/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Player {
    
    private int playerId; //uniquely identifies a player
    private String name; //display name for player, doesn't have to be unique
    private ArrayList<Card> hand; //the players hand of cards
    
    public Player(int playerId, String name){
        this.playerId = playerId;
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
      
}
