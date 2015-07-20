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
public class CapacitantTesseract extends PowerStorage{

    public CapacitantTesseract(double limit) {
        super(limit, limit);
    }

    @Override
    public void execute(double time, Object... params) {
        
    }
    
}
