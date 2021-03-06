package Driver;

import java.awt.Color;

import SensoryPerception.Hearing;
import SensoryPerception.Sight;
import Vehicle.Vehicle;

public class NormalDriver extends Driver {
	
	private int speedModifier = 3;
	private double crashChance = 0.05;
	public NormalDriver() {
		
	}

	public NormalDriver(Vehicle driverVehicle, String name, int age, String sex) {
		super(driverVehicle, name, age, sex);
		driverVehicle.setColor(Color.green);
	}
	
	public void drive(){
		//run calls drive
		boolean carAhead = this.getDriverSight().checkLane(this.driverVehicle.getLane(), this.driverVehicle.getCurrentCell(), this.driverVehicle.getID(), 0, 15);
		if(carAhead)
			crashChance = this.checkAvailableLanes(12, crashChance);
		this.driverVehicle.accelerate(speedModifier);
	}
}
