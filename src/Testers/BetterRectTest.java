package Testers;

import Geometry.BetterRectangle;
import Geometry.Line;
import Geometry.Point;

public class BetterRectTest {
    public static void main(String[] args) {
        BetterRectangle rectangle = new BetterRectangle(new Line(new Point(0,10), new Point(10,10)), new Line(new Point(0,0), new Point(10,0)));;
        System.out.println(rectangle);


        rectangle.shiftRect(10);

        Line topLine = new Line(new Point(0,10), new Point(10,10));
        Line bottomLine = new Line(new Point(0,0), new Point(10,0));

        topLine.shiftLine(10);
        bottomLine.shiftLine(10);

        System.out.println(topLine);
        System.out.println(bottomLine);


        // WOOOHOOO THE THING FUCKING WORKS



        rectangle.shiftRect(10);
        System.out.println(rectangle); // simple rectangle with heading of 0




        BetterRectangle verticalRectangle = new BetterRectangle(new Line(new Point(0,0), new Point(0, 10)), new Line(new Point(10,0), new Point(10,10)));


        verticalRectangle.shiftRect(50);
        System.out.println(verticalRectangle);

    }
}
