/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo_board_game;

import javafx.scene.image.Image;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sc857
 */
public class WeaponTest {
    
    private Weapon testWeapon;
    private String testName;
    private Tile testLocation;
    
    @Before
    public void setUp() throws Exception {
        testName = "tWeapon";
        testWeapon = new Weapon(testName);
        testLocation = new Tile(1,1);
    }
    
    @Test
    public void testGetName(){
        //Test value of string after being set
        assertEquals(testWeapon.getName(),testName);
    }
    @Test
    public void testGetWeaponLocation(){
        //test default value of location being null
        assertEquals(testWeapon.getLocation(),testLocation);
    }
    @Test
    public void testSetWeaponLocation(){
        Tile testNewLocation = new Tile(2,2);
        testWeapon.setLocation(testNewLocation);
        //Individually test column and row values of weapon location after being set
        assertEquals(testWeapon.getLocation().getColIndex(),testNewLocation.getColIndex());
        assertEquals(testWeapon.getLocation().getRowIndex(),testNewLocation.getRowIndex());
    }
    /**
     *Get and Set Image methods not tested
     */
}
