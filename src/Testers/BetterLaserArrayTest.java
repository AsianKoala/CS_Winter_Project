package Testers;

import Geometry.Line;
import Geometry.Point;
import Subsystems.Background;
import Subsystems.Laser;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BetterLaserArrayTest {
    public static void main(String[] args) {
        UtilMethods.runFrame(new ArrayTestPanel());
    }
}


class ArrayTestPanel extends JPanel {

    private ArrayList<Laser> ourLasers = new ArrayList<>();

    ArrayTestPanel() {

        for (int i = 0; i < 5; i++) {
            Point startPoint = new Point(400, 400 + 50 * i);
            Point endPoint = new Point(450, 400 + 50 * i);
            ourLasers.add(new Laser(new Line(startPoint, endPoint), 10, 5));
        }
    }


    private Background bg = new Background(800, 800);

    @Override
    public void paintComponent(Graphics g) {
        bg.run(g);

        for (Laser laser : ourLasers) {
            laser.run(g);
        }


        UtilMethods.sleep(250);
        repaint();
    }
}
