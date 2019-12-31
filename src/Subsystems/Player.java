package Subsystems;

import Geometry.Line;
import Geometry.Point;
import Geometry.Triangle;
import Util.Telemetry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Player extends Triangle implements Subsystem {
    private Telemetry telemetry = new Telemetry(0);
    private Color ourColor;

    private double clickAngle = 0;

    // translation
    private int movementX = 0;
    private int movementY = 0;


    // wrap around
    private int screenWidth = 800;
    private int screenHeight = 800;



    // laser
    ArrayList<BetterLaser> ourLasers = new ArrayList<>();
    private boolean is_F_Pressed = false;


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

                case VK_F:
                    is_F_Pressed = true;
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
        ourColor = color;
    }




    // link up with panel
    public void initListeners(JPanel panel) {
        panel.addMouseListener(ourMouseAdapter);
        panel.addMouseMotionListener(ourMouseMotionAdapter);
        panel.addKeyListener(ourKeyAdapter);
    }












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
        double biggestX = -100000;
        double biggestY = -100000;
        double smallestX = 100000;
        double smallestY = 100000;
        for(Point p : allVertices()) {
            biggestX = Math.max(biggestX, p.x);
            biggestY = Math.max(biggestY, p.y);

            smallestX = Math.min(smallestX, p.x);
            smallestY = Math.min(smallestY, p.y);
        }


        if(biggestX < 0) { offset((screenWidth - 20) - biggestX, 0); }
        if(biggestY < 0 ) { offset( 0, (screenHeight - 20) - biggestY); }

        if(smallestX > screenWidth) { offset(-smallestX + 20, 0); }
        if(smallestY > screenHeight) { offset(0, -smallestY + 20); }
    }



    private void laserLogic(Graphics g) {
        for(BetterLaser laser : ourLasers) {
            if(laser.topLine.endPoint.x < 0 || laser.topLine.endPoint.x > 800 + laser.topLine.getLength()) {
                ourLasers.remove(laser);
            }

            laser.run(g);
        }
    }




    private void handleTelemetry() {
        telemetry.addData("xPos, yPos: ", (top.x + left.x + right.x)/3 + (top.y + left.y + right.y)/3);
    }







    @Override
    public void run(Graphics g) {
        movementLogic();
        wrapAroundLogic();
        laserLogic(g);
        draw(g);

        handleTelemetry();
        telemetry.run(g);
    }
}
