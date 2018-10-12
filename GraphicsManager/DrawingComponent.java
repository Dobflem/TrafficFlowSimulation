package GraphicsManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import Vehicle.Vehicle;
import Road.*;
import Driver.Driver;
import javax.imageio.ImageIO;

public class DrawingComponent implements IDrawingComponent{

    private int screenWidth;
    private int screenHeight;
    private Graphics2D g2d;
    private BufferStrategy buffer;
    private Road road;
    private ArrayList<Driver> drivers;
    private final String GRASS_IMAGE_PATH;
    private Vehicle v1;
    private BufferedImage grass;

    public DrawingComponent(IDisplay _display, double screenWidth, double screenHeight, Road road, ArrayList<Driver> drivers) {
        this.screenWidth = (int) screenWidth;
        this.screenHeight = (int) screenHeight;
        this.road = road;
        this.drivers = drivers;
        this.v1 = this.drivers.get(0).getDriverVehicle();
        this.GRASS_IMAGE_PATH = "grass.png";
        setBuffer(_display);
        g2d = (Graphics2D) buffer.getDrawGraphics();
    	g2d.clearRect(0, 0, this.screenWidth, this.screenHeight);
    	this.grass = createGrass();
    }

    public void render() {
    	
        drawGrass();
    	
        ArrayList<Lane> lanes = road.getLanes();
        for (Lane l : lanes) {
            drawLane(l);
        }
        
    	for (Driver d : this.drivers) {
            drawVehicle(d.getDriverVehicle());
    	}
    	
    	buffer.show();
    	// g2d.dispose();
    }
    
    private void drawGrass() {
    	g2d.drawImage(this.grass, 0, 0, null);
    }

    private void setBuffer(IDisplay display) {
        buffer = display.getCanvas().getBufferStrategy();
        if (buffer == null) {
            display.getCanvas().createBufferStrategy(3);
            buffer = display.getCanvas().getBufferStrategy();
        }
    }
    
    private BufferedImage createGrass() {
    	try {
    		return ImageIO.read(new File(GRASS_IMAGE_PATH));
    	} catch(Exception ex) {
    		return null;
    	}
    }

    private void drawLane(Lane lane) {
        g2d.setColor(lane.getLaneColor());
        g2d.setStroke(new BasicStroke(lane.getLaneWidth()));
        g2d.draw(new Ellipse2D.Double(lane.getX(), lane.getY(), lane.getWidth(), lane.getHeight()));
    }

    private void drawVehicle(Vehicle v) {
        Point pos = v.getPosition();
        Point p2 = new Point(pos.x - (v.getVehicleWidth() / 2), pos.y - (v.getVehicleHeight() / 2));

        AffineTransform at = new AffineTransform();
        at.rotate(v.getAngle(), p2.x + (v.getVehicleWidth() / 2), p2.y + (v.getVehicleHeight() / 2));
        at.translate(p2.x, p2.y);

        g2d.drawImage(v.getCarImage(), at, null);
    }
}
