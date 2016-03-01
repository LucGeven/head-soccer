package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.math.Vector2;
import com.geven.headsoccer.screens.play_screen.gameworld.GameWorld;

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

    }
    public void setPosition(){
        /*
        Ball.setVelocity(new Vector2(0,0));                                 //Set velocity from ball to 0
        Ball.setPosition(new Vector2(2040 / 2 - (100 / 2), 1360 - 190 - 100));   //Set the position from the bal in the middle of the screen
        SpriteHome.setPositionX(250 + 20);                                          //Set the position from the sprite
        SpriteHome.setPositionY(1360 - 200 - 190);                                  //Set the position from the sprite
        */
    }

    public static Vector2 getScore(){
        return score;
    }
}
