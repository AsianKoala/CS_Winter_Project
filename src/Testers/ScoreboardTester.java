package Testers;

import Subsystems.Background;
import Subsystems.Scoreboard;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class ScoreboardTester {
    public static void main(String[] args) {
        UtilMethods.runFrame(new ScoreboardPanel());
    }
}


class ScoreboardPanel extends JPanel {
    private Scoreboard scoreboard = new Scoreboard();
    private Background background = new Background();



    @Override
    public void paintComponent(Graphics g) {
        background.run(g);
        scoreboard.run(g);

        UtilMethods.sleep(15);
        repaint();
    }
}
