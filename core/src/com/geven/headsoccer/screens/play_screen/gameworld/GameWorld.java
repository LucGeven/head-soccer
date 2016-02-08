package com.geven.headsoccer.screens.play_screen.gameworld;

import com.geven.headsoccer.screens.play_screen.collision.BallCollision;
import com.geven.headsoccer.screens.play_screen.collision.SpriteHomeCollision;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.Goal;
import com.geven.headsoccer.screens.play_screen.objects.Score;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.SpriteOut;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;

public class GameWorld {
    private Score score;
    private TimerHandler timeHandler;
    private SpriteHome spriteHome;
    private SpriteOut spriteOut;
    private Ball ball;
    private Goal goal;

    private SpriteHomeCollision spriteHomeCollision;
    private BallCollision ballCollision;

    public static boolean goalScore;
    private float timer;

    public GameWorld(){
        score = new Score();
        timeHandler = new TimerHandler();
        spriteHome = new SpriteHome();
        spriteOut = new SpriteOut();
        ball = new Ball();
        goal = new Goal();

        spriteHomeCollision = new SpriteHomeCollision();
        ballCollision = new BallCollision();

        goalScore = false;
        timer = 0;
    }

    public void update(float delta){
        score.update();
        timeHandler.update(delta);
        spriteHome.update(delta);
        spriteOut.update(delta);

        ball.update(delta);

        spriteHomeCollision.update();
        ballCollision.update();

        //Wait 2 second to set the new positions after the goal
        if (goalScore){
            if (timer >= 2){
                score.setPosition();
                goalScore = false;
                timer = 0;
            }
            else {
                timer += delta;
            }

        }

    }
}
