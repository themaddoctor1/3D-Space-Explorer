/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.Camera;
import gui.Interface;
import gui.Interface3D;
import physics.Coordinate;

/**
 *
 * @author Christopher
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Interface3D.initialize("Space Game", 1200, 900,new Camera(new Coordinate(0,0,0),0,0));
        
        while(true)
            Interface.getInterface().redraw();
        
    }
    
}
