package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.math.Vector2;

public class SpriteOut {
    private static Vector2 position;
    private static Vector2 size;
    private static int velocityWalk;
    private static int velocityJump;
    private static int jumpDistance;
    private static float runTime;
    private boolean jump, jumpIsRunning, fall;
    private boolean shoot, shootIsRunning;

    public SpriteOut(){
        position = new Vector2(2040 - (250 + 20) - 200, 1360 - 200 - 190);
        size = new Vector2(200,200);
        velocityWalk = 4;
        velocityJump = 7;
        jumpDistance = 200;
        runTime = 0;
        jump = jumpIsRunning = fall = false;
        shoot = shootIsRunning = false;
    }

    public void update(float delta){
        if (Ball.getPosition().x + Ball.getSize().x < position.x + 112){
            position.x -=velocityWalk;
        }
        if (Ball.getPosition().x > position.x + 155.2f){
            position.x += velocityWalk;
        }
        /*if (Ball.getPosition().x >= position.x && Ball.getPosition().x <= position.x + size.x){
            if (!jumpIsRunning) {
                jump = true;
            }
        }*/
        if (position.x - (Ball.getPosition().x + Ball.getSize().x) <= -20){
            if (!shootIsRunning) {
                shoot = true;
            }
        }
        if (shoot){

            if (runTime >= 0.21){
                runTime = (0.03f * 8) - 0.03f;
                shoot = false;
                shootIsRunning = false;
            }
            else {
                runTime += delta;
            }
        }
        else {
            if (runTime <= 0){
                runTime = 0;
            }
            else {
                runTime -= delta;
            }
        }
        if (jump){
            if (1360 - 200 - 190 - jumpDistance < position.y - velocityJump ){
                position.y -= velocityJump;
            }
            else {
                jump = false;
                jumpIsRunning = false;
                fall = true;
            }
        }
        else if (fall){
            if (position.y < 1360 - 200 - 190 ){
                position.y += velocityJump;
            }
            else {
                jump = false;
                fall = false;
            }
        }

    }

    public static Vector2 getPosition(){
        return position;
    }
    public static void setPositionX(float value){
        position.x = value;
    }
    public static void setPositionY(float value){
        position.y = value;
    }
    public static Vector2 getSize(){
        return size;
    }
    public static float getRunTime(){
        return runTime;
    }
}
