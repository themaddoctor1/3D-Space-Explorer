/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package world;

import java.util.ArrayList;
import ships.Ship;

/**
 *
 * @author Christopher Hittner
 */
public class WorldManager {
    //Starts with an empty world
    private static World world = new World();
    
    private static long timeChecked = System.nanoTime();
    
    
    
    
    public static void addShip(Ship s){getShips().add(s);}
    public static Ship getShip(int index){return getShips().get(index);}
    
    public static ArrayList<Ship> getShips(){
        return world.getShips();
    }
    
    
    
    
    
    
    
    
    public static void setWorld(World w){
        world = w;
        confirmCheck();
    }
    
    public static World getWorld(){
        return world;
    }
    
    
    public static void executePhysics(){
        executePhysics(confirmCheck());
    }
    
    public static void executePhysics(double time){
        for(Ship s : getShips())
            s.execute(time, null);
    }
    
    private static double confirmCheck(){
        double time = (getNanoTimeSinceLastCycle() / Math.pow(10,9));
        timeChecked = System.nanoTime();
        return time;
    }
    
    private static long getNanoTimeSinceLastCycle(){
        return System.nanoTime() - timeChecked;
    }
    
    
    public static void startSimulation(){
        if(WorldThread.getRunStatus())
            return;
        (new WorldThread()).start();
        
    }
    
    public static void stopSimulation(){
        WorldThread.setRunStatus(false);
    }
    
    /*
     * This subclass is meant to create a Thread that only this class can used.
     * The purpose of the thread is to execute the simulation.
    */
    private static class WorldThread extends Thread {
        private static boolean run = false;

        private WorldThread(){}
        
        @Override
        public void run(){
            setRunStatus(true);
            confirmCheck();
            
            while(run){
                double time = confirmCheck();
                executePhysics(time);
            }
        }
        
        private static void setRunStatus(boolean val){
            run = val;
        }
        
        private static boolean getRunStatus() {
            return run;
        }
        
    }
    
}
