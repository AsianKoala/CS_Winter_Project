package Testers;

import Geometry.Point;
import Subsystems.Asteroid;
import Subsystems.Background;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class AsteroidTester {
    public static void main(String[] args) {
        UtilMethods.runFrame(new AsteroidTesterPanel());
    }
}


class AsteroidTesterPanel extends JPanel {
    private Background ourBackground = new Background();


    @Override
    public void paintComponent(Graphics g) {
        ourBackground.run(g);
        Asteroid.runOurAsteroidsList(g, new Point(400, 400));
        g.drawString("" + Asteroid.ourAsteroids.size(), 15, 15);


        UtilMethods.sleep(15);
        repaint();
    }
}
