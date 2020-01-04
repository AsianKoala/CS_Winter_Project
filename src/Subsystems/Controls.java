package Subsystems;

import Geometry.Point;

import static Subsystems.Player.*;

import javax.swing.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_S;

public class Controls {

    public static Point dragPoint = new Point(0,400);

    static boolean is_rmb_pressed = false;
    static boolean is_f_pressed = false;


    private static boolean leftKeyPressed = false;
    private static boolean topKeyPressed = false;
    private static boolean rightKeyPressed = false;
    private static boolean bottomKeyPressed = false;

    private static long leftKeyReadTime = 0;
    private static long rightKeyReadTime = 0;
    private static long topKeyReadTime = 0;
    private static long bottomKeyReadTime = 0;

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }






    private static KeyAdapter ourKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D:
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
                    is_f_pressed = true;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D:
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

                case VK_F:
                    is_f_pressed = false;

            }
        }
    };

    private static MouseMotionAdapter ourMouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            dragPoint = new Point(e.getX(), e.getY());
        }
    };


    private static MouseAdapter ourMouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                is_rmb_pressed = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                is_rmb_pressed = false;
            }
        }
    };





    static void movementLogic() {
        if (leftKeyPressed && rightKeyPressed) {
            if (leftKeyReadTime > rightKeyReadTime) movementX = -5;
            else movementX = 5;
        } else if (leftKeyPressed) movementX = -5;
        else if (rightKeyPressed) movementX = 5;
        else movementX = 0;

        if (topKeyPressed && bottomKeyPressed) {
            if (topKeyReadTime > bottomKeyReadTime) movementY = -5;
            else movementY = 5;
        } else if (topKeyPressed) movementY = -5;
        else if (bottomKeyPressed) movementY = 5;
        else movementY = 0;
    }



    // link up with panel
    public static void initListeners(JPanel panel) {
        panel.addKeyListener(ourKeyAdapter);
        panel.addMouseMotionListener(ourMouseMotionAdapter);
        panel.addMouseListener(ourMouseAdapter);
    }
}
