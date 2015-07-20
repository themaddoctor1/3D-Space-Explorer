/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.Interface;
import gui.shapes.CurvedPolygon;
import gui.shapes.Rectangle3D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import physics.Coordinate;
import physics.Vector;
import ships.PowerUser;

/**
 *
 * @author Christopher
 */
public abstract class Screen extends ControlItem{
    
    protected ArrayList<ControlItem> subitems = new ArrayList<>();
    
    public Screen(Coordinate coord, double xz, double y, double w, double h){
        super(new Rectangle3D(coord,xz,y,w,h), w*h*100);
    }
    
    @Override
    public final void draw(Graphics g, Camera c){
        
        Graphics2D g2 = (Graphics2D) g;
        
        Rectangle3D sh = (Rectangle3D) this.shape;
        
        //Draw the rim
        //Rectangle3D s = (Rectangle3D) shape;
        //g2.setColor(Color.GRAY);
        //(new Rectangle3D(shape.CENTER, shape.XZ, shape.Y, shape.WIDTH*1.05, shape.HEIGHT*1.05)).fillShape(g, c);
        
        if(willInteract(c, Interface.getInterface().getMouseX(), Interface.getInterface().getMouseY())){
            g2.setColor(Color.YELLOW);
            (new Rectangle3D(sh.CENTER, sh.XZ, sh.Y, sh.WIDTH*1.025, sh.HEIGHT*1.025)).fillShape(g, c);
        }
        
        //Black screen by default
        g2.setColor(new Color(16,16,16));
        shape.fillShape(g, c);
        
        //Draw stuff on the screen
        if(isOn()){
            screenDetails(g, c);
        }
        
        g2.setColor(Color.GRAY);
        shape.drawShape(g, c);
        
        
        
    }
    
    @Override
    public void execute(double time, Object... params){
        super.execute(time, params);
    }
    
    @Override
    public void interact(Camera c, double time, int x, int y){
        if(willInteract(c,x,y)) for(ControlItem ci : subitems)
            ci.interact(c, time, x, y);
    }
    
    
    public abstract void screenDetails(Graphics g, Camera c);
    
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
