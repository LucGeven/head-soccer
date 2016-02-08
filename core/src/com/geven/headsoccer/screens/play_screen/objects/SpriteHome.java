package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SpriteHome {
    private static Vector2 position;
    private static Vector2 size;
    private static int velocityWalk;
    private static int velocityJump;
    private static int jumpDistance;
    private static float runTime;
    private static boolean shootIsPressed;
    private static boolean jump, fall;

    public SpriteHome(){
        position = new Vector2(250 + 20, 1360 - 200 - 190);
        size = new Vector2(200,200);
        velocityWalk = 4;
        velocityJump = 7;
        jumpDistance = 200;
        runTime = 0;
        jump = fall = false;

    }
    public void update(float delta){

        //Code to handle the shoe position:
        if (shootIsPressed){
            if (runTime >= 0.21){
                runTime = (0.03f * 8) - 0.03f ;
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
    public static void leftIsPressed(){
        position.x -= velocityWalk;
        Ball.hPressed = "LEFT_PRESSED";
    }
    public static void rightIsPressed(){
        position.x += velocityWalk;
        Ball.hPressed = "RIGHT_PRESSED";
    }
    public static void shootIsPressed(){
        shootIsPressed = true;
    }
    public static void shootIsNotPressed(){
        shootIsPressed = false;
    }
    public static void jumpIsPressed(){
        if (jump || fall){

        }
        else {
            jump = true;
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
