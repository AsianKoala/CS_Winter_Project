package Subsystems;

import Util.ScreenHandler;

import java.awt.*;
import java.text.DecimalFormat;

import static Util.Globals.asteroidGeneration;
import static Util.Globals.asteroidSpeed;

public class Menu implements Subsystem {
    private DecimalFormat df = new DecimalFormat("0.0");

    static boolean enterPressed = false;
    static boolean left_bracket_pressed = false;
    static boolean right_bracket_pressed = false;
    static boolean minus_pressed = false;
    static boolean equals_pressed = false;


    private static long lastMenuCalcTime = 0;

    private void calculations() {
        if (System.currentTimeMillis() - lastMenuCalcTime < 250) {
            return;
        }

        if (minus_pressed) {
            asteroidSpeed--;
            lastMenuCalcTime = System.currentTimeMillis();
        }

        if (equals_pressed) {
            asteroidSpeed++;
            lastMenuCalcTime = System.currentTimeMillis();
        }

        if (left_bracket_pressed) {
            asteroidGeneration -= 0.1;
            lastMenuCalcTime = System.currentTimeMillis();
        }

        if (right_bracket_pressed) {
            asteroidGeneration += 0.1;
            lastMenuCalcTime = System.currentTimeMillis();
        }

        if (equals_pressed) {
            lastMenuCalcTime = System.currentTimeMillis();
        }
    }


    @Override
    public void run(Graphics g) {
        calculations();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(" Neil's CS Game", 300, 100);


        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString(" Asteroid speed    :       " + asteroidSpeed, 230, 300);
        g.drawString(" Asteroids per sec :       " + df.format(asteroidGeneration), 230, 400);


        g.setFont(new Font("Arial", Font.PLAIN, 17));
        g.drawString("press - and = to modify speed", 230, 500);
        g.drawString("press [ and ] to modify gen rate", 230, 515);
        g.drawString("press enter to continue", 320, 700);


        if (enterPressed) {
            ScreenHandler.nextScreen();
            enterPressed = false;
        }
    }
}
