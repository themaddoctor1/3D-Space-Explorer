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
            
            //Like in Python, line spacing needs to be properly formatted.
            "print Not contained in the loop",
            //For loops are the only loops currently available.
            "for 2",
            "    print Layer one of the loop",
            //Loops can be nested inside of other loops.
            "    for 2",
            "        print Layer two of the loop",
            "    endfor",
            "endfor",
            "print Not contained",
            //Values are assigned as so: $[variable name]$ = # [YOUR NUMERIC FUNCTION HERE]
            //Spacing needs to be exactly correct when setting a variable to a value. Numeric functions can be set to be functions of other variables. 
            "$test$ = # 6 * 6",
            "$test$ = # $test$ + $test$^2",
            "print $test$"
            
        };
        
        ControlItem[] controls = new ControlItem[]{
            new BlankScreen(new Coordinate(0.325,-0.2,0.42), Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.4, 0.3),
            new BlankScreen(new Coordinate(0.325,-0.2,-0.42), -Math.PI/4.0, Math.PI/6.0, 0.4, 0.3),
            new ToggleButton(new Rectangle3D(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.04, 0.03), Color.GREEN, Color.RED, testOn, new String[]{"print Off"}, false)
        };
        
        result.addNewSubsystem(Cockpit.buildCockpit( controls ));
        
        result.addNewSubsystem(new EnergyTesseract());
        
        result.addNewSubsystem(new CapacitantTesseract(Math.pow(10, 20)));
        
        ShipComputer shipComputer = new ShipComputer();
        result.addNewSubsystem(shipComputer);
        
        for(ShipSystem ss : result.subsystems)
            ss.setOwner(result);
        
        for(ControlItem ci : controls)
            ci.setOutput(shipComputer);
        
        return result;
        
    }
}
