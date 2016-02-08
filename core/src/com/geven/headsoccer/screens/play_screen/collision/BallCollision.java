package com.geven.headsoccer.screens.play_screen.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.Goal;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.SpriteOut;

public class BallCollision {

    private Rectangle hShoe1,hShoe2,hShoe3,hShoe4,hShoe5,hShoe6,hShoe7,hShoe8;
    private Rectangle hShoe[];
    private Rectangle oShoe1,oShoe2,oShoe3,oShoe4,oShoe5,oShoe6,oShoe7,oShoe8;
    private Rectangle oShoe[];

    private Rectangle goalHome1, goalHome2, goalOut1, goalOut2;

    private Circle ball;
    private Circle hFace1, hFace2;
    private Circle oFace1, oFace2;


    public BallCollision(){
        //Initialize the rectangles and the circles with the begin position:
        hShoe1 = new Rectangle(SpriteHome.getPosition().x + 24,SpriteHome.getPosition().y + 130,64,60);
        hShoe2 = new Rectangle(SpriteHome.getPosition().x + 40,SpriteHome.getPosition().y + 140,64,50);
        hShoe3 = new Rectangle(SpriteHome.getPosition().x + 57.6f,SpriteHome.getPosition().y + 157,68.8f,35);
        hShoe4 = new Rectangle(SpriteHome.getPosition().x + 71.2f,SpriteHome.getPosition().y + 147,68,53);
        hShoe5 = new Rectangle(SpriteHome.getPosition().x + 84.8f,SpriteHome.getPosition().y + 130,73.6f,65);
        hShoe6 = new Rectangle(SpriteHome.getPosition().x + 114.4f,SpriteHome.getPosition().y + 92,59.2f,77);
        hShoe7 = new Rectangle(SpriteHome.getPosition().x + 128,SpriteHome.getPosition().y + 63,38.4f,81);
        hShoe8 = new Rectangle(SpriteHome.getPosition().x + 124,SpriteHome.getPosition().y + 60,40,82);

        hShoe = new Rectangle[] {hShoe1,hShoe2,hShoe3,hShoe4,hShoe5,hShoe6,hShoe7,hShoe8};

        oShoe1 = new Rectangle(SpriteOut.getPosition().x + 112,SpriteOut.getPosition().y + 130,64,60);
        oShoe2 = new Rectangle(SpriteOut.getPosition().x + 96,SpriteOut.getPosition().y + 140,64,50);
        oShoe3 = new Rectangle(SpriteOut.getPosition().x + 73.6f,SpriteOut.getPosition().y + 157,68.8f,35);
        oShoe4 = new Rectangle(SpriteOut.getPosition().x + 60.8f,SpriteOut.getPosition().y + 147,68,53);
        oShoe5 = new Rectangle(SpriteOut.getPosition().x + 41.6f,SpriteOut.getPosition().y + 130,73.6f,65);
        oShoe6 = new Rectangle(SpriteOut.getPosition().x + 26.4f,SpriteOut.getPosition().y + 92,59.2f,77);
        oShoe7 = new Rectangle(SpriteOut.getPosition().x + 33.6f,SpriteOut.getPosition().y + 63,38.4f,81);
        oShoe8 = new Rectangle(SpriteOut.getPosition().x + 36,SpriteOut.getPosition().y + 60,40,82);

        oShoe = new Rectangle[] {oShoe1,oShoe2,oShoe3,oShoe4,oShoe5,oShoe6,oShoe7,oShoe8};

        ball = new Circle(Ball.getPosition().x + Ball.getSize().x / 2, Ball.getPosition().y + Ball.getSize().x / 2, Ball.getSize().x / 2);

        /*
        x = 229 - 250 = -21         21 / 250 * 200 = 16.8
        y position = 0
        width = 229 - 68 = 161     161 / 250 * 200 = 128.8
        height = 161
        x out = 229 / 250 * 200 = 183.2 183.2 - 128.8 = 54.4f
         */
        hFace1 = new Circle(SpriteHome.getPosition().x + 16.8f + (128.8f/2),SpriteHome.getPosition().y + 0 + (128.8f/2),128.8f/2);
        hFace2 = new Circle(SpriteHome.getPosition().x + 16.8f + (128.8f/2),SpriteHome.getPosition().y + (161-128.8f) + (128.8f/2),128.8f/2);

        oFace1 = new Circle(SpriteOut.getPosition().x + 54.4f + (128.8f/2),SpriteOut.getPosition().y + 0 + (128.8f/2),128.8f/2);
        oFace2 = new Circle(SpriteOut.getPosition().x + 54.4f + (128.8f/2),SpriteHome.getPosition().y + (161-128.8f) + (128.8f/2),128.8f/2);
        /*
        x = 0
        y position = 0
        width = 20 - 0 = 20         20 / 300 * 250 = 16.66
        width = 20 / 300 * 200 = 13.33
        height = 600 - 0            600 / 600 * 500 = 500
        height = 400
         */
        goalHome1 = new Rectangle(Goal.getPositionHome().x + 0,Goal.getPositionHome().x + 0,13.33f,400);
        /*
        x = 0
        y position = 0
        width = 300 - 0 = 300       300 / 300 * 250 = 250
        width = 200
        height = 20 - 0 = 20        20 / 600 * 500 = 16.66
        height = 20 / 600 * 400 = 13.33
         */
        goalHome2 = new Rectangle(Goal.getPositionHome().x + 0,Goal.getPositionHome().y + 0,200,13.33f);
        /*
        x = 0 + 300 - 20 = 280      280 / 300 * 250 = 233.33
        x = 280/300 * 200 = 186.66
        y position = 0 + 600 - 600 = 0
        width = 20 - 0 = 20         20 / 300 * 250 = 16.66
        width = 20 / 300 * 200 = 13.33
        height = 600 - 0 = 600      600 / 600 * 500 = 500
        height = 400
         */
        goalOut1 = new Rectangle(Goal.getPositionOut().x + 186.66f,Goal.getPositionOut().y + 0,13.33f,400);
        /*
        x = 0 + 300 - 300 = 0
        y = 0
        width = 300 - 0 = 300       300 / 300 * 250 = 250
        width = 200
        height = 20 - 0 = 20        20 / 600 * 500 = 16.66
        height = 20 / 600 * 400 = 13.33
         */
        goalOut2 = new Rectangle(Goal.getPositionOut().x + 0,Goal.getPositionOut().y + 0,200,13.33f);


    }
    public void update(){

        updatePositions();      //Update the new positions for the rectangles and the circles

        //Check the collisions:

        shoeOverlapsBall();
        ballOverlapsGoal();

        if (hFace1.overlaps(ball)){
            Ball.touch("hFACE_COLLISION");
        }
        if (hFace2.overlaps(ball)){
            Ball.touch("hFACE_COLLISION");
        }
        if (oFace1.overlaps(ball)){
            Ball.touch("oFACE_COLLISION");
        }
        if (oFace2.overlaps(ball)){
            Ball.touch("oFACE_COLLISION");
        }
        if (Ball.getPosition().x + Ball.getSize().x >= 2040){   //if the ball will go out of the screen
            Ball.touch("SCREEN_COLLISION");
        }
        if (Ball.getPosition().x <= 0){                         //if the ball will go out of the screen
            Ball.touch("SCREEN_COLLISION");
        }


    }
    private void updatePositions(){                 //Update the new positions for the rectangles and the circles
        ball.set(Ball.getPosition().x + Ball.getSize().x / 2, Ball.getPosition().y + Ball.getSize().x / 2, Ball.getSize().x / 2);
        hFace1.set(SpriteHome.getPosition().x + 16.8f + (128.8f/2),SpriteHome.getPosition().y + 0 + (128.8f/2),128.8f/2);
        hFace2.set(SpriteHome.getPosition().x + 16.8f + (128.8f / 2), SpriteHome.getPosition().y + (161 - 128.8f) + (128.8f / 2), 128.8f / 2);
        oFace1.set(SpriteOut.getPosition().x + 54.4f + (128.8f/2),SpriteOut.getPosition().y + 0 + (128.8f/2),128.8f/2);
        oFace2.set(SpriteOut.getPosition().x + 54.4f + (128.8f/2),SpriteHome.getPosition().y + (161-128.8f) + (128.8f/2),128.8f/2);
        hShoe1.set(SpriteHome.getPosition().x + 24, SpriteHome.getPosition().y + 130, 64, 60);
        hShoe2.set(SpriteHome.getPosition().x + 40,SpriteHome.getPosition().y + 140,64,50);
        hShoe3.set(SpriteHome.getPosition().x + 57.6f,SpriteHome.getPosition().y + 157,68.8f,35);
        hShoe4.set(SpriteHome.getPosition().x + 71.2f, SpriteHome.getPosition().y + 147, 68, 53);
        hShoe5.set(SpriteHome.getPosition().x + 84.8f, SpriteHome.getPosition().y + 130, 73.6f, 65);
        hShoe6.set(SpriteHome.getPosition().x + 114.4f, SpriteHome.getPosition().y + 92, 59.2f, 77);
        hShoe7.set(SpriteHome.getPosition().x + 128, SpriteHome.getPosition().y + 63, 38.4f, 81);
        hShoe8.set(SpriteHome.getPosition().x + 124, SpriteHome.getPosition().y + 60, 40, 82);
        oShoe1.set(SpriteOut.getPosition().x + 112,SpriteOut.getPosition().y + 130,64,60) ;
        oShoe2.set(SpriteOut.getPosition().x + 96,SpriteOut.getPosition().y + 140,64,50);
        oShoe3.set(SpriteOut.getPosition().x + 73.6f,SpriteOut.getPosition().y + 157,68.8f,35);
        oShoe4.set(SpriteOut.getPosition().x + 60.8f,SpriteOut.getPosition().y + 147,68,53);
        oShoe5.set(SpriteOut.getPosition().x + 41.6f,SpriteOut.getPosition().y + 130,73.6f,65);
        oShoe6.set(SpriteOut.getPosition().x + 26.4f,SpriteOut.getPosition().y + 92,59.2f,77);
        oShoe7.set(SpriteOut.getPosition().x + 33.6f,SpriteOut.getPosition().y + 63,38.4f,81);
        oShoe8.set(SpriteOut.getPosition().x + 36,SpriteOut.getPosition().y + 60,40,82);

        goalHome1.set(Goal.getPositionHome().x + 0,Goal.getPositionHome().x + 0,13.33f,400);
        goalHome2.set(Goal.getPositionHome().x + 0,Goal.getPositionHome().y + 0,200,13.33f);
        goalOut1.set(Goal.getPositionOut().x + 186.66f,Goal.getPositionOut().y + 0,13.33f,400);
        goalOut2.set(Goal.getPositionOut().x + 0,Goal.getPositionOut().y + 0,200,13.33f);
    }
    private void shoeOverlapsBall(){
        if (SpriteHome.getRunTime() == (0.03f * 0)){
            if (Intersector.overlaps(ball,hShoe1)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 1)){
            if (Intersector.overlaps(ball,hShoe2)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 2)){
            if (Intersector.overlaps(ball,hShoe3)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 3)){
            if (Intersector.overlaps(ball,hShoe4)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 4)){
            if (Intersector.overlaps(ball,hShoe5)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 5)){
            if (Intersector.overlaps(ball,hShoe6)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 6)){
            if (Intersector.overlaps(ball,hShoe7)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteHome.getRunTime() == (0.03f * 7)){
            if (Intersector.overlaps(ball,hShoe8)){
                Ball.touch("hSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 0)){
            if (Intersector.overlaps(ball,oShoe1)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 1)){
            if (Intersector.overlaps(ball,oShoe2)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 2)){
            if (Intersector.overlaps(ball,oShoe3)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 3)){
            if (Intersector.overlaps(ball,oShoe4)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 4)){
            if (Intersector.overlaps(ball,oShoe5)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 5)){
            if (Intersector.overlaps(ball,oShoe6)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 6)){
            if (Intersector.overlaps(ball,oShoe7)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
        if (SpriteOut.getRunTime() == (0.03f * 7)){
            if (Intersector.overlaps(ball,oShoe8)){
                Ball.touch("oSHOE_COLLISION");
            }
        }
    }
    private void ballOverlapsGoal(){
        if (Intersector.overlaps(ball,goalHome1)){
            Ball.touch("GOAL_COLLISION");
        }
        if (Intersector.overlaps(ball,goalHome2)){
            Ball.touch("GOAL_COLLISION");
        }
        if (Intersector.overlaps(ball,goalOut1)){
            Ball.touch("GOAL_COLLISION");
        }
        if (Intersector.overlaps(ball,goalOut2)){
            Ball.touch("GOAL_COLLISION");
        }
    }
}
