package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    private static Vector2 position;
    private static Vector2 size;
    private static Vector2 velocity;
    public static String hPressed, oPressed;

    private static float hTime, oTime;

    public Ball(){
        position = new Vector2(2040/2 - (100/2),1360 - 190 - 100);
        size = new Vector2(80,80);
        velocity = new Vector2(0,0);
        hPressed = "";
        oPressed = "";
        hTime = oTime = 0;
    }

    public void update(float delta){
        //Check how long is the sprite position 8
        if (SpriteHome.getRunTime() <= 0.24 && SpriteHome.getRunTime() >= 0.18){
            hTime += delta;
        }
        else {
            hTime = 0;
        }
        if (SpriteOut.getRunTime() <= 0.24 && SpriteOut.getRunTime() >= 0.18){
            oTime += delta;
        }
        else {              //if the sprite position isn't 8 than the time will be 0
            oTime = 0;
        }
        position.x += velocity.x;
        position.y += velocity.y;
        gravityUpdate();

    }
    public static void touch(String touchCollision){

        if (touchCollision == "hFACE_COLLISION"){    //if the face overlaps the ball:
            //Check if the SpriteHome sit on the ball, and handle that exception:
            if (SpriteHome.getPosition().y < 1360 - 190 - 100){
                if (SpriteHome.getPosition().x < position.x){
                    if (velocity.x > 10){
                        velocity.x = -velocity.x;
                    }
                    else if (hPressed == "LEFT_PRESSED" || hPressed == "RIGHT_PRESSED") {
                        velocity.x = 10;
                    }
                    else {
                        velocity.x =-velocity.x;
                    }
                }
                else {
                    if (velocity.x < -10){
                        velocity.x = -velocity.x;
                    }
                    else if (hPressed == "LEFT_PRESSED" || hPressed == "RIGHT_PRESSED") {
                        velocity.x = -10;
                    }
                    else {
                        velocity.x =-velocity.x;
                    }
                }
                //Als er problemen zijn dit weglaten:
                velocity.y = -velocity.y;
            }

            //Check which side it must going:
            else {

                if (velocity.x < 0) {
                    if (velocity.x > -10) {
                        velocity.x = -10;
                        velocity.y = -velocity.y;
                    } else {
                        velocity.x = -velocity.x;
                        velocity.y = -velocity.y;
                    }
                } else if (velocity.x > 0) {
                    if (velocity.x < 10) {
                        velocity.x = 10;
                        velocity.y = -velocity.y;
                    } else {
                        velocity.x = -velocity.x;
                        velocity.y = -velocity.y;
                    }
                } else {
                /*if (SpriteHome.getPosition().y + SpriteHome.getSize().y < position.y) {
                    velocity.x = -30;
                }*/
                    if (position.x < SpriteHome.getPosition().x) {
                        velocity.x = -10;               //Going to the left
                    } else if (position.x > SpriteHome.getPosition().x) {
                        velocity.x = 10;                //Going to the right
                    } else {
                        velocity.x = -30;
                    }
                }
            }
        }
        else if (touchCollision == "hSHOE_COLLISION"){   //if the shoe overlaps the ball:
            /*if (SpriteHome.getRunTime() >= 0 && SpriteHome.getRunTime() <= 0.03){
                velocity.x = 10;
            }*/
            /*else*/ if (SpriteHome.getRunTime() <= 0.21 && SpriteHome.getRunTime() >= 0.18 && hTime >= 0.1/*Check if the time is so long that that isn't come from a shoot!*/){
                velocity.x = 10;
            }
            else if (SpriteHome.getRunTime() >= 0.03 && SpriteHome.getRunTime() < 0.21 /*&& time < 2*/){
                velocity.x = 30;
                velocity.y = -20;
            }
            else {
                //velocity.x = 10;
                if (SpriteHome.getPosition().y < 1360 - 190 - 100){
                    if (SpriteHome.getPosition().x < position.x){
                        if (velocity.x > 10){
                            velocity.x = -velocity.x;
                        }
                        else {
                            velocity.x = 10;
                        }
                    }
                    else {
                        if (velocity.x < -10){
                            velocity.x = -velocity.x;
                        }
                        else {
                            velocity.x = -10;
                        }
                    }
                }
            }
        }




        if (touchCollision == "oFACE_COLLISION"){    //if the face overlaps the ball:
            //Check if the SpriteHome sit on the ball, and handle that exception:
            if (SpriteOut.getPosition().y < 1360 - 190 - 100){
                if (SpriteOut.getPosition().x < position.x){
                    if (velocity.x > 10){
                        velocity.x = -velocity.x;
                    }
                    else if (oPressed == "LEFT_PRESSED" || oPressed == "RIGHT_PRESSED") {
                        velocity.x = 10;
                    }
                    else {
                        velocity.x =-velocity.x;
                    }
                }
                else {
                    if (velocity.x < -10){
                        velocity.x = -velocity.x;
                    }
                    else if (oPressed == "LEFT_PRESSED" || oPressed == "RIGHT_PRESSED") {
                        velocity.x = -10;
                    }
                    else {
                        velocity.x =-velocity.x;
                    }
                }
                //Als er problemen zijn dit weglaten:
                velocity.y = -velocity.y;
            }

            //Check which side it must going:
            else {

                if (velocity.x < 0) {
                    if (velocity.x > -10) {
                        velocity.x = -10;
                        velocity.y = -velocity.y;
                    } else {
                        velocity.x = -velocity.x;
                        velocity.y = -velocity.y;
                    }
                } else if (velocity.x > 0) {
                    if (velocity.x < 10) {
                        velocity.x = 10;
                        velocity.y = -velocity.y;
                    } else {
                        velocity.x = -velocity.x;
                        velocity.y = -velocity.y;
                    }
                } else {
                /*if (SpriteHome.getPosition().y + SpriteHome.getSize().y < position.y) {
                    velocity.x = -30;
                }*/ 
                    if (position.x < SpriteOut.getPosition().x) {
                        velocity.x = -10;               //Going to the left
                    } else if (position.x > SpriteOut.getPosition().x) {
                        velocity.x = 10;                //Going to the right
                    } else {
                        velocity.x = -30;
                    }
                }
            }
        }
        else if (touchCollision == "oSHOE_COLLISION"){   //if the shoe overlaps the ball:
            /*if (SpriteHome.getRunTime() >= 0 && SpriteHome.getRunTime() <= 0.03){
                velocity.x = 10;
            }*/
            /*else*/ if (SpriteOut.getRunTime() <= 0.21 && SpriteOut.getRunTime() >= 0.18 && oTime >= 0.1/*Check if the time is so long that that isn't come from a shoot!*/){
                velocity.x = 10;
            }
            else if (SpriteOut.getRunTime() >= 0.03 && SpriteOut.getRunTime() < 0.21 /*&& time < 2*/){
                velocity.x = 30;
                velocity.y = -20;
            }
            else {
                //velocity.x = 10;
                if (SpriteOut.getPosition().y < 1360 - 190 - 100){
                    if (SpriteOut.getPosition().x < position.x){
                        if (velocity.x > 10){
                            velocity.x = -velocity.x;
                        }
                        else {
                            velocity.x = 10;
                        }
                    }
                    else {
                        if (velocity.x < -10){
                            velocity.x = -velocity.x;
                        }
                        else {
                            velocity.x = -10;
                        }
                    }
                }
            }
        }
        else if (touchCollision == "GOAL_COLLISION"){   //if the ball overlaps the goal:
            velocity.x = -velocity.x;
            velocity.y = -velocity.y;
        }
        else if (touchCollision == "SCREEN_COLLISION"){ //if the ball will go out of the screen
            velocity.x = -velocity.x;
            velocity.y = -velocity.y;
        }
    }
    private void gravityUpdate(){
        if (velocity.x > 0){
            velocity.x -= 0.2;
            if (velocity.x < 0){
                velocity.x = 0;
            }
        }
        else if (velocity.x < 0) {
            velocity.x += 0.2;
            if (velocity.x > 0){
                velocity.x = 0;
            }
        }
        else {
            velocity.x = 0;
        }
        if (position.y >= 1360 - 190 - 100){
            position.y = 1360 - 190 - 100;
            velocity.y = 0;

        }
        else {
          velocity.y +=0.5;
        }

    }

    public static Vector2 getPosition(){
        return position;
    }
    public static Vector2 getSize(){
        return size;
    }

    public static void setPosition(Vector2 position) {
        Ball.position = position;
    }

    public static void setVelocity(Vector2 velocity) {
        Ball.velocity = velocity;
    }
}
