package Main;

import Geometry.Point;
import Subsystems.Menu;
import Subsystems.*;
import Util.ScreenHandler;

import javax.swing.*;
import java.awt.*;

import static Util.UtilMethods.sleep;

public class MainPanel extends JPanel {

    // init all our objects
    private Player ourPlayer = new Player(new Point(415, 400), new Point(400, 430), new Point(430, 430), Color.GREEN);
    private Background ourBackground = new Background(800, 800);
    private Scoreboard ourScoreboard = new Scoreboard();
    private ControlScreen ourControlScreen = new ControlScreen();
    private Menu menu = new Menu();


    MainPanel() {
        setFocusable(true);
        requestFocusInWindow();

        ourPlayer.initListeners(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ourBackground.run(g);

        switch (ScreenHandler.getCurrentScreen()) {
            case 0:
                menu.run(g);
                break;

            case 1:
                ourControlScreen.run(g);
                break;

            case 2:
                menu = null;
                ourPlayer.run(g);
                ourScoreboard.run(g);
                Asteroid.runOurAsteroidsList(g, ourPlayer.getCentroid());
                break;

            case 3:
                ourPlayer = null;
                ourScoreboard.endingScreen(g);
                break;

            case 4:
                System.exit(1);
                break;

        }

        sleep(15);
        repaint();
    }
}
