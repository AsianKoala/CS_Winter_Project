package Subsystems;

import Geometry.Line;

import java.awt.*;

public class BetterLaser extends Line implements Subsystem {
    private double speed;




    public BetterLaser(Line topLine, double speed) {
        super(topLine.startPoint, topLine.endPoint);
        this.speed = speed;
    }



    @Override
    public void run(Graphics g) {
        shiftLine(speed);
        g.setColor(Color.GREEN);

        g.fillRect((int)startPoint.x, (int)startPoint.y, (int)getLength(), 3);
    }
}
