package Testers;

import Subsystems.Background;

import javax.swing.*;
import java.awt.*;

public class backgroundtest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BackgroundPanel());
        frame.setVisible(true);
    }
}


class BackgroundPanel extends JPanel {

    private Background background = new Background(800, 800);


    @Override
    public void paintComponent(Graphics g) {
        //   g.setColor(Color.BLACK);
        background.initPoints(100);
        background.run(g);

        System.out.println(background.allPoints);
    }
}
