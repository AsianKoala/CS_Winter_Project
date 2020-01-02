package Subsystems;

import java.awt.*;

public class Scoreboard implements Subsystem {
    private long startTime = System.currentTimeMillis();
    private long remainingTime;

    private int score;
    private int lives = 5;


    public Scoreboard() {

    }




    public void giveMeScore(int score) {
        this.score = score;
    }
    public void setLives(int lives) { this.lives = lives; }


    private void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Time remaining: " + remainingTime, 15, 30);
        g.drawString("Score: " + score, 15, 60);
        g.drawString("Lives: " + lives, 15, 90);
    }



    @Override
    public void run(Graphics g) {
        long currTime = System.currentTimeMillis();
        remainingTime = (60 - (currTime - startTime)/1000);

        draw(g);


        if(remainingTime == 0 || lives == 0) { System.exit(0); }
    }
}
