package Testers;

import Geometry.Line;
import Geometry.Rectangle;
import Subsystems.Background;
import Subsystems.BetterLaser;
import Subsystems.PepegaLaser;
import Util.UtilMethods;
import Geometry.Point;

import javax.swing.*;
import java.awt.*;


public class BetterRectangleTest {
    public static void main(String[] args){
        UtilMethods.runFrame(new JFrame(), new BetterRectanglePanel());
    }
}

class BetterRectanglePanel extends JPanel {
//
//    @Override
//    public void paintComponent(Graphics g) {
//
//        Point startPoint = new Point(400,400);
//        Point endPoint = new Point(450,450);
//        Point bottomStartPoint = startPoint.findExtendedPoint(10, -1);
//        Point bottomEndPoint = endPoint.findExtendedPoint(10,-1);
//
//        int[] xPoints = {
//                (int)startPoint.x,
//                (int)endPoint.x,
//                (int)bottomEndPoint.x,
//                (int)bottomStartPoint.x
//        };
//
//        int[] yPoints = {
//                (int)startPoint.y,
//                (int)endPoint.y,
//                (int)bottomEndPoint.y,
//                (int)bottomStartPoint.y,
//        };
//
//
//        g.drawPolygon(xPoints,yPoints,4);
//    }


    private Background bg = new Background(800,800);

    private BetterLaser bLaser = new BetterLaser(new Line(new Point(400,400), new Point(400,450)), 5, 5);

    @Override
    public void paintComponent(Graphics g) {
        bg.run(g);

//        pepegaLaser.run(g);
        bLaser.run(g);

        System.out.println(bLaser);
    }
}
