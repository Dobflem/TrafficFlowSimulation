package SimulationControl;

import java.util.ArrayList;

import Driver.Driver;
import GraphicsManager.Display;
import GraphicsManager.DrawingComponent;
import GraphicsManager.IDisplay;
import Road.Road;

public class GraphicsController extends Controller implements Runnable {

    private String title;
    private double screenWidth, screenHeight;
    public DrawingComponent draw;
    private SimulationController controller;

    //private Thread thread;
    private IDisplay display;

    public GraphicsController(SimulationController _controller, double screenWidth, double screenHeight, ArrayList<Driver> drivers, Road road) {
        this.title = "Traffic Flow Simulation";
        this.display = new Display(title, screenWidth, screenHeight);
        this.display.createDisplay();
        this.draw = new DrawingComponent(this.display, screenWidth, screenHeight, road, drivers);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = _controller;
    }
    
    public void render() {
    	this.draw.render();
    }

    public void run() {
    	/*
        init();

        while(true) {
        	try{
        		draw.render(display);
        		Thread.sleep(16);
        	} catch(InterruptedException ex) {
        		ex.printStackTrace();
        	}
        }
        */
    }

    public void init() {
        
    }
}
