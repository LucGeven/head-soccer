package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.PPM;
import com.badlogic.gdx.Gdx;

public class ComputerHandler {

    private boolean check;
    private boolean rightIsPressedBool;

    public ComputerHandler(){
        check = false;
        rightIsPressedBool = false;
    }

    public void update(){

        if (Ball.ball.getPosition().x < (SpriteOut.getoBody1().getPosition().x - (128.8f / 2 / PPM)) && Ball.ball.getPosition().x > (2040 / 2 / PPM)){  //If the ball is on spriteOut side
            SpriteOut.leftIsPressed(true);
            check = true;
        }
        else {
            SpriteOut.leftIsPressed(false);
            check = false;
        }
        if ((Ball.ball.getPosition().x - (35 / PPM)) > (SpriteOut.getoBody1().getPosition().x + (128.8f / 2 / PPM))){
            SpriteOut.rightIsPressed(true);
            rightIsPressedBool = true;
        }
        else {
            SpriteOut.rightIsPressed(false);
            rightIsPressedBool = false;
        }

        //If (the ball is not anymore on his side
        if (SpriteOut.getoBody1().getPosition().x < (((2040 - (250 + 20 + 200)) / PPM) + (54.4f / PPM) + ((128.8f / 2) / PPM)) && !check) {      //The values is the begin position of spriteOut.
            SpriteOut.rightIsPressed(true);
        }
        else {
            if (!rightIsPressedBool) {
                SpriteOut.rightIsPressed(false);
            }
        }

        //If the ball is above SpriteOut head
        if ((Ball.ball.getPosition().x) + (/*100*/150 / PPM) > (SpriteOut.getoBody1().getPosition().x - (128.8f / 2 / PPM)) &&
                Ball.ball.getPosition().x < (SpriteOut.getoBody1().getPosition().x + (128.8f / 2 / PPM))&&
                Ball.ball.getPosition().y < SpriteOut.getoBody1().getPosition().y){
            SpriteOut.jumpIsPressed(true);
        }
        else {
            SpriteOut.jumpIsPressed(false);
        }

        if (Ball.ball.getLinearVelocity().x <= 0){
            if (((Ball.ball.getPosition().x + (35 / PPM)) >= (SpriteOut.getoBody1().getPosition().x - (128.8f / 2 / PPM) - (0/*200*/ / PPM))) && ((Ball.ball.getPosition().x + (35 / PPM)) < (SpriteOut.getoBody1().getPosition().x + (128.8f / 2 / PPM)))){
                SpriteOut.shootIsPressed(true);
            }
            else {
                SpriteOut.shootIsPressed(false);
            }
        }
        else {
            if (((Ball.ball.getPosition().x + (35 / PPM)) >= (SpriteOut.getoBody1().getPosition().x - (128.8f / 2 / PPM) - (200/*100*/ / PPM))) && ((Ball.ball.getPosition().x + (35 / PPM)) < (SpriteOut.getoBody1().getPosition().x + (128.8f / 2 / PPM)))) {
                SpriteOut.shootIsPressed(true);
            } else {
                SpriteOut.shootIsPressed(false);
            }
        }

    }

}
