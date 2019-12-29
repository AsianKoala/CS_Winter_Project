package Subsystems;

import Geometry.Line;

import java.awt.*;

public class Laser implements Subsystem {
    public Line laserLine;

    public double speed;


    public Laser(Line laserLine, double speed) {
        this.laserLine = laserLine;
        this.speed = speed;
    }



    private void calculateNewPosition() {
        laserLine.shiftLine(speed);
    }


    @Override
    public void run(Graphics g) {
        calculateNewPosition();
        g.setColor(Color.BLACK);
        g.drawLine((int)laserLine.startPoint.x, (int)laserLine.startPoint.y, (int)laserLine.endPoint.x, (int)laserLine.endPoint.y);
    }
}
