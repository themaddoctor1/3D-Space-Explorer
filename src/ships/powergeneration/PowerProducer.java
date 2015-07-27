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
public abstract class PowerProducer extends ShipSystem{
    

    @Override
    public double getEnergyDemand(double time) {
        return getWattage() * time;
    }
    
    @Override
    public final double getWattage() {
        return -getOutputWattage();
    }
    
    public abstract double getOutputWattage();
    
    @Override
    public Object runScript(String function, String parameter){
        switch(function){
            case "value":
                switch(parameter){
                    case "wattage":
                        return getWattage();
                    case "outputWattage":
                        return getOutputWattage();
                    case "isOn":
                        return isOn();
                }
        }
        
        return super.runScript(function, parameter);
        
    }
    
}
