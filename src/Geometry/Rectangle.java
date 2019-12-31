package Geometry;

import java.awt.*;

public class Rectangle {
    public Line topLine;
    public Line bottomLine;

    public Rectangle(Line topLine, double width) {
        this.topLine = topLine;

        bottomLine = topLine.getParallelLine(width);
    }


    public void shiftRect(double d) {
        topLine.shiftLine(d);
        bottomLine.shiftLine(d);
    }




    @Override
    public String toString() {
        return topLine + " , " + bottomLine;
    }








    /// DEBUGGINGG

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

        g.setColor(Color.PINK);
        g.drawPolygon(xPoints,yPoints,4);
    }
}
