package com.geven.headsoccer.screens.choose_country.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.geven.headsoccer.handler.SetCompetition;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.choose_country.gameworld.GameRender;

public class GestureHandler implements GestureDetector.GestureListener {

    private boolean loop;
    private float screenX, screenY;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        screenX = (x / Gdx.graphics.getWidth())*1360;       //Scale screenX
        screenY = (y / Gdx.graphics.getHeight())*2040;      //Scale screenY

        for (int i = 0; i <= VariablesHandler.countries.length -1; i++){
            if (screenY >= GameRender.buttonsPositionY[i] &&
                    screenY <= GameRender.buttonsPositionY[i] + GameRender.surfaceRectangle.y){
                GameRender.draggedCountry = VariablesHandler.countries[i];
                break;          //Go out loop
            }
        }
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        screenX = (x / Gdx.graphics.getWidth())*1360;       //Scale screenX
        screenY = (y / Gdx.graphics.getHeight())*2040;      //Scale screenY

        for (int i = 0; i <= VariablesHandler.countries.length -1; i++){
            if (screenY >= GameRender.buttonsPositionY[i] &&
                    screenY <= GameRender.buttonsPositionY[i] + GameRender.surfaceRectangle.y){
                VariablesHandler.myCountry = VariablesHandler.countries[i];         //Set your chosen country
                SetCompetition.setCompetition();                                    //Set the current competition
                ChooseCountry.startChooseTeam();                                 //Start Choose team
                break;          //Go out loop
            }
        }
        Gdx.app.log("TEST",VariablesHandler.myCountry);

        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //else if (GameRender.buttonsPositionY[VariablesHandler.countries.length-1] + GameRender.surfaceRectangle.y)

        screenX = (x / Gdx.graphics.getWidth())*1360;       //Scale screenX
        screenY = (y / Gdx.graphics.getHeight())*2040;      //Scale screenY

        if (deltaY > 0 && GameRender.buttonsPositionY[0] >= (0 + 200 + 50)){
            deltaY = 0;
        }
        else if (deltaY < 0 && GameRender.buttonsPositionY[GameRender.buttonsPositionY.length-1] + GameRender.surfaceRectangle.y <= 2040 ){
            deltaY = 0;
        }

        Gdx.app.log("DELTA",Float.toString(deltaY));

        GameRender.deltaY = deltaY;                                     //Set delta y how much it will be scroll
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        GameRender.deltaY = 0;                                          //Set that scroll must 'shut down'
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
