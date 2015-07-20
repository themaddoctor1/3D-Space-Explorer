/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import ships.cockpit.TestCockpit;
import ships.powergeneration.*;

/**
 *
 * @author Christopher
 */
public class TestShip extends Ship{
    
    protected TestShip(){}
    
    public static Ship buildShip(){
        
        TestShip result = new TestShip();
        
        result.addNewSubsystem(new TestCockpit());
        result.addNewSubsystem(new EnergyTesseract());
        result.addNewSubsystem(new CapacitantTesseract(Math.pow(10, 10)));
        
        
        return result;
        
    }
}
