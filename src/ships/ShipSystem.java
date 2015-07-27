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
public abstract class ShipSystem implements ComputerControlled{
    
    protected ShipSystem owner = null;
    
    public final void setOwner(ShipSystem own){
        if(!own.equals(this)) owner = own;
    }
    
    public final void addSubsystem(ShipSystem sub){
        sub.setOwner(this);
    }
    
    public abstract void activate();
    public abstract void deactivate();
    public abstract void execute(double time, Object... params);
    public abstract boolean isOn();
    public abstract double getEnergyDemand(double time);
    public abstract double getWattage();
    
    public void useEnergy(double amt){
        if(owner.isPowered(amt))
            owner.useEnergy(amt);
    }
    
    public boolean isPowered(double needed){
        try{
            return owner.isPowered(needed);
        } catch(Exception e){
            return false;
        }
    }
    
    @Override
    public Object runScript(String function, String parameter){
        switch(function){
            case "activate":
                activate();
                break;
            case "deactivate":
                deactivate();
                break;
        }
        
        return null;
        
    }
    
    
}
