package Subsystems;

import Geometry.Line;
import Geometry.Point;
import Geometry.Square;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends Square implements Subsystem {
    public static ArrayList<Asteroid> ourAsteroids = new ArrayList<>(); // TODO: CHANGE THIS TO PRIVATE WHEN DONE TESTING
    private static ArrayList<Asteroid> removeList = new ArrayList<>();
    private static final double intersectionRadius = 15;
    private static int hitsTaken = 0;
    private static long lastLoopTime;


    private Point targetPoint;
    private double distanceToTargetPoint;
    private double slopeToPoint;


    private Asteroid(Line topLine) {
        super(topLine);
    }


    public void setTargetPoint(Point targetPoint) { this.targetPoint = targetPoint; }
    public void setSlope(double slope) { this.slopeToPoint = slope; }
    public void setDistanceToTargetPoint(double distanceToTargetPoint) { this.distanceToTargetPoint = distanceToTargetPoint; }







    @Override
    public void run(Graphics g) {
        g.setColor(Color.RED.darker());
        g.fillPolygon(xPoints(), yPoints(), 4);
    }




    /**
     * the big boi is right here
     * @param g link to graphics
     * @param targetPoint
     */
    public static void runOurAsteroidsList(Graphics g, Point targetPoint) {
        for(Asteroid a : ourAsteroids) {
            a.setTargetPoint(targetPoint);
            a.setDistanceToTargetPoint(Math.hypot(a.centroid().x - a.targetPoint.x, a.centroid().y - a.targetPoint.y));
            a.setSlope((targetPoint.y - a.centroid().y) / (targetPoint.y - a.centroid().x));


            if(a.distanceToTargetPoint <= intersectionRadius) {
                removeList.add(a);
                hitsTaken++;
            }

            ourAsteroids.removeAll(removeList); // removing all things that hit the thing




            // im not sure how much the asteroids should go but im going to just accel them at the slope
            a.shiftRaw(5, a.slopeToPoint);

            a.run(g);
        }


    }



    private static void handleGeneration() {
        if(System.currentTimeMillis() - lastLoopTime < 1000) {
            return;
        }

        double verticalPercentage = Math.random() * 800;
        double horizontalPercentage = Math.random() * 800;

        ourAsteroids.add(new Asteroid(new Line(new Point(horizontalPercentage + 50,verticalPercentage), new Point(horizontalPercentage + 50,verticalPercentage))));
    }
}
