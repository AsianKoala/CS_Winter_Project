package Subsystems;

import Geometry.BetterRectangle;
import Geometry.Line;

import java.awt.*;

public class BetterLaser extends BetterRectangle implements Subsystem {
    private double speed;


    public BetterLaser(Line topLine, Line bottomLine, double speed) {
        super(topLine, bottomLine);
        this.speed = speed;
    }



    public BetterLaser(Line topLine) {
        super(topLine, 5);
        speed = 5;
    }



    @Override
    public void run(Graphics g) {
        shiftRect(speed);
        g.setColor(Color.GREEN);

        g.fillRect((int)topLine.startPoint.x, (int)topLine.startPoint.y,
                (int)topLine.getLength(),
                (int)new Line(topLine.startPoint, bottomLine.startPoint).getLength());
    }
}
