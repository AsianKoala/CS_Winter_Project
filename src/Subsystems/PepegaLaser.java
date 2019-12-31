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


        rect.draw(g);
    }
}
