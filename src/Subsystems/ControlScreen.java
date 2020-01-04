package Subsystems;

import Util.ScreenHandler;

import java.awt.*;

import static Subsystems.Menu.enterPressed;
import static Subsystems.Menu.lastMenuCalcTime;

public class ControlScreen implements Subsystem {




    @Override
    public void run(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));


        g.drawString("CONTROLS", 350, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 17));


        g.drawString("WASD -> Player translation controls", 100, 200);
        g.drawString("Space -> Shoot in current direction", 100,250);
        g.drawString("Left click (dragging also works, flexible) -> Turn/Point in that direction", 100, 300);
        g.drawString("Right click -> Point and shoot in that direction", 100, 350);
        g.drawString("press enter to continue", 320, 700);



        if(enterPressed) {
            ScreenHandler.nextScreen();
        }
    }
}
