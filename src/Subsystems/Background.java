package Subsystems;

import Geometry.Point;

import java.awt.*;
import java.util.ArrayList;

public class Background implements Subsystem {
    private int width, height;
    public ArrayList<Point> allPoints = new ArrayList<>();

    public Background() {
        width = 800;
        height = 800;
    }

    public Background(int width, int height) {
        this.height = height;
        this.width = width;
    }


    public void initPoints(double amt) {
        for (int i = 0; i < amt; i++) {
            allPoints.add(new Point((int) (Math.random() * width), (int) (Math.random() * height)));
        }
    }


    @Override
    public void run(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }
}
