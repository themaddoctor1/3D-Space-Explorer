/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package world;

import java.util.ArrayList;
import physics.Constants;
import physics.Coordinate;
import physics.Vector;
import physics.functions.Plane;
import ships.*;

/**
 *
 * @author Christopher Hittner
 */
public class World implements Constants{
    
    protected ArrayList<Ship> ships = new ArrayList<>();
    
    protected ArrayList<Ship> getShips(){ return ships; }
    
}
