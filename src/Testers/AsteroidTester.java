package Testers;

import Geometry.Point;
import Subsystems.Asteroid;
import Subsystems.Background;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class AsteroidTester {
    public static void main(String[] args) {

    }
}


class AsteroidTesterPanel extends JPanel {

    Background ourBackground = new Background();



    @Override
    public void paintComponent(Graphics g) {
        ourBackground.run(g);
        Asteroid.runOurAsteroidsList(g, new Point(400,400));


        UtilMethods.sleep(50);
        repaint();
    }
}
