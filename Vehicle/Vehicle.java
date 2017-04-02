package Vehicle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Road.Road;
import Road.Lane;

public class Vehicle implements I_VehicleCollisionObserver {
	

	public static enum manufacturer{
		Toyota,Volkswagen,Hyundai,Ford,Nissan,Mazda
	}
    private int currentCell, vehicleWidth, vehicleHeight, currentLaneId, vehicleId;
	private double maxSpeed,currentSpeed,angle;
	private Lane track;
	private Road road;
	private Point position;
	private Color color;
	private String imagePath;
	private I_VehicleState state;
	private BufferedImage carImage;
	
	public Vehicle(Point xy, int cellId, Road road, int laneId, int vWidth, int vHeight, int id, Color color){
		this.position = xy;
		this.currentCell = cellId;
		this.road = road;
		this.currentLaneId = laneId;
		this.track = road.getLane(currentLaneId);
		this.vehicleWidth = vWidth;
		this.vehicleHeight = vHeight;
		this.color = color;
        this.currentSpeed = 0.0;
        this.vehicleId = id;
        this.state = new VehicleDrivingState();
        this.carImage = createCarImage(this.getVehicleHeight(), this.getVehicleWidth());
	}
	
	public Vehicle(Point xy, int cellId, Road road, int laneId, int vWidth, int vHeight, int id, String imagePath){
		 this.position = xy;
		 this.currentCell = cellId;
		 this.road = road;
		 this.currentLaneId = laneId;
		 this.track = road.getLane(currentLaneId);
		 this.vehicleWidth = vWidth;
		 this.vehicleHeight = vHeight;
		 this.color = Color.gray;
		 this.imagePath = imagePath;
		 this.currentSpeed = 0.0;
	     this.vehicleId = id;
		 this.state = new VehicleDrivingState();
		 this.carImage = createCarImage(this.getVehicleHeight(), this.getVehicleWidth());

	 }

	 public int getCurrentCellId() {
	    return this.currentCell;
     }
	
	public void setCurrentSpeed(double currentSpeed){
		this.currentSpeed = currentSpeed;
	}
	
	public void accelerate(int speedModifier){
		state.accelerate(speedModifier, this, track);
	}
	
	public void decellerate(){
		this.decrementCellId();
		this.updatePosition(track.getPosition(this.currentCell));
		this.updateAngle(track.getCarAngle(this.currentCell));
	}
	
	public void incrementCellId(int speedModifier){
		int previousCell = this.currentCell;
		this.currentCell = (this.currentCell + speedModifier) % track.getNumCells();
		track.occupyCell(previousCell, currentCell, this.vehicleId);
	}
	
	public void decrementCellId(){
		this.currentCell = (this.currentCell - 1) % track.getNumCells();
	}
	
	public void updateAngle(double angle){
		this.angle = angle;
	}

	public void updatePosition(Point position) {
		
		this.position.x = position.x;
		this.position.y = position.y;
		/*
		if (this.currentCell > 400 && this.currentCell < 600) {
			this.position.x = position.x - this.vehicleWidth;
		} else {
			this.position.x = position.x;
		}
		
		if (this.currentCell < track.getNumCells() / 2) {
			this.position.y = position.y;
		} else {
			this.position.y = position.y - this.vehicleHeight;
		}
		*/
	}

	public void collisionNotification(int vehicle1ID, int vehicle2ID) {
		if (this.vehicleId == vehicle1ID || this.vehicleId == vehicle2ID) {
		    this.state = new VehicleCrashedState();
        }
	}
	
	public Point getPosition(){
		return position;
	}
	
	public double getAngle(){
		return angle;
	}
	
	public Road getRoad(){
		return road;
	}

	public Lane getLane() {
		return track;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getVehicleImagePath() {
		return this.imagePath;
	}
	
	public int getCurrentCell() {
		return currentCell;
	}
	
	public int getVehicleHeight() {
		return vehicleHeight;
	}
	
	public int getVehicleWidth() {
		return vehicleWidth;
	}
	
	public double getCurrentSpeed() {
		return currentSpeed;
	}

    public boolean isCrashed() {
        return (this.state.getState() == VehicleStatesEnum.CRASHED);
    }

    public void setColor(Color color){
		this.color = color;
	}
    
    public int getID(){
    	return vehicleId;
    }
    
    public int getCurrentLaneID() {
    	return this.currentLaneId;
    }
    
    public void setLane(int laneID) {
    	this.currentLaneId = laneID;
		this.track = road.getLane(laneID);
    }
    
    public VehicleStatesEnum getStateEnum() {
        return this.state.getState();
    }
    
    public I_VehicleState getState() {
        return this.state;
    }
    
    public BufferedImage getCarImage() {
    	return this.carImage;
    }
    
    private BufferedImage createCarImage(int newW, int newH) {
    	BufferedImage car, resized = null;
    	Image tmp;
    	
        try {
            car = ImageIO.read(new File(this.imagePath));
            tmp = car.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resized;
    }
}

