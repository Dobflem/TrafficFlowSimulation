package SimulationControl;

import java.util.ArrayList;

import Driver.Driver;
import Road.Road;

public class SimpleControllerFactory {

	public Controller createSimulationController(SimpleControllerFactory factory) {
        return new SimulationController(factory);
    }

	public CollisionDetectionController createCollisionDetectionController(SimulationController _controller, ArrayList<Driver> d) {
		return  new CollisionDetectionController(_controller, d);
	}

	public GraphicsController createGraphicsController(SimulationController _controller, double screenWidth, double screenHeight, ArrayList<Driver> drivers, Road road){
		return new GraphicsController(_controller, screenWidth, screenHeight, drivers, road);
	}
	
	public DriverController createDriverController(SimulationController _controller, ArrayList<Driver> _drivers) {
		return new DriverController(_controller, _drivers);
	}

}
