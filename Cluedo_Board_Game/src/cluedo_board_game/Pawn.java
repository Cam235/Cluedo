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
public class Pawn {
    private String name;
    private Tile pawnLocation = null;
 //   private boolean isAgent = false;

    public Pawn(String name) {
        this.name = name;
   //     this.pawnLocation = pawnLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile getPawnLocation() {
        return pawnLocation;
    }

    public void setPawnLocation(Tile pawnLocation) {
        this.pawnLocation = pawnLocation;
        pawnLocation.setOccupied(true);
    }
    
}
