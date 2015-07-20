/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import physics.Coordinate;
import ships.PowerUser;
import ships.Ship;
import ships.ShipSystem;

/**
 *
 * @author Christopher
 */
public abstract class Cockpit extends PowerUser {
    
    protected ArrayList<ControlItem> controls = new ArrayList<>();
    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        drawDefaultVisuals(g, new Camera(new Coordinate(0,0,0),0,0));
        
        for(ControlItem s : controls)
            s.draw(g, new Camera(new Coordinate(0,0,0),Math.sin(System.currentTimeMillis()/1000.0),0));
        
    }
    
    public void interact(Camera c, double time, int x, int y){
        for(ControlItem s : controls)
            s.interact(c, time, x, y);
    }
    
    
    
    
    protected abstract void drawDefaultVisuals(Graphics g, Camera c);
    
    
    
    
    @Override
    public void activate(){}
    
    @Override
    public double getEnergyDemand(double time){
        double energy = getWattage()*time;
        for(ControlItem s : controls)
            energy += s.getEnergyDemand(time);
        
        return energy;
        
    }
    
    @Override
    public void deactivate(){}
    
    @Override
    public double getWattage(){
        double watt = 0;
        
        for(ControlItem s : controls)
            watt += s.getWattage();
        
        return watt;
        
    }
    
    public static Cockpit buildCockpit(){throw new UnsupportedOperationException("Not supported yet.");}
    
}
