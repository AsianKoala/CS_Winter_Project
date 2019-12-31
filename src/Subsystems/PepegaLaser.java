package Subsystems;

import Geometry.Rectangle;

import java.awt.*;

public class PepegaLaser implements Subsystem {
    private double speed;
    Rectangle rect;

    public PepegaLaser(Rectangle rect, double speed) {
        this.rect = rect;
        this.speed = speed;
    }


    @Override
    public void run(Graphics g) {
        g.setColor(Color.GREEN);

        int[] xPoints = {
                (int)rect.topLine.startPoint.x,
                (int)rect.topLine.endPoint.x,
                (int)rect.bottomLine.endPoint.x,
                (int)rect.bottomLine.startPoint.x,
        };

        int[] yPoints = {
                (int)rect.topLine.startPoint.y,
                (int)rect.topLine.endPoint.y,
                (int)rect.bottomLine.endPoint.y,
                (int)rect.bottomLine.startPoint.y,
        };

        g.drawPolygon(xPoints,yPoints,4);
    }
}
