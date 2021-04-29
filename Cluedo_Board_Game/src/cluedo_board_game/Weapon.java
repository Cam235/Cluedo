/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.image.Image;

/**
 * The weapon class which is for creating and placing game weapons in rooms to
 * indicate previous suggestions.
 *
 * @author Anilz
 * @version 1.0
 */
public class Weapon {

    private String name; // Name of weapon
    private Tile weaponLocation; // Tile Position of weapon
    private Image weaponImage; // Image of weapon

    /**
     * Constructor for weapon.
     *
     * @param name
     */
    public Weapon(String name) {
        this.name = name;

    }

    /**
     * Gets name of weapon.
     *
     * @return name
     */
    public String getName() {
        return name;

    }

    /**
     * Gets the location of weapon.
     *
     * @return
     */
    public Tile getWeaponLocation() {
        return weaponLocation;
    }

    /**
     * Places the weapon on selected tile, and makes the tile occupied.
     *
     * @param newWeaponLocation tile in which weapon is put
     */
    public void setWeaponLocation(Tile newWeaponLocation) {
        //Removes weapon from previous position if it is not null
        if (weaponLocation != null) {
            weaponLocation.setOccupied(false);
        }
        //Puts weapon in new position
        weaponLocation = newWeaponLocation;
        //set the the tile to be occupied
        weaponLocation.setOccupied(true);
    }

    /**
     * Gets weapon Image.
     *
     * @return weaponImage
     */
    public Image getWeaponImage() {
        return weaponImage;
    }

    /**
     * Sets the weapon Image.
     *
     * @param weaponImage
     */
    public void setWeaponImage(Image weaponImage) {
        this.weaponImage = weaponImage;
    }

}
