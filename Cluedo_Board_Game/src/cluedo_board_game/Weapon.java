/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Anilz
 */
public class Weapon extends Circle {

    private String name; // Name
    private Tile weaponLocation; // Tile Position of weapon
    private Image weaponImage; // Images of weapon

    /**
     * constructor for weapon, does not place on any tile yet
     *
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
     *
     * @return
     */
    public String getName() {
        return name;

    }

    /**
     * return weapon Location
     *
     * @return
     */
    public Tile getWeaponLocation() {
        return weaponLocation;
    }

    /**
     * Places the weapon on selected tile
     *
     * @param newWeaponLocation
     */
    public void setWeaponLocation(Tile newWeaponLocation) {
        //if its not null , set the previous position to null
        if (weaponLocation != null) {
            weaponLocation.setOccupied(false);
        }
        //new location is set to weapon location
        weaponLocation = newWeaponLocation;
        //set the the tile to be occupied
        weaponLocation.setOccupied(true);
    }

    /**
     * returns weapon image
     *
     * @return
     */
    public Image getWeaponImage() {
        return weaponImage;
    }

    /**
     * sets the weapon Image
     *
     * @param weaponImage
     */
    public void setWeaponImage(Image weaponImage) {
        this.weaponImage = weaponImage;
    }

}
