/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import gui.shapes.Rectangle3D;
import java.awt.Color;
import physics.Coordinate;
import ships.cockpit.*;
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
                "System[1] toggle| ",
                //"System[2] toggle| ",
                "System[4] toggle| "
        };
        
        String[] testOff = {
                "System[1] toggle| ",
                //"System[2] toggle| ",
                "System[4] toggle| "
        };
        
        
        ShipComputer shipComputer = new ShipComputer();
        result.addNewSubsystem(shipComputer);
        shipComputer.addDevice(result);
        
        ControlItem[] controls = new ControlItem[]{
            new BlankScreen(new Coordinate(0.325,-0.2,0.42), Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.325,-0.2,-0.42), -Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            null
        };

        controls[3] = new ToggleButton(new Rectangle3D(((Screen) controls[1]).getSurfacePosition(0.5,1.05), 0, Math.PI/6.0, 0.04, 0.03), Color.GREEN, Color.RED, testOn,testOff, false);

        ((Screen) controls[1]).addSubitem(new ToggleButton(new Rectangle3D(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.04, 0.03), Color.GREEN, Color.RED, testOn,testOff, false), 180, 135);
        ((Screen) controls[1]).getSubitems().get(0).setOwner(controls[1]);

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
