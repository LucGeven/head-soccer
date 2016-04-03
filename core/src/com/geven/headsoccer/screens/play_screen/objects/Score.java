package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.math.Vector2;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.screens.play_screen.gameworld.GameWorld;

import static com.geven.headsoccer.handler.Vars.PPM;

public class Score {
    private static Vector2 score;

    public Score(){
        score = new Vector2(0,0);

    }

    public void update(){
        /*if (Ball.getPosition().x + Ball.getSize().x < Goal.getPositionHome().x + Goal.getSizeHome().x &&
                Ball.getPosition().y > Goal.getPositionHome().y && GameWorld.goalScore == false){
            score.x++;
            GameWorld.goalScore = true;     //Wait 2 second and then to setPosition
        }
        if (Ball.getPosition().x > Goal.getPositionOut().x &&
                Ball.getPosition().y > Goal.getPositionOut().y && GameWorld.goalScore == false){
            score.y++;
            GameWorld.goalScore = true;     //Wait 2 second and then to setPosition
        }*/
        if ((Ball.ball.getPosition().x + (35 / PPM)) < (Goal.getPositionHome().x + Goal.getSizeHome().x) &&
                Ball.ball.getPosition().y > Goal.getPositionHome().y && !GameWorld.goalScore ){
            score.y++;
            AssetLoader.boo.play();
            GameWorld.goalScore = true;
        }
        if ((Ball.ball.getPosition().x - (35 / PPM)) > (Goal.getPositionOut().x) &&
                Ball.ball.getPosition().y > Goal.getPositionOut().y && !GameWorld.goalScore){
            score.x++;
            AssetLoader.goal.play();
            GameWorld.goalScore = true;
        }

    }
    public void setPosition(){
        //Ball.setVelocity(new Vector2(0, 0));                                 //Set velocity from ball to 0
        //Ball.setPosition(new Vector2(2040 / 2 - (100 / 2), 1360 - 190 - 100));   //Set the position from the bal in the middle of the screen
        //SpriteHome.setPositionX(250 + 20);                                          //Set the position from the sprite
        //SpriteHome.setPositionY(1360 - 200 - 190);                                  //Set the position from the sprite

        Ball.ball.setLinearVelocity(0, 0);
        Ball.ball.setTransform(2040 / 2 / PPM, ((1360 - 190 - 35) / PPM) - (500 / PPM), 0);
        SpriteHome.gethBody1().setTransform(((250 + 20) / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((128.8f / 2) / PPM), 0);
        SpriteHome.gethBody2().setTransform(((250 + 20) / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM), 0);
        SpriteOut.getoBody1().setTransform(((2040 - (250 + 20 + 200)) / PPM) + (54.4f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((128.8f / 2) / PPM), 0);
        SpriteOut.getoBody2().setTransform(((2040 - (250 + 20 + 200)) / PPM) + (54.4f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM), 0);

    }

    public static Vector2 getScore(){
        return score;
    }
}
