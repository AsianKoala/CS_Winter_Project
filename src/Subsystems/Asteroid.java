package Subsystems;

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Square;

import javax.xml.transform.stax.StAXResult;
import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends Square implements Subsystem {
    private static ArrayList<Asteroid> ourAsteroids = new ArrayList<>();
    private static ArrayList<Asteroid> removeList = new ArrayList<>();
    private static final double intersectionRadius = 15;
    private static int hitsTaken = 0;


   private Point targetPoint;
    private double velocity;
    private double distanceToTargetPoint;
    private double slopeToPoint;


    public Asteroid(Line topLine) {
        super(topLine);
    }


    public void setTargetPoint(Point targetPoint) { this.targetPoint = targetPoint; }

    @Override
    public void run(Graphics g) {

    }






    public static void runOurAsteroidsList() {
        for(Asteroid a : ourAsteroids) {
            if(Math.hypot(a.centroid().x - a.targetPoint.x, a.centroid().y - a.targetPoint.y) <= intersectionRadius) { removeList.add(a); } // removing all things that hit the thing

            // im not sure how much the asteroids should go but im going to just accel them at the slope

        }
    }


}
