/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.powergeneration;

/**
 *
 * @author Christopher
 */
public class EnergyTesseract extends PowerProducer{
    
    private boolean active = false;
    
    @Override
    public double getOutputWattage() {
        return Math.pow(10, 7);
    }

    @Override
    public void activate() { active = true; }

    @Override
    public void deactivate() { active = false; }

    @Override
    public void execute(double time, Object... params) {
        
    }

    @Override
    public boolean isOn() { return active; }
    
}
