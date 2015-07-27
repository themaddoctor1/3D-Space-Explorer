/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import gui.shapes.Rectangle3D;
import java.awt.Color;
import physics.Coordinate;
import ships.cockpit.BlankScreen;
import ships.cockpit.Cockpit;
import ships.cockpit.ControlItem;
import ships.cockpit.ToggleButton;
import ships.powergeneration.*;

/**
 *
 * @author Christopher
 */
public class TestShip extends Ship{
    
    protected TestShip(){}
    
    public static Ship buildShip(){
        
        TestShip result = new TestShip();
        
        String[] testOn = {
            "System[1] activate| ",
            "System[2] activate| ",
            "System[3] activate| "
        };
        
        String[] testOff = {
            "System[1] deactivate| ",
            "System[2] deactivate| ",
            "System[3] deactivate| "
        };
        
        
        ShipComputer shipComputer = new ShipComputer();
        result.addNewSubsystem(shipComputer);
        shipComputer.addDevice(result);
        
        ControlItem[] controls = new ControlItem[]{
            new BlankScreen(new Coordinate(0.325,-0.2,0.42), Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.325,-0.2,-0.42), -Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new ToggleButton(new Rectangle3D(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.04, 0.03), Color.GREEN, Color.RED, testOn,testOff, false)
        };
        
        for(ControlItem ci : controls)
            shipComputer.addDevice(ci);
        
        result.addNewSubsystem(Cockpit.buildCockpit( controls ));
        
        result.addNewSubsystem(new EnergyTesseract());
        
        result.addNewSubsystem(new CapacitantTesseract(Math.pow(10, 20)));
        
        for(ShipSystem ss : result.subsystems)
            ss.setOwner(result);
        
        for(ControlItem ci : controls)
            ci.setOutput(shipComputer);
        
        return result;
        
    }
}
