package Testers;

import Geometry.BetterRectangle;
import Geometry.Line;
import Geometry.Point;
import Subsystems.BetterLaser;
import Subsystems.Laser;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class LaserTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LaserPanel());
        frame.setVisible(true);
    }
}

class LaserPanel extends JPanel {

    private Line topLine = new Line(new Point(400,400), new Point(410, 400));
//    private Line bottomLine = new Line(new Point(400, 395), new Point(410, 395));
//    private BetterLaser laser = new BetterLaser(topLine, bottomLine, 5);


    private BetterLaser laser = new BetterLaser(topLine);



    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());

        laser.run(g);
        UtilMethods.sleep(15);
        repaint();
    }
}
