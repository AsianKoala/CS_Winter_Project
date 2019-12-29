package Testers;

import Geometry.BetterRectangle;
import Geometry.Line;
import Geometry.Point;

public class ParallelLineTest {
    public static void main(String[] args) {
        BetterRectangle rectangle = new BetterRectangle(new Line(new Point(400,400), new Point(410, 400)), 5);

        System.out.println(rectangle.topLine.getLength());

        System.out.println(new Line(400,400,410,400).getLength());
        System.out.println(new Line(new Point(400,400), new Point(410, 400)).getLength());
    }
}
