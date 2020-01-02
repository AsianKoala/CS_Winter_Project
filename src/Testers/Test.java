package Testers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;

import static java.awt.event.KeyEvent.*;

public class Test {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocation(1920 / 2, 1080 / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new lolz());
        frame.setVisible(true);
    }
}


class lolz extends JPanel {
    private int[] yPoints = {50, 0, 50};
    private int[] xPoints = {0, 25, 50};


    private double coolAngle = 0f;

    // translation stuff
    private double movementX = 0;
    private double movementY = 0;


    lolz() {

        setFocusable(true);
        requestFocusInWindow();


        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse dragged called");
                coolAngle = -Math.toDegrees(Math.atan2(e.getX() - (xPoints[0] + xPoints[1] + xPoints[2]) / 3f, e.getY() - (yPoints[0] + yPoints[1] + yPoints[2]) / 3f)) + 90;
            }
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed: " + e.toString());
                switch (e.getKeyCode()) {
                    case VK_D:
                        movementX = 5;
                        break;

                    case VK_A:
                        movementX = -5;
                        break;

                    case VK_W:
                        movementY = -5;
                        break;

                    case VK_S:
                        movementY = 5;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("key released: " + e.toString());
                switch (e.getKeyCode()) {
                    case VK_D:
                        movementX = 0;
                        break;

                    case VK_A:
                        movementX = 0;
                        break;

                    case VK_W:
                        movementY = 0;
                        break;

                    case VK_S:
                        movementY = 0;
                        break;

                }
            }
        });
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();


        int xE = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
        int yE = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;


        at.setToRotation(Math.toRadians(coolAngle + 90), xE, yE);
        g2d.setTransform(at);
        g2d.drawPolygon(xPoints, yPoints, 3);


        // Guide
        g2d.setColor(Color.RED);
        g2d.drawLine(xE, yE, xPoints[1], yPoints[1]);


        translationLogic();

        try {
            Thread.sleep(15);
        } catch (Exception ignored) {
        }


        repaint();
    }


    private void translationLogic() {
        if (movementX == 0 && movementY == 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            xPoints[i] += movementX;
            yPoints[i] += movementY;
        }

        repaint();
    }


}