package Testers;

import Geometry.Line;
import Geometry.Point;


public class LineExtensionTest {
    public static void main(String[] args) {
        Line line = new Line(new Point(0,0), new Point(0,10));
        Line line2 = new Line(new Point(0,0), new Point(4,4));

        line.shiftLine(10);
        line2.shiftLine(6 * Math.sqrt(2));

        System.out.println(line.startPoint.y + " , " + line.startPoint.y);
        System.out.println(line.endPoint.x + " , " + line.endPoint.y);

        System.out.println(line2);



        System.out.println();
        System.out.println(line.getSlope());
    }
}
