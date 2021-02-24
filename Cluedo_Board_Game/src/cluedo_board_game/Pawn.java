/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Anilz
 */
public class Pawn extends Circle {
    private String name;
    private boolean agent = true;
    private Tile pawnLocation = null;


    public Pawn(String name) {
        this.name = name;
        setFill(Color.RED);
        setRadius(8);
        setTranslateX(+2);
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

    //For Agent
    public boolean IsAgent() {
        return agent;
    }

    public void setIsAgent(boolean isAgent) {
        this.agent = isAgent;
    }
    
    
    
    
}
