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
    private static final double speed = 3;

    public static int hitsTaken = 0;
    private static long lastLoopTime;


    private Point targetPoint;
    private double distanceToTargetPoint;
    private double slopeToPoint;
    private boolean wantToRemoveMe = false;


    public Asteroid(Line topLine) {
        super(topLine);
    }


    private void setTargetPoint(Point targetPoint) { this.targetPoint = targetPoint; }
    private void setSlope(double slope) { this.slopeToPoint = slope; }
    private void setDistanceToTargetPoint(double distanceToTargetPoint) { this.distanceToTargetPoint = distanceToTargetPoint; }
    private void removeMe() { removeList.add(this); }

    private boolean isLeft() { return centroid().x - targetPoint.x < 0; }
    private boolean isAbove() { return centroid().y - targetPoint.y < 0; }
    private double getSlopeToPoint() { return slopeToPoint + 0.00000000000003; }


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
       // handleGeneration(); // handle our asteroid generation

        for(Asteroid a : ourAsteroids) {
            a.setTargetPoint(targetPoint);
            a.setDistanceToTargetPoint(Math.hypot(a.centroid().x - a.targetPoint.x, a.centroid().y - a.targetPoint.y));
            a.setSlope((targetPoint.y - a.centroid().y) / (targetPoint.y - a.centroid().x));

            // check for intersection of targetPoint
            if(a.distanceToTargetPoint < intersectionRadius) {
                a.removeMe();
                hitsTaken++;
            }
// just divide by speed lawl

//            if(a.isAbove()) {
//                if(a.isLeft()) {
//                    a.shiftRaw(speed, speed / a.getSlopeToPoint());
//                } else {
//                    a.shiftRaw(-speed, speed / a.getSlopeToPoint());
//                }
//
//            } else {
//                if(a.isLeft()) {
//                    a.shiftRaw(speed, speed / a.getSlopeToPoint());
//                } else {
//                    a.shiftRaw(-speed, -speed / a.getSlopeToPoint());
//                }
//            }


            if(a.isAbove()) {
                if(a.isLeft()) {
                    a.shiftRaw(speed, speed / a.getSlopeToPoint());
                } else {
                    a.shiftRaw(-speed, speed / a.getSlopeToPoint());
                }

            } else {
                if(a.isLeft()) {
                    a.shiftRaw(speed, speed / a.getSlopeToPoint());
                } else {
                    a.shiftRaw(-speed, -speed / a.getSlopeToPoint());
                }
            }




            // drawing
            a.run(g);
        }

        ourAsteroids.removeAll(removeList); // removing all things that hit the thing
    }




    private static void handleGeneration() {
        if(System.currentTimeMillis() - lastLoopTime < 1000) {
            return;
        }

        double verticalPercentage = Math.random() * 780;
        double horizontalPercentage = Math.random() * 780;
        boolean isYRandom = Math.random() <= 0.5;
        boolean isLeft = Math.random() <= 0.5;
        boolean isTop = Math.random() <= 0.5;


        Point leftPoint, rightPoint;

        if(isYRandom) {
            if(isLeft) {
                leftPoint = new Point(0,verticalPercentage);
                rightPoint = new Point(20, verticalPercentage);
            } else {
                leftPoint = new Point(763, verticalPercentage);
                rightPoint = new Point(783, verticalPercentage);
            }
        } else {
            if(isTop) {
                leftPoint = new Point(horizontalPercentage, 0);
                rightPoint = new Point(horizontalPercentage + 20, 0);
            } else {
                leftPoint = new Point(horizontalPercentage, 740);
                rightPoint = new Point(horizontalPercentage + 20, 740);
            }
        }

        ourAsteroids.add(new Asteroid(new Line(leftPoint, rightPoint)));
        System.out.println(new Asteroid(new Line(leftPoint, rightPoint)) + "\n isYRandom: " + isYRandom +  "\n isLeft: " + isLeft  );

        lastLoopTime = System.currentTimeMillis();
    }





    @Override
    public String toString() {
        return super.toString();
    }
}
