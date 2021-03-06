package SimulationControl;

import java.util.ArrayList;

import Driver.Driver;

public class DriverController extends Controller {
	
	private SimulationController controller;
	private ArrayList<Driver> drivers;
	
	public DriverController(SimulationController _controller, ArrayList<Driver> _drivers) {
		this.controller = _controller;
		this.drivers = _drivers;
	}
	
	public void drive() {
		for(Driver d : this.drivers) {
			d.drive();
		}
	}
	
}
