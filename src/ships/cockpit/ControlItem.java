/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.shapes.Shape3D;
import java.awt.Graphics;
import physics.Coordinate;
import physics.Vector;
import ships.PowerUser;

/**
 *
 * @author Christopher
 */
public abstract class ControlItem extends PowerUser{
    
    protected Shape3D shape;
    
    public ControlItem(Shape3D s, double watts){
        shape = s;
        wattage = watts;
    }
    
    public void interact(Camera c, double time, int x, int y){
        if(willInteract(c,x,y))
            execute(time);
    }
    
    public boolean willInteract(Camera c, int x, int y){
        return shape.contains(c, x, y);
    }
    
    
    public abstract void draw(Graphics g, Camera c);
    
    
}
