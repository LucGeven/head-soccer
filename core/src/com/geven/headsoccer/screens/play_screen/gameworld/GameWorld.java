package com.geven.headsoccer.screens.play_screen.gameworld;

import com.geven.headsoccer.screens.play_screen.handler.InputHandler;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.Goal;
import com.geven.headsoccer.screens.play_screen.objects.Ground;
import com.geven.headsoccer.screens.play_screen.objects.Score;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;
import com.geven.headsoccer.screens.play_screen.objects.WorldSize;

public class GameWorld {
    private Score score;
    private TimerHandler timeHandler;

    //Box2d bodies:
    private Ball ball;
    private Ground ground;
    private WorldSize worldSize;
    private Goal goal;
    private SpriteHome spriteHome;

    private InputHandler inputHandler;

    public static boolean goalScore;
    private float timer;

    public GameWorld(){
        score = new Score();
        timeHandler = new TimerHandler();

        ball = new Ball();
        ground = new Ground();
        worldSize = new WorldSize();
        goal = new Goal();
        spriteHome = new SpriteHome();

        inputHandler = new InputHandler();

        goalScore = false;
        timer = 0;
    }

    public void update(float delta){
        score.update();
        timeHandler.update(delta);
        spriteHome.update(delta);


        inputHandler.update(delta);

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
