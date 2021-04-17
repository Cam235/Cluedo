/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Represents token which is used to make movements on board
 *
 * @author Anilz
 * @version 1.0
 */
public class Token extends Circle implements TokenInterface{

    //Token name, if boolean or agent, and which tile it does steps on
    private String name;
    //private boolean agent = true;
    private Tile tokenLocation = null;

    /**
     * Constructor for Token
     *
     * @param name name of the Token
     */
    public Token(String name) {
        //Initialise Token, with circle properties
        this.name = name;
        setFill(Color.RED);
        setRadius(7.5);
        setStrokeWidth(1.5);
        setStrokeMiterLimit(10);
        setStrokeType(StrokeType.CENTERED);
        setStroke(Color.BLACK);
        setTranslateX(+2);
        //this.tokenLocation = tokenLocation;
    }

    /**
     * @return the name of Token
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * sets the name of Token
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return location of the Token
     */
    @Override
    public Tile getTokenLocation() {
        return tokenLocation;
    }

    /**
     * sets Token's location on new selected tile
     *
     * @param tokenLocation is the new selected tile
     */
    @Override
    public void setTokenLocation(Tile tokenLocation) {
        this.tokenLocation = tokenLocation;
        tokenLocation.setOccupied(true);
    }

}
