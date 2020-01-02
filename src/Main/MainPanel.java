package Main;

import Geometry.Point;
import Subsystems.Asteroid;
import Subsystems.Background;
import Subsystems.Player;
import Subsystems.Scoreboard;

import static Util.UtilMethods.*;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    // init all our objects
    private Player ourPlayer = new Player(new Point(415,400), new Point(400,430), new Point(430,430), Color.GREEN);
    private Background ourBackground = new Background(800, 800);
    private Scoreboard ourScoreboard = new Scoreboard();



    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();

        ourPlayer.initListeners(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        if(!ourScoreboard.gameIsDone) {
            super.paintComponent(g);

            ourBackground.run(g);
            ourScoreboard.run(g);
            ourPlayer.run(g);
            Asteroid.runOurAsteroidsList(g, ourPlayer.getCentroid());


            sleep(15);
            repaint();
        }

        else {
            super.paintComponent(g);
            ourScoreboard.endingScreen(g);
        }
    }
}
