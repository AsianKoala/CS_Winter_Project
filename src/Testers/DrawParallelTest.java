package Testers;

import Geometry.BetterRectangle;
import Geometry.Line;
import Geometry.Point;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class DrawParallelTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setSize(800,800);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ParallelTestPanel());
        frame.setVisible(true);
    }
}




class ParallelTestPanel extends JPanel {

    BetterRectangle rectangle = new BetterRectangle(new Line(new Point(400,400), new Point(500,400)), 5);


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        g.setColor(Color.GREEN);
        g.fillRect((int)rectangle.topLine.startPoint.x, (int)rectangle.topLine.startPoint.y, (int)rectangle.topLine.getLength(), (int)new Line(rectangle.topLine.startPoint, rectangle.bottomLine.startPoint).getLength());


     //   g.fillRect(400,400,100,5);
        System.out.println(rectangle.topLine.startPoint.x);
        System.out.println(rectangle.topLine.startPoint.y);
        System.out.println(rectangle.topLine.getLength());
 //       System.out.println(rectangle.topLine.getSlope());
        System.out.println(rectangle.topLine.getParallelLine(5).getLength());
    }
}
