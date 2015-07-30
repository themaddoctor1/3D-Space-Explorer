/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.shapes.CurvedPolygon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import physics.Coordinate;

/**
 *
 * @author Christopher
 */
public class BlankScreen extends Screen{

    public BlankScreen(Coordinate coord, double xz, double y, double w, double h) {
        super(coord, xz, y, w, h);
    }
    
    
    @Override
    public void screenDetails(Graphics g, Camera c){
        
        ((Graphics2D) g).setColor(Color.white);
        /*
        for(int i = 0; i <= 5; i++){
            Coordinate a = this.getSurfacePosition(0.1*i, 0);
            Coordinate b = this.getSurfacePosition(0.1*i, 1);
            CurvedPolygon.drawCurvedLine(g, c, 5, a, b);
            
            a = this.getSurfacePosition(0,0.1*i);
            b = this.getSurfacePosition(1,0.1*i);
            CurvedPolygon.drawCurvedLine(g, c, 5, a, b);
        }
        */
        
        shape.fillShape(g, c);

        for(ControlItem ci : subitems)
            ci.draw(g, c);

        
    }
    
    //public boolean isOn(){return true;}
    
}
