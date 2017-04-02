package GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class Display implements IDisplay {
    private String title;
    private JFrame frame;
    private Canvas canvas;
    private Dimension screen;
    private int width, height;

    public Display(String title, double screenWidth, double screenHeight)  {
        this.title = title;
        this.screen = new Dimension();
        this.width = (int)(screenWidth * 1.9);
        this.height = (int)(screenHeight * 1.9);
        screen.setSize(screenWidth, screenHeight);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void createDisplay() {
        frame = new JFrame(title);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(this.width, this.height));
        frame.setLocation(0, 0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        canvas = new Canvas();
        canvas.setPreferredSize(this.screen);
        canvas.setSize(this.width, this.height);
        
        frame.add(canvas);
        frame.pack();

    }
}
