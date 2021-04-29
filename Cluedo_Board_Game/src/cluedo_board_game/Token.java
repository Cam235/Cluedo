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
 * Represents token which is used by a player to move around the board, and be
 * summoned into rooms.
 *
 * It extends to Circle , so a circle represents the token on the board
 *
 * @author Anilz
 * @version 1.0
 */
public class Token extends Circle {

    private String name; // Token name
    private Tile tokenLocation = null; //Token location , initially set to null

    /**
     * Constructor for Token that creates token with desired circle properties.
     *
     * @param name name of the Token
     */
    public Token(String name) {
        this.name = name;
        setFill(Color.RED);
        setRadius(7.5);
        setStrokeWidth(1.5);
        setStrokeMiterLimit(10);
        setStrokeType(StrokeType.CENTERED);
        setStroke(Color.BLACK);
        setTranslateX(+2);
    }

    /**
     * Gets the name of token.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of token.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets tokens location.
     *
     * @return tokenLocation
     */
    public Tile getTokenLocation() {
        return tokenLocation;
    }

    /**
     * Sets Token's location on selected tile.
     *
     * @param tokenLocation
     */
    public void setTokenLocation(Tile tokenLocation) {
        this.tokenLocation = tokenLocation;
        tokenLocation.setOccupied(true);
    }

}
