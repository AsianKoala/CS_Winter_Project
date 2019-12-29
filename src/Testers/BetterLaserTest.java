package Testers;

import Geometry.Line;
import Geometry.Point;
import Subsystems.BetterLaser;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class BetterLaserTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BetterLaserPanel());
        frame.setVisible(true);
    }
}



class BetterLaserPanel extends JPanel {

    BetterLaser laser = new BetterLaser(new Line(new Point(400,400), new Point(450, 400)), 5);

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());
        laser.run(g);

        UtilMethods.sleep(250);
        repaint();
    }
}
