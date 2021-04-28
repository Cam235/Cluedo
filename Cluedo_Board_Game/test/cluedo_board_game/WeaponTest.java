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
    
    @Before
    public void setUp() throws Exception {
        testName = "tWeapon";
        testWeapon = new Weapon(testName);
    }
    
    @Test
    public void testGetName(){
        //Test value of string after being set
        assertEquals(testWeapon.getName(),testName);
    }
}
