package SimulationControl;

import java.util.ArrayList;

import Driver.Driver;
import CollisionDetection.*;

public class CollisionDetectionController extends Controller implements Runnable {
	
	private I_CollisionDetectionSubject cd;
	private ArrayList<Driver> drivers;
	private boolean running;
	private SimulationController controller;
	
	
	public CollisionDetectionController(SimulationController _controller, ArrayList<Driver> d) {
		this.controller = _controller;
		this.cd  = new CollisionDetection();
		this.drivers = d;
		this.running = true;
		registerVehicles();
	}
	
	public void registerVehicles() {
		for(Driver d : drivers) {
			cd.registerObserver(d.getDriverVehicle());
		}
	}
	
	public void checkForCollisions() {
		for(Driver d : drivers){
			if(!(d.getDriverVehicle().isCrashed())) {
				cd.checkForCollisions(d.getDriverVehicle().getID(), d.getDriverVehicle().getCurrentCell(), d.getDriverVehicle().getLane());
			}
		}
	}
	
	@Override
	public void run() {
		Timer t = new Timer(32);
		while(running) {
			t.start();
			for(Driver d : drivers){
				if(!(d.getDriverVehicle().isCrashed())) {
					cd.checkForCollisions(d.getDriverVehicle().getID(), d.getDriverVehicle().getCurrentCell(), d.getDriverVehicle().getLane());
				}
			}
			t.end();
		}
	}
	
	public void stopRunning() {
		this.running = false;
	}
	
	public void startRunning() {
		this.running = true;
	}
}
