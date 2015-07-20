/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.Interface;
import gui.shapes.*;
import java.awt.Color;
import java.awt.Graphics;
import physics.Coordinate;
import physics.Vector;

/**
 *
 * @author Christopher
 */
public abstract class Button extends ControlItem{
    
    private Color ON, OFF;
    
    public Button(Rectangle3D s, Color on, Color off) {
        super(s, 0);
        ON = on;
        OFF = off;
    }
    
    public Button(Rectangle3D s){
        this(s, Color.GREEN, Color.RED);
    }
    
    @Override
    public void draw(Graphics g, Camera c){
        
        Rectangle3D s = (Rectangle3D) shape;
        
        if(willInteract(c, Interface.getInterface().getMouseX(), Interface.getInterface().getMouseY())){
            g.setColor(Color.YELLOW);
            (new Rectangle3D(s.CENTER, s.XZ, s.Y, s.WIDTH * 1.05, s.HEIGHT * 1.05)).fillShape(g, c);
        }
        
        if(this.isPowered(wattage)){
            
            if(this.activated)
                g.setColor(ON);
            else 
                g.setColor(OFF);
        }else {
            g.setColor(new Color(16,16,16));
        }
        
        shape.drawShape(g, c);
    }
    
    
    public Coordinate getSurfacePosition(double x, double y){
        
        
        Coordinate[] list = Rectangle3D.generateCorners((Rectangle3D) shape);
        
        Vector a = new Vector(list[0],list[1]);
        a.multiplyMagnitude(x);
        Vector b = new Vector(list[0],list[3]);
        b.multiplyMagnitude(y);
        
        Coordinate c = list[0];
        
        c.addVector(a);
        c.addVector(b);
        
        return c;
        
    }
    
    
}
