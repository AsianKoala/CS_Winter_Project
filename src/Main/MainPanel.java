package Main;

import Geometry.Point;
import Subsystems.Background;
import Subsystems.Player;
import static Util.UtilMethods.*;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    // init all our members
    private Player ourPlayer = new Player(new Point(15,0), new Point(0,30), new Point(30,30), Color.GREEN);
    private Background ourBackground = new Background(800, 800);


    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();

        ourPlayer.initListeners(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ourBackground.run(g);
        ourPlayer.run(g);


        sleep(15);
        repaint();
    }
}
