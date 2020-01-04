package Subsystems;

import Geometry.Line;
import Geometry.Rectangle;

import java.awt.*;

public class Laser extends Rectangle implements Subsystem {
    private double speed;

    public Laser(Line topLine, double width, double speed) {
        super(topLine, width);

        this.speed = speed;
    }


    @Override
    public String toString() {
        return topLine + "  ,  " + bottomLine;
    }


    @Override
    public void run(Graphics g) {
        g.setColor(Color.GREEN);
        shiftRect(speed);

        int[] xPoints = {
                (int) Math.round(topLine.startPoint.x),
                (int) Math.round(topLine.endPoint.x),
                (int) Math.round(bottomLine.endPoint.x),
                (int) Math.round(bottomLine.startPoint.x)
        };

        int[] yPoints = {
                (int) Math.round(topLine.startPoint.y),
                (int) Math.round(topLine.endPoint.y),
                (int) Math.round(bottomLine.endPoint.y),
                (int) Math.round(bottomLine.startPoint.y)
        };


        g.fillPolygon(xPoints, yPoints, 4);
    }
}
