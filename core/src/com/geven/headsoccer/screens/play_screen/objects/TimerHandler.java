package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;

public class TimerHandler {

    private float timer;

    private static int minute;
    private static int tientallen;
    private static int second;

    public TimerHandler(){
        //Initialize how long the match duurt
        minute = 2;
        tientallen = 0;
        second = 0;

        timer = 0;

    }

    public void update(float delta){
        timer += delta;

        //Check if it is 1 second
        if (timer >= 1) {
            second--;
            timer -= 1;
        }
        //Check if time = 0:00
        if (minute <= 0 && tientallen <= 0 && second <= 0){
            minute = tientallen = second = 0;
        }
        else {
            if (second < 0){
                tientallen--;
                second = 9;
            }

            if (tientallen < 0){
                minute--;
                tientallen = 5;
            }

        }


    }
    public static int getMinute(){
        return minute;
    }
    public static int getTientallen(){
        return tientallen;
    }
    public static int getSecond(){
        return second;
    }
}
