package Subsystems;

import Geometry.Line;
import Geometry.Point;
import Geometry.Triangle;
import Util.Telemetry;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Subsystems.Controls.*;


public class Player extends Triangle implements Subsystem {
    private static Player instance;

    public static Player getInstance() {
        return instance;
    }


    private Telemetry telemetry = new Telemetry(0);
    private Color ourColor;




    // translation
    public static int movementX = 0;
    public static int movementY = 0;


    // laser
    private ArrayList<Laser> ourLasers = new ArrayList<>();
    private Triangle guideTriangle;
    private long lastReloadTime = 0;


    // linkage to scoreboard
    static int score = 0;







    /**
     * @param top   top point
     * @param left  left point
     * @param right right point
     * @param color color
     */

    public Player(Point top, Point left, Point right, Color color) {
        super(top, left, right);

        Player.instance = this;

        guideTriangle = new Triangle(top, left, right);
        ourColor = color;
    }





    // link up with Asteroids
    public Point getCentroid() {
        return new Point((top.x + left.x + right.x) / 3, (top.y + left.y + right.y) / 3);
    }

    private double getClickAngle() {
        return -Math.toDegrees(Math.atan2(dragPoint.x - (top.x + left.x + right.x) / 3f, dragPoint.y - (top.y + left.y + right.y) / 3f)) + 90;
    }


    // draws from the point numbers
    private void draw(Graphics g) {
        offsetMovement();

        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();


        int xE = (int) (top.x + left.x + right.x) / 3;
        int yE = (int) (top.y + left.y + right.y) / 3;


        int[] xPoints = {
                (int) left.x,
                (int) top.x,
                (int) right.x
        };

        int[] yPoints = {
                (int) left.y,
                (int) top.y,
                (int) right.y
        };


        at.setToRotation(Math.toRadians(getClickAngle() + 90), xE, yE);
        dragPoint = guideTriangle.top;
        g2d.setTransform(at);
        g2d.setColor(ourColor);


        g2d.drawPolygon(xPoints, yPoints, 3);


        Point2D point1 = at.transform(new java.awt.Point(xPoints[0], yPoints[0]), new Point2D.Double());
        Point2D point2 = at.transform(new java.awt.Point(xPoints[1], yPoints[1]), new Point2D.Double());
        Point2D point3 = at.transform(new java.awt.Point(xPoints[2], yPoints[2]), new Point2D.Double());


        guideTriangle.left = new Point(point1.getX(), point1.getY());
        guideTriangle.top = new Point(point2.getX(), point2.getY());
        guideTriangle.right = new Point(point3.getX(), point3.getY());


        // Guide
        g2d.setColor(Color.RED);
        g2d.drawLine(xE, yE, (int) top.x, (int) top.y);
    }


    // this changes the point numbers
    private void offsetMovement() {
        offset(movementX, movementY);
    }


    private void wrapAroundLogic() {

        // make them super big/small since they r arbitrary
        double biggestX = -1000000000;
        double biggestY = -1000000000;
        double smallestX = 1000000000;
        double smallestY = 1000000000;
        for (Point p : allVertices()) {
            biggestX = Math.max(biggestX, p.x);
            biggestY = Math.max(biggestY, p.y);

            smallestX = Math.min(smallestX, p.x);
            smallestY = Math.min(smallestY, p.y);
        }


        // wrap around
        int screenWidth = 800;
        if (biggestX < 0) {
            offset((screenWidth - 20) - biggestX, 0);
        }
        int screenHeight = 800;
        if (biggestY < 0) {
            offset(0, (screenHeight - 20) - biggestY);
        }

        if (smallestX > screenWidth) {
            offset(-smallestX + 20, 0);
        }
        if (smallestY > screenHeight) {
            offset(0, -smallestY + 20);
        }
    }


    private void handleLasers(Graphics g) {
        long reloadTime = 500;
        if (is_rmb_pressed && (System.currentTimeMillis() - lastReloadTime > reloadTime)) {
            ourLasers.add(new Laser(new Line(centroid(), new Point(guideTriangle.top.x, guideTriangle.top.y)), 3, 5));
            lastReloadTime = System.currentTimeMillis();
        }


        ArrayList<Laser> intersection = new ArrayList<>();
        for (Laser laser : ourLasers) {
            if (Math.hypot(400 - laser.topLine.startPoint.x, 400 - laser.topLine.startPoint.y) >= 400) {
                intersection.add(laser);
            }


            // laser intersection with asteroid
            for (Asteroid a : Asteroid.ourAsteroids) {
                if (Math.hypot(laser.topLine.startPoint.x - a.centroid().x, laser.topLine.startPoint.y - a.centroid().y) <= Asteroid.size ||
                        Math.hypot(laser.topLine.endPoint.x - a.centroid().x, laser.topLine.endPoint.y - a.centroid().y) <= Asteroid.size) {
                    a.removeMe();
                    score += 100;
                }
            }


            laser.run(g);
        }


        ourLasers.removeAll(intersection); // garbage collector
    }


    private void handleTelemetry() {
        telemetry.addData("xPos, yPos", centroid().toString());
        telemetry.addData("top", top.toString());
        telemetry.addData("left", left.toString());
        telemetry.addData("right", right.toString());
        telemetry.addLine("wtf");
        telemetry.addLine(guideTriangle.top.toString());
        telemetry.addLine(guideTriangle.left.toString());
        telemetry.addLine(guideTriangle.right.toString());
        telemetry.addData("amount of lasers active", ourLasers.size());
    }




    @Override
    public void run(Graphics g) {
        movementLogic();
        wrapAroundLogic();
        draw(g);
        handleLasers(g);

        handleTelemetry();


        telemetry.run(g);
    }
}
