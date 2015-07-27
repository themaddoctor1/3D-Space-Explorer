/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.powergeneration;

import ships.ShipSystem;

/**
 *
 * @author Christopher
 */
public abstract class PowerStorage extends ShipSystem{
    
    private double energy = 0;
    private final double ENERGY_LIMIT;
    
    public PowerStorage(double limit, double precharge){
        ENERGY_LIMIT = limit;
        energy = Math.max(0, Math.min(precharge, ENERGY_LIMIT));
    }
    
    @Override
    public void activate() {}

    @Override
    public void deactivate() {}

    @Override
    public abstract void execute(double time, Object... params);

    @Override
    public boolean isOn() {
        return true;
    }
    
    public boolean hasEnergy(double needed){ return energy >= needed; }
    public double storeEnergy(double amt){
        
        if(amt < 0)
            return amt;
        
        double newEnergy = energy + amt;
        
        energy = Math.min(ENERGY_LIMIT, newEnergy);
        
        return Math.max(newEnergy - ENERGY_LIMIT, 0);
        
    }
    
    public double takeEnergy(double amt){
        
        if(amt < 0)
            return amt;
        
        double newEnergy = energy - amt;
        
        energy = Math.max(newEnergy, 0);
        
        return Math.min(-newEnergy, 0);
        
    }
    
    public double getStoredEnergy(){ return energy; }
    
    @Override
    public double getWattage() { return 0; }

    @Override
    public double getEnergyDemand(double time) { return 0; }
    
    @Override
    public Object runScript(String function, String parameter){
        switch(function){
            case "value":
                switch(parameter){
                    case "storedEnergy":
                        return getStoredEnergy();
                    case "hasEnergy":
                        return hasEnergy(0);
                    case "wattage":
                        return getWattage();
                    case "isOn":
                        return isOn();
                }
        }
        
        return super.runScript(function, parameter);
        
    }
    
}
