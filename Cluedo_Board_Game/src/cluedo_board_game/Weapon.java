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
public class Weapon extends Circle {

    private String name; // Name
    private Tile weaponLocation; // Tile Position of weapon
    
    /**
     * constructor for weapon, does not place on any tile yet
     * @param name 
     */
    public Weapon(String name) {
        this.name = name; 
        //Characteristics of Circle
        setFill(Color.GREEN);
        setRadius(5);
    }
    
    /**
     * return name 
     * @return 
     */
    public String getName() {
        return name;

    }
    
    /**
     * return weapon Location
     * @return 
     */
    public Tile getWeaponLocation() {
        return weaponLocation;
    }
    
    /**
     * Places the weapon on selected tile
     * @param weaponLocation 
     */
    public void setWeaponLocation(Tile weaponLocation) {
        this.weaponLocation = weaponLocation;
        //sets the tile to occupied
        weaponLocation.setOccupied(true);
    }

}
