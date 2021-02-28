/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents pawn which is used to make movements on board
 *
 * @author Anilz
 * @version 1.0
 */
public class Pawn extends Circle {

    //Pawn name, if boolean or agent, and which tile it does steps on
    private String name;
    private boolean agent = true;
    private Tile pawnLocation = null;

    /**
     * Constructor for Pawn
     *
     * @param name name of the Pawn
     */
    public Pawn(String name) {
        //Initialise Pawn, with circle properties
        this.name = name;
        setFill(Color.RED);
        setRadius(8);
        setTranslateX(+2);
        //this.pawnLocation = pawnLocation;
    }

    /**
     * @return the name of Pawn
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of Pawn
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return location of the pawn
     */
    public Tile getPawnLocation() {
        return pawnLocation;
    }

    /**
     * sets Pawn's location on new selected tile
     *
     * @param pawnLocation is the new selected tile
     */
    public void setPawnLocation(Tile pawnLocation) {
        this.pawnLocation = pawnLocation;
        pawnLocation.setOccupied(true);
    }

    /**
     *
     * @return whether pawn is agent or not
     */
    public boolean IsAgent() {
        return agent;
    }

    /**
     *  
     * @param isAgent set pawn to agent if true, else player controls
     */
    public void setIsAgent(boolean isAgent) {
        this.agent = isAgent;
    }

}
