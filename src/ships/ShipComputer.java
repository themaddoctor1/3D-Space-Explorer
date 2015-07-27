/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import gui.Camera;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import ships.cockpit.ControlItem;

/**
 *
 * @author Christopher
 */
public class ShipComputer extends PowerUser{
    
    //This Stack contains the programs' memory
    private Stack<HashMap<String, Object>> RAM = new Stack();
    
    //A list of every subclass of ComputerControlled that is under the influence of this ShipComputer
    private ArrayList<ComputerControlled> connectedDevices = new ArrayList<>();
    
    
    
    public void addDevice(ComputerControlled device){
        connectedDevices.add(device);
    }
    
    
    @Override
    public void execute(double time, Object... params) {
        super.execute(time, params);
    }
    
    private void executeProgram(String[] program, HashMap<String, Object> extraValues){
        
        RAM.push(extraValues);
        
        String[] progLeft = new String[program.length];
        System.arraycopy(program, 0, progLeft, 0, program.length);
        
        while(progLeft.length > 0)
            progLeft = executeLine(progLeft);
        
        RAM.pop();
    }
    
    public final void executeProgram(String[] program){
        executeProgram(program, new HashMap<String, Object>());
    }
    
    @Override
    public double getEnergyDemand(double time) {
        return super.getEnergyDemand(time);
    }

    private String[] executeLine(String[] program) {
        
        HashMap<String, Object> dedicatedRAM = RAM.peek();
        
        String line = program[0] + "";
        
        if(line.startsWith("for ")){
            int numLoops = Integer.parseInt(line.substring(4));
            int endLine = -1;
            for(int i = 0; i < program.length; i++){
                if(program[i].startsWith("endfor")){
                    endLine += i;
                    break;
                }
            }
            
            String[] loopedProgram = new String[endLine];
            for(int i = 1; i <= endLine; i++)
                loopedProgram[i-1] = program[i].substring(4);
            
            for(int i = 0; i < numLoops; i++)
                executeProgram(loopedProgram, dedicatedRAM);
            
            String[] progLeft = new String[program.length - endLine - 2];
            
            for(int i = 0; i < progLeft.length; i++)
                progLeft[i] = program[endLine + 2 + i];
            
            return progLeft;
            
            
        } else {
            if(line.startsWith("print ")){
                String parameter = line.substring(6);
                
                System.out.println(parseValueOf(parameter, dedicatedRAM));
                
                
            } else if(line.startsWith("$")){
                String variableName = line.substring(1, line.indexOf("$", 2));
                if(!dedicatedRAM.containsKey(variableName))
                    dedicatedRAM.put(variableName, null);
                
                String parameter = line.substring(line.indexOf(" = ") + 3);
                
                
                dedicatedRAM.put(variableName, parseValueOf(parameter, dedicatedRAM));
                
            } else if(line.startsWith("System[")){
                int index = Integer.parseInt(line.substring(7, line.indexOf("]")));
                
                String parameter = line.substring(line.indexOf("]") + 2);
                
                connectedDevices.get(index).runScript(
                        parameter.substring(0, parameter.indexOf("|")), 
                        parseValueOf(parameter.substring(parameter.indexOf("|")+1, parameter.length()-1), dedicatedRAM).toString()
                );
                
            }
            
            String[] progLeft = new String[program.length-1];
            for(int i = 1; i < program.length;i++)
                progLeft[i-1] = program[i];
            return progLeft;
            
        }
    }

    public Object parseValueOf(Object parameter, HashMap<String, Object> ram) {
        
        if(parameter instanceof String){
            
            String param = (String) parameter;
            
            //Replace variables with values. Errors will ensue if improper values are used.
            while(param.contains("$")){
                    int first = param.indexOf("$");
                    int last = param.indexOf("$", first+2);

                    String name = param.substring(first+1, last);
                    
                    if(last + 1 != param.length())
                        param = param.substring(0, first) + ram.get(name) + param.substring(last + 1);
                    else
                        param = param.substring(0, first) + ram.get(name);

                }
            
            if(param.startsWith("# ")){
                String function = param.substring(2);

                //Adds spaces to make sure that the interpreter will work.
                for(int i = 1; i < function.length()-1; i++){
                    String ch = function.substring(i, i+1);
                    if(ch.equals("^") || ch.equals("*") || ch.equals("/") || ch.equals("+") || ch.equals("-")){
                        if(!function.substring(i+1, i+2).equals(" "))
                            function = function.substring(0, i+1) + " " + function.substring(i+1);
                        if(!function.substring(i-1, i).equals(" "))
                            function = function.substring(0, i) + " " + function.substring(i);
                    }
                }
                
                
                //Parentheses component of PEMDAS
                if(function.contains("(")){
                    int index = function.indexOf("(")+1, layers = 0, endIndex = -1;

                    for(int i = index; layers > 0; i++){
                        if(function.substring(i, i+1).equals(")")){
                            endIndex = i;
                            layers--;
                        } else if(function.substring(i, i+1).equals("("))
                            layers++;
                    }

                    return parseValueOf(function.substring(0, index-1) + (String)parseValueOf(function.substring(index, endIndex), ram) + function.substring(endIndex+1), ram);

                }



                if(function.contains(" ")){

                    ArrayList<String> symbolicValues = new ArrayList<>();

                    String tempFunction = function + " ";

                    while(!tempFunction.isEmpty()){
                        symbolicValues.add(tempFunction.substring(0, tempFunction.indexOf(" ")));
                        tempFunction = tempFunction.substring(tempFunction.indexOf(" ") + 1);
                    }

                    //Exponents portion of PEMDAS
                    for(int i = symbolicValues.size() - 2; i > 0; i--){
                        String symbol = symbolicValues.get(i);
                        if(symbol.equals("^")){
                            symbolicValues.set(i, "" + Math.pow(Double.parseDouble(symbolicValues.get(i-1)), Double.parseDouble(symbolicValues.get(i+1))));
                            symbolicValues.remove(i+1);
                            symbolicValues.remove(i-1);
                        }
                    }

                    //Multiplication and Division portion of PEMDAS
                    for(int i = symbolicValues.size() - 2; i > 0; i--){
                        String symbol = symbolicValues.get(i);
                        if(symbol.equals("*")){
                            symbolicValues.set(i, "" + (Double.parseDouble(symbolicValues.get(i-1)) * Double.parseDouble(symbolicValues.get(i+1))));
                            symbolicValues.remove(i+1);
                            symbolicValues.remove(i-1);
                        } else if(symbol.equals("/")){
                            symbolicValues.set(i, "" + (Double.parseDouble(symbolicValues.get(i-1)) / Double.parseDouble(symbolicValues.get(i+1))));
                            symbolicValues.remove(i+1);
                            symbolicValues.remove(i-1);
                        }
                    }

                    //Addition and Subtraction portion of PEMDAS
                    for(int i = symbolicValues.size() - 2; i > 0; i--){
                        String symbol = symbolicValues.get(i);
                        if(symbol.equals("+")){
                            symbolicValues.set(i, "" + (Double.parseDouble(symbolicValues.get(i-1)) + Double.parseDouble(symbolicValues.get(i+1))));
                            symbolicValues.remove(i+1);
                            symbolicValues.remove(i-1);
                        } else if(symbol.equals("-")){
                            symbolicValues.set(i, "" + (Double.parseDouble(symbolicValues.get(i-1)) - Double.parseDouble(symbolicValues.get(i+1))));
                            symbolicValues.remove(i+1);
                            symbolicValues.remove(i-1);
                        }
                    }

                    if(symbolicValues.size() == 1)
                        return symbolicValues.get(0);

                }
            } else if(param.startsWith("System[")){
                int index = Integer.parseInt(param.substring(7, param.indexOf("]")));
                
                String dataType = param.substring(param.indexOf("]" + 2));
                
                return connectedDevices.get(index).runScript(
                        "value",
                        param.substring(param.indexOf("]") + 2)
                );
                
            }
            
            return param;
            
        }
        return parameter;
    }
    
}
