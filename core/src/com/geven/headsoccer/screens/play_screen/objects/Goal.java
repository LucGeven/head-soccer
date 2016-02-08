package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.math.Vector2;

public class Goal {

    private static Vector2 positionHome, positionOut;
    private static Vector2 sizeHome, sizeOut;

    public Goal(){
        positionHome = new Vector2(0, 1360 - 400 - 190);            //190 came from the button x position + the marge: 20;
        sizeHome = new Vector2(200, 400);

        positionOut = new Vector2(2040 - 200, 1360 - 400 - 190);
        sizeOut = new Vector2(200, 400);
    }
    public static Vector2 getPositionHome(){
        return positionHome;
    }
    public static Vector2 getSizeHome(){
        return sizeHome;
    }
    public static Vector2 getPositionOut(){
        return positionOut;
    }
    public static Vector2 getSizeOut(){
        return sizeOut;
    }
}
