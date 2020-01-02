package Subsystems;

import java.awt.*;

public class Scoreboard implements Subsystem {
    private long startTime = System.currentTimeMillis();
    private long remainingTime;

    private int score = 0;
    private int lives = 5;
    public boolean gameIsDone = false;


    public Scoreboard() {

    }

    private void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Time remaining: " + remainingTime, 15, 30);
        g.drawString("Score: " + score, 15, 60);
        g.drawString("Lives: " + lives, 15, 90);
    }


    @Override
    public void run(Graphics g) {
        lives = 5 - Asteroid.hitsTaken;
        score = Player.score;

        long currTime = System.currentTimeMillis();
        remainingTime = (60 - (currTime - startTime) / 1000);

        draw(g);


        if (remainingTime == 0 || lives == 0) {
            gameIsDone = true;
        }
    }


    public void endingScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(" Your score:       " + score, 250, 300);
    }
}
