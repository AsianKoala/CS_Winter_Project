package Subsystems;

import Geometry.Line;
import Geometry.Point;
import Geometry.Triangle;
import Util.Telemetry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Player extends Triangle implements Subsystem {
    private Telemetry telemetry = new Telemetry(0);
    private Color ourColor;

    private double clickAngle = 0;

    // translation
    private int movementX = 0;
    private int movementY = 0;


    // laser
    private ArrayList<Laser> ourLasers = new ArrayList<>();
    private boolean is_rmb_pressed = false;
    private Triangle guideTriangle;
    private long lastReloadTime = 0;


    private boolean leftKeyPressed = false;
    private boolean topKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean bottomKeyPressed = false;

    private long leftKeyReadTime = 0;
    private long rightKeyReadTime = 0;
    private long topKeyReadTime = 0;
    private long bottomKeyReadTime = 0;

    private long getCurrentTime() { return System.currentTimeMillis(); }

    /**
     * Explanation of why we have to create booleans that we write to when we get inputs instead of writing to the actual used vars ( movementX & Y)
     *                                  ------------------------------------------------------------------------
     *      So previously, movementX and Y were changed directly from the inputs that we get from the keyboard. This was fine, but because of how key events are
     *      taken as 1 input, previous inputs are NOT saved; only the current input(1) input is read. Because of this, when you hold down a key, and then press another key, the 2nd key will be
     *      read, but because the 1st key is no longer the one who was pressed down. To solve this normal keyboards have this thing called rollover, but we have to program
     *      it in since KeyAdapter sucks that way. So we just assign values to booleans and have a logic method that sorts out movementX and movementY. Kinda dumb but whatever lol.
     *      I'm pretty sure im over complicating it since it actually doesn't really matter that much since the key that is held down after the 2nd key is released is actually read by KeyAdapter,
     *      but there is so much lag it annoys me.
     */

    private KeyAdapter ourKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D :
                    rightKeyReadTime = getCurrentTime();
                    rightKeyPressed = true;
                    break;

                case VK_A:
                    leftKeyReadTime = getCurrentTime();
                    leftKeyPressed = true;
                    break;

                case VK_W:
                    topKeyReadTime = getCurrentTime();
                    topKeyPressed = true;
                    break;

                case VK_S:
                    bottomKeyReadTime = getCurrentTime();
                    bottomKeyPressed = true;
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D :
                    rightKeyPressed = false;
                    break;

                case VK_A:
                    leftKeyPressed = false;
                    break;

                case VK_W:
                    topKeyPressed = false;
                    break;

                case VK_S:
                    bottomKeyPressed = false;
                    break;

            }
        }
    };



    private void movementLogic() {
        if(leftKeyPressed && rightKeyPressed) {
            if(leftKeyReadTime > rightKeyReadTime) movementX = -5;
            else movementX = 5;
        }
        
        else if(leftKeyPressed) movementX = -5;
        else if(rightKeyPressed) movementX = 5;
        else movementX = 0;

        if(topKeyPressed && bottomKeyPressed) {
            if(topKeyReadTime > bottomKeyReadTime) movementY = -5;
            else movementY = 5;
        }

        else if(topKeyPressed) movementY = -5;
        else if(bottomKeyPressed) movementY = 5;
        else movementY = 0;
    }






    private MouseMotionAdapter ourMouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            clickAngle = -Math.toDegrees(Math.atan2(e.getX() - (top.x + left.x + right.x)/3f, e.getY() - (top.y + left.y + right.y)/3f)) + 90;
        }
    };




    private MouseAdapter ourMouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            clickAngle = -Math.toDegrees(Math.atan2(e.getX() - (top.x + left.x + right.x)/3f, e.getY() - (top.y + left.y + right.y)/3f)) + 90;

            if(e.getButton() == MouseEvent.BUTTON3) {
                is_rmb_pressed = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3) {
                is_rmb_pressed = false;
            }
        }
    };






    /**
     *
     * @param top top point
     * @param left left point
     * @param right right point
     * @param color color
     */

    public Player(Point top, Point left, Point right, Color color) {
        super(top,left,right);
        guideTriangle = new Triangle(top,left,right);
        ourColor = color;
    }




    // link up with panel
    public void initListeners(JPanel panel) {
        panel.addMouseListener(ourMouseAdapter);
        panel.addMouseMotionListener(ourMouseMotionAdapter);
        panel.addKeyListener(ourKeyAdapter);
    }


    // link up with Asteroids
    public Point getCentroid() { return new Point((top.x + left.x + right.x)/3, (top.y + left.y + right.y)/3); }










// draws from the point numbers
    private void draw(Graphics g) {
        offsetMovement();

        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();


        int xE = (int)(top.x + left.x + right.x)/3;
        int yE = (int)(top.y + left.y + right.y)/3;



        int[] xPoints = {
                (int)left.x,
                (int)top.x,
                (int)right.x
        };

        int[] yPoints = {
                (int)left.y,
                (int)top.y,
                (int)right.y
        };


        at.setToRotation(Math.toRadians(clickAngle+90), xE, yE);
        g2d.setTransform(at);
        g2d.setColor(ourColor);


        g2d.drawPolygon(xPoints, yPoints, 3);


        Point2D point1 = at.transform(new java.awt.Point(xPoints[0],yPoints[0]), new Point2D.Double());
        Point2D point2 = at.transform(new java.awt.Point(xPoints[1],yPoints[1]), new Point2D.Double());
        Point2D point3 = at.transform(new java.awt.Point(xPoints[2],yPoints[2]), new Point2D.Double());


        guideTriangle.left = new Point(point1.getX(), point1.getY());
        guideTriangle.top = new Point(point2.getX(), point2.getY());
        guideTriangle.right = new Point(point3.getX(), point3.getY());



        // Guide
        g2d.setColor(Color.RED);
        g2d.drawLine(xE, yE, (int)top.x, (int)top.y);
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
        for(Point p : allVertices()) {
            biggestX = Math.max(biggestX, p.x);
            biggestY = Math.max(biggestY, p.y);

            smallestX = Math.min(smallestX, p.x);
            smallestY = Math.min(smallestY, p.y);
        }


        // wrap around
        int screenWidth = 800;
        if(biggestX < 0) { offset((screenWidth - 20) - biggestX, 0); }
        int screenHeight = 800;
        if(biggestY < 0 ) { offset( 0, (screenHeight - 20) - biggestY); }

        if(smallestX > screenWidth) { offset(-smallestX + 20, 0); }
        if(smallestY > screenHeight) { offset(0, -smallestY + 20); }
    }



    private void handleLasers(Graphics g) {
        long reloadTime = 500;
        if (is_rmb_pressed && (System.currentTimeMillis() - lastReloadTime > reloadTime)) {
                ourLasers.add(new Laser(new Line(centroid(), new Point(guideTriangle.top.x, guideTriangle.top.y)), 3, 5));
                lastReloadTime = System.currentTimeMillis();
            }


            ArrayList<Laser> intersection = new ArrayList<>();
            for (Laser laser : ourLasers) {
                if (Math.hypot(400 - laser.topLine.startPoint.x, 400 - laser.topLine.startPoint.y) >= 800) {
                         intersection.add(laser);
                }

                laser.run(g);
            }


        ourLasers.removeAll(intersection); // garbage collector
    }





    private void handleTelemetry() {
        telemetry.addData("xPos, yPos", centroid().toString());
        telemetry.addData("top" , top.toString());
        telemetry.addData("left" , left.toString());
        telemetry.addData("right" , right.toString());
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
