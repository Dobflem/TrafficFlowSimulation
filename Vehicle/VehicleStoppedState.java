package Vehicle;

import Road.Lane;

public class VehicleStoppedState implements I_VehicleState{

    public void accelerate(int speedModifier, Vehicle v, Lane l) {
        //Will not accelerate when Stopped
    }

    public VehicleStatesEnum getState() {
        return VehicleStatesEnum.STOPPED;
    }
    
    public void moveLane(int laneID, Vehicle v) {
        //Will not move lane when Stopped
	}
}
