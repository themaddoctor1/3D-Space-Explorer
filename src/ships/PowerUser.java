/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

/**
 *
 * @author Christopher
 */
public abstract class PowerUser extends ShipSystem{
    
    protected boolean activated = true;
    protected double wattage = 0;
    
    @Override
    public double getEnergyDemand(double time){
        if(activated)
            return wattage * time;
        else
            return 0;
        
    }
    
    @Override
    public void execute(double time, Object... params){
        if(this.isPowered(getEnergyDemand(time)))
            this.useEnergy(getEnergyDemand(time));
    }
    
    @Override
    public void activate(){ activated = true; }
    @Override
    public void deactivate(){ activated = false; }
    @Override
    public double getWattage(){ return wattage; }
    @Override
    public boolean isOn(){ return activated && isPowered(wattage); }
    
    @Override
    public Object runScript(String function, String parameter){
        switch(function){
            case "value":
                switch(parameter){
                    case "wattage":
                        return getWattage();
                    case "isOn":
                        return isOn();
                }
        }
        
        return super.runScript(function, parameter);
        
    }
    
    
}
