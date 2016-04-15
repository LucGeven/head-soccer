package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.competition.Competition;
import com.geven.headsoccer.screens.competition.handler.MatchHandler;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

import javafx.print.PageLayout;

public class TimerHandler {

    private float timer;

    private static int minute;
    private static int tientallen;
    private static int second;

    public TimerHandler(){
        //Initialize how long the match is
        minute = 0;
        tientallen = 1;
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
        if (minute <= 0 && tientallen <= 0 && second <= -1){
            minute = tientallen = second = -1;

            if (HeadSoccer.situation.equals("COMPETITION")) {
                MatchHandler matchHandler = new MatchHandler(PlayScreen.headsoccer, VariablesHandler.computerTeam);
                matchHandler.handle();
            }
            //TODO if situation.equals "match"
            else {
                PlayScreen.headsoccer.startActivity.startActivity("com.geven.headsoccer.game.android.AD_ACTIVITY");
            }

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
