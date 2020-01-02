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




    @Override
    public String toString() {
        return topLine + " , " + bottomLine;
    }
































































    // ignore me
    public void draw(Graphics g) {

        int[] xPoints = {
                (int)topLine.startPoint.x,
                (int)topLine.endPoint.x,
                (int)bottomLine.endPoint.x,
                (int)bottomLine.startPoint.x,
        };

        int[] yPoints = {
                (int)topLine.startPoint.y,
                (int)topLine.endPoint.y,
                (int)bottomLine.endPoint.y,
                (int)bottomLine.startPoint.y,
        };

        g.drawPolygon(xPoints,yPoints,4);
    }
}
