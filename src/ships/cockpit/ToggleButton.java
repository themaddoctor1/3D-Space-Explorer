/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.shapes.Rectangle3D;
import java.awt.Color;

/**
 *
 * @author Christopher
 */
public class ToggleButton extends Button{
    
    private String[] onProgram, offProgram;
    private boolean alwaysRun;
    
    public ToggleButton(Rectangle3D s, Color on, Color off, String[] onProg, String[] offProg, boolean alwaysRunProg) {
        super(s, on, off);
        onProgram = onProg;
        offProgram = offProg;
        alwaysRun = alwaysRunProg;
    }
    
    @Override
    public void execute(double time, Object... params){
        
        super.execute(time, params);
        
        if(alwaysRun)
            output.executeProgram(getProgram());
        
    }
    
    @Override
    public void interact(Camera c, double time, int x, int y) {
        if(willInteract(c,x,y)){
            if(isOn())
                deactivate();
            else if(isPowered(getEnergyDemand(time)))
                activate();
            output.executeProgram(getProgram());
        }
    }

    @Override
    public String[] getProgram(Object... params) {
        if(isOn()) return onProgram;
        else return offProgram;
    }

    
}
