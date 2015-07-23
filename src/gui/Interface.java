/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import physics.Coordinate;
import world.WorldManager;

/**
 *
 * @author WHS-D4B1W8
 */
public class Interface extends Applet implements KeyListener, MouseListener, MouseMotionListener{
    //The GUI object
    protected static Interface gui;
    
    //Controller
    protected Controller controller = null;
    
    //Applet stuff
    protected Graphics graphics;
    protected JFrame frame;
    
    private int mouseX = 0, mouseY = 0;
    private boolean mouseHeld = false;
    
    ////////////////////////////////////////////////////
    
    protected Interface(String name){
        //this(480,320);
        this(name, 800,600);
    }
    
    protected Interface(String name, int width, int height){
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //Creates a JFrame with a title
        frame = new JFrame(name);
        //Puts the Tester object into thhe JFrame
	frame.add(this);
        //Sets the size of the applet to be 800 pixels wide  by 600 pixels high
	frame.setSize(width, height);
        //Makes the applet visible
	frame.setVisible(true);
        //Sets the applet so that it can't be resized
        frame.setResizable(false);
        //This will make the program close when the red X in the top right is
        //clicked on
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    public void redraw(){
        repaint();
    }

    public void update(Graphics g){
        Image image = null;
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            graphics = image.getGraphics();
        }
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,  0,  this.getWidth(),  this.getHeight());
        graphics.setColor(getForeground());
        paint(graphics);
        g.drawImage(image, 0, 0, this);
        
    }
    
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        
    }
    
    
    
    public static void initialize(String name){
        gui = new Interface(name, 800, 600);
    }
    
    public static void initialize(String name, int width, int height){
        gui = new Interface(name, width, height);
    }
    
    public static Interface getInterface(){ return gui;}
    
    //-----------------------
    //Controller
    //-----------------------
    
    public void setController(Controller c){ controller = c; }
    public Controller getController(){ return controller; }
    public void cycleController(){ controller.execute(); }
    
    
    //-----------------------
    //Keyboard and Mouse
    //-----------------------
    

    public int getCenterX() {
        return frame.getWidth()/2;
    }

    public int getCenterY() {
        return frame.getHeight()/2;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mouseHeld = true;
        WorldManager.getShip(0).getCockpit().interact(new Camera(new Coordinate(0,0,0),0,0), 0, mouseX, mouseY);
        mouseMoved(me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseHeld = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        controller.setState(ke.getKeyCode(), true);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        controller.setState(ke.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        controller.setState(ke.getKeyCode(), false);
    }
    
    public int getMouseX(){ return mouseX; }
    public int getMouseY(){ return mouseY; }
    public boolean mouseHeldDown(){ return mouseHeld; }
    
}
