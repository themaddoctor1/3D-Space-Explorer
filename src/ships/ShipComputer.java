/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import gui.Camera;
import java.awt.Graphics;
import ships.cockpit.ControlItem;

/**
 *
 * @author Christopher
 */
public class ShipComputer extends PowerUser{
    
    @Override
    public void execute(double time, Object... params) {
        super.execute(time, params);
    }
    
    public final void executeProgram(String[] program){
        
    }
    
    @Override
    public double getEnergyDemand(double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
