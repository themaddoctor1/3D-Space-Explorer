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
    protected double wattage;
    
    @Override
    public double getEnergyDemand(double time){
        if(!isPowered(time * wattage))
            return wattage * time;
        else{
            deactivate();
            return 0;
        }
    }
    
    @Override
    public void execute(double time, Object... params){
        
    }
    
    @Override
    public void activate(){ activated = true; }
    @Override
    public void deactivate(){ activated = false; }
    @Override
    public double getWattage(){ return wattage; }
    @Override
    public boolean isOn(){ return activated && isPowered(wattage); }
    
    
    
    
}
