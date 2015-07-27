/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import ships.powergeneration.PowerStorage;
import java.util.ArrayList;
import ships.cockpit.Cockpit;

/**
 *
 * @author Christopher
 */
public abstract class Ship extends ShipSystem{
    
    ArrayList<ShipSystem> subsystems = new ArrayList<>();
    
    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addNewSubsystem(ShipSystem sys){
        subsystems.add(sys);
        if(!(sys instanceof ShipComputer)) for(ShipSystem s : subsystems)
            if(s instanceof ShipComputer)
                ((ShipComputer) s).addDevice(sys);
    }
    
    
    @Override
    public void deactivate() {
        for(ShipSystem sys : subsystems)
            sys.deactivate();
    }

    @Override
    public double getWattage() {
        double wattage = 0;
        
        for(ShipSystem sys : subsystems)
            wattage += sys.getWattage();
        
        return wattage;
        
    }
    
    @Override
    public void execute(double time, Object... params) {
        
        for(ShipSystem sys : subsystems){
            sys.execute(time, params);
        }
    }

    @Override
    public double getEnergyDemand(double time) {
        double demand = 0;
        
        for(ShipSystem ss : subsystems)
            ss.getEnergyDemand(time);
            
        return demand;
        
    }
    
    @Override
    public boolean isOn(){ return true; }
    
    @Override
    public boolean isPowered(double needed) {
        //Counts how many Joules of energy are available for the system requesting some.
        double available = 0;
        
        for(ShipSystem ss : subsystems){
            if(ss instanceof PowerStorage) available += ((PowerStorage) ss).getStoredEnergy();
        }
        
        return needed <= available;
        
    }
    
    
    @Override
    public void useEnergy(double amt){
        
        if(amt < 0){
            storeEnergy(-amt);
            return;
        }
        
        if(!isPowered(amt))
            return;
        
        double amountLeft = amt;
        
        for(ShipSystem ss : subsystems)
            if(ss instanceof PowerStorage) amountLeft = ((PowerStorage) ss).takeEnergy(amountLeft);
        
    }
    
    protected void storeEnergy(double amt){
        
        double amountLeft = amt;
        
        for(ShipSystem ss : subsystems)
            if(ss instanceof PowerStorage) amountLeft = ((PowerStorage) ss).storeEnergy(amountLeft);
    }
    
    
    
    
    public static Ship buildShip(){
        throw new UnsupportedOperationException("This ship cannot be constructed because no code has been implemented to do so.");
    }

    public Cockpit getCockpit() {
        for(ShipSystem ss : subsystems)
            if(ss instanceof Cockpit)
                return (Cockpit) ss;
        return null;
    }
    
    
}
