package Geometry;

import java.awt.*;

public class Rectangle {
    public Line topLine;
    public Line bottomLine;
    public Line leftLine;
    public Line rightLine;

    public Rectangle(Line topLine, double width) {
        this.topLine = topLine;

        bottomLine = topLine.getParallelLine(width);

        leftLine = new Line(topLine.startPoint, bottomLine.endPoint);
        rightLine = new Line(topLine.endPoint, bottomLine.endPoint);
    }


    public void shiftRect(double d) {
        if(topLine.getHeading() > 90 && topLine.getHeading() <= 270) {
            topLine.shiftLine(-d);
            bottomLine.shiftLine(-d);
        }

        else {
            topLine.shiftLine(d);
            bottomLine.shiftLine(d);
        }

        leftLine = new Line(topLine.startPoint, bottomLine.endPoint);
        rightLine = new Line(topLine.endPoint, bottomLine.endPoint);
    }



    public void shiftAllPoints(double d, double m, boolean isLeft) {
        if(isLeft) {
            topLine.startPoint = topLine.startPoint.findExtendedPoint(d, m);
            topLine.endPoint = topLine.endPoint.findExtendedPoint(d, m);
            bottomLine.startPoint = bottomLine.startPoint.findExtendedPoint(d, m);
            bottomLine.endPoint = bottomLine.endPoint.findExtendedPoint(d, m);
        } else {
            topLine.startPoint = topLine.startPoint.findExtendedPoint(-d,m);
            topLine.endPoint = topLine.endPoint.findExtendedPoint(-d,m);
            bottomLine.startPoint = bottomLine.startPoint.findExtendedPoint(-d,m);
            bottomLine.endPoint = bottomLine.endPoint.findExtendedPoint(-d,m);
        }
    }




    public int[] xPoints() {
        return new int[]{
                (int) topLine.startPoint.x,
                (int) topLine.endPoint.x,
                (int) bottomLine.endPoint.x,
                (int) bottomLine.startPoint.x,
        };
    }


    public int[] yPoints() {
        return new int[]  {
                (int)topLine.startPoint.y,
                (int)topLine.endPoint.y,
                (int)bottomLine.endPoint.y,
                (int)bottomLine.startPoint.y,
        };
    }




    @Override
    public String toString() {
        return topLine + " , " + bottomLine;
    }

}
