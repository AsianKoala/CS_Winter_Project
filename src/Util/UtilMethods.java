package Util;

import javax.swing.*;
import java.awt.*;

public class UtilMethods {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }


    public static void runFrame(Container container) {
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(container);
        frame.setVisible(true);
    }
}
