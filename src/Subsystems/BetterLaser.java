package Subsystems;

import Geometry.Line;
import Geometry.Rectangle;

import java.awt.*;

public class BetterLaser extends Rectangle implements Subsystem {
    double speed;

    public BetterLaser(Line topLine, double width, double speed) {
        super(topLine,width);

        this.speed = speed;
    }



    @Override
    public void run(Graphics g) {
        g.setColor(Color.GREEN);
        shiftRect(speed);

        int[] xPoints = {
                (int)topLine.startPoint.x,
                (int)topLine.endPoint.x,
                (int)bottomLine.endPoint.x,
                (int)bottomLine.startPoint.x
        };

        int[] yPoints = {
                (int)topLine.startPoint.y,
                (int)topLine.endPoint.y,
                (int)bottomLine.endPoint.y,
                (int)bottomLine.startPoint.y
        };


        g.drawPolygon(xPoints,yPoints,4);
    }
}
