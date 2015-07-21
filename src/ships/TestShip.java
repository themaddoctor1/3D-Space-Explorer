/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import physics.Coordinate;
import ships.cockpit.BlankScreen;
import ships.cockpit.Cockpit;
import ships.cockpit.ControlItem;
import ships.powergeneration.*;

/**
 *
 * @author Christopher
 */
public class TestShip extends Ship{
    
    protected TestShip(){}
    
    public static Ship buildShip(){
        
        TestShip result = new TestShip();
        
        ControlItem[] controls = new ControlItem[]{
            new BlankScreen(new Coordinate(0.325,-0.2,0.42), Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.325,-0.2,-0.42), -Math.PI/4.0, Math.PI/6.0, 0.4, 0.3)
        };
        
        result.addNewSubsystem(Cockpit.buildCockpit( controls ));
        
        result.addNewSubsystem(new EnergyTesseract());
        
        result.addNewSubsystem(new CapacitantTesseract(Math.pow(10, 20)));
        
        ShipComputer shipComputer = new ShipComputer();
        result.addNewSubsystem(shipComputer);
        
        for(ShipSystem ss : result.subsystems)
            ss.setOwner(result);
        
        
        return result;
        
    }
}
