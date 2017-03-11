package Vehicle;

import java.awt.*;

import Road.Road;

public class Vehicle {
    private Point position;
    private int cellId;
    private double angle;
    private Road road;
    private int vehicleWidth;
    private int vehicleHeight;
    private Color color;

    public Vehicle(Point xy, int cellId, Road road, int vWidth, int vHeight, Color color) {
        this.position = xy;
        this.cellId = cellId;
        this.road = road;
        this.vehicleWidth = vWidth;
        this.vehicleHeight = vHeight;
        this.angle = 0.0;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public int getVehicleWidth() {
        return vehicleWidth;
    }

    public int getVehicleHeight() {
        return vehicleHeight;
    }

    public double getAngle() {
        return angle;
    }

    public Road getRoad() {
        return road;
    }

    public Color getColor() {
        return color;
    }

    public void accelerate() {
        this.incrementCellId();
        this.updatePosition(road.getPosition(cellId));
        this.updateAngle(road.getCarAngle(cellId));
    }

    private void updatePosition(Point newPosition) {
        this.position.x = newPosition.x;
        this.position.y = newPosition.y;
    }

    private void incrementCellId() {
    	cellId = (cellId + 1) % road.getNumCells();
    }

    private void updateAngle(double angle) {
        this.angle = angle;
    }
}
