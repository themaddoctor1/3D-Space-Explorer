/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.cockpit;

import gui.Camera;
import gui.shapes.CurvedPolygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import physics.Coordinate;

/**
 *
 * @author Christopher
 */
public class TestCockpit extends Cockpit{
    
    
    public TestCockpit(){
        super();
        
        controls.add(new BlankScreen(new Coordinate(0.325,-0.2,0.42), Math.PI/4.0, Math.PI/6.0, 0.4, 0.3));
        
        controls.add(new BlankScreen(new Coordinate(0.5,-0.2,0), 0, Math.PI/6.0, 0.4, 0.3));
        
        controls.add(new BlankScreen(new Coordinate(0.325,-0.2,-0.42), -Math.PI/4.0, Math.PI/6.0, 0.4, 0.3));
        
    }
    
    @Override
    protected void drawDefaultVisuals(Graphics g, Camera c) {
        
        /*
        double x_ = 1, y_ = 0, z_ = 0, s_ = .1;
        
        
        
        //screens.add(new BlankScreen(new Coordinate(0.4,-0.2,-0.04), + x*Math.PI/2.0, Math.PI/6.0, 0.4, 1.3));
        
        for(int i = 0; i < 4; i++){
            CurvedPolygon.drawCurvedLine(g, c, 5, new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+i*Math.PI/2.0),s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+i*Math.PI/2.0)), new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+(i+1)*Math.PI/2.0),s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+(i+1)*Math.PI/2.0)));
            CurvedPolygon.drawCurvedLine(g, c, 5, new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+i*Math.PI/2.0),-s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+i*Math.PI/2.0)), new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+(i+1)*Math.PI/2.0),-s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+(i+1)*Math.PI/2.0)));
            CurvedPolygon.drawCurvedLine(g, c, 5, new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+i*Math.PI/2.0),s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+i*Math.PI/2.0)), new Coordinate(x_+s_/Math.sqrt(2)*Math.cos(x+i*Math.PI/2.0),-s_/2 + y_,0+s_/Math.sqrt(2)*Math.sin(x+i*Math.PI/2.0)));
        }
        
        //(new Screen(new Coordinate(0.5,0,0), x, Math.PI/3.0, 0.4, .3)).draw(g, new Camera(new Coordinate(0,0,0),0,0));*/
    }
    
}
