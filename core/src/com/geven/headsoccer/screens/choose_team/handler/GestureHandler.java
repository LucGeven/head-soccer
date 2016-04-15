package com.geven.headsoccer.screens.choose_team.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.choose_team.ChooseTeam;
import com.geven.headsoccer.screens.choose_team.gameworld.GameRender;
import com.geven.headsoccer.screens.competition.handler.CreateData;
import com.geven.headsoccer.screens.create_menu.CreateMenu;

public class GestureHandler implements GestureDetector.GestureListener {

    private float screenX, screenY;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        screenX = (x / Gdx.graphics.getWidth()) * 1360;       //Scale screenX
        screenY = (y / Gdx.graphics.getHeight()) * 2040;      //Scale screenY

        for (int i = 0; i <= VariablesHandler.finalCompetition.length - 1; i++) {

            //Check if you touch the button:
            if (screenY >= GameRender.buttonsPositionY[i] &&
                    screenY <= GameRender.buttonsPositionY[i] + GameRender.surfaceRectangle.y) {
                GameRender.draggedTeam = VariablesHandler.finalCompetition[i];
                break;
            }

        }
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        screenX = (x / Gdx.graphics.getWidth())*1360;       //Scale screenX
        screenY = (y / Gdx.graphics.getHeight())*2040;      //Scale screenY

        for (int i = 0; i <= VariablesHandler.finalCompetition.length -1; i++){

            //Check if you touch the button:
            if (screenY >= GameRender.buttonsPositionY[i] &&
                    screenY <= GameRender.buttonsPositionY[i] + GameRender.surfaceRectangle.y) {

                //Check if myTeam not is used:
                if (HeadSoccer.situation.equals("COMPETITION")) {
                    CreateData createData = new CreateData(ChooseTeam.headsoccer,VariablesHandler.finalCompetition,VariablesHandler.finalCompetition[i],i,VariablesHandler.finalCompetition.length - 1);
                    createData.create();
                } else {
                    if (VariablesHandler.myTeam.equals("")) {
                        VariablesHandler.myTeam = VariablesHandler.finalCompetition[i];

                        VariablesHandler.positionInFinalCompetition = i;
                        VariablesHandler.lenghtPositionInFinalCompetition = VariablesHandler.finalCompetition.length - 1;
                        //CreateMenu.startChooseCountry();        //Start again choosecountry
                        ChooseTeam.startChooseCountry();
                    }
                    //If myTeam is used
                    else {
                        VariablesHandler.computerTeam = VariablesHandler.finalCompetition[i];

                        VariablesHandler.positionInFinalComputerCompetition = i;
                        VariablesHandler.lengthPositionInFinalComputerCompetition = VariablesHandler.finalCompetition.length - 1;


                        Gdx.app.log("TEAM", VariablesHandler.myTeam);
                        Gdx.app.log("TEAM", VariablesHandler.computerTeam);

                        ChooseTeam.startGameScreen();           //Start the GameScreen
                    }


                    break;          //Go out loop
                }
            }
        }
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

        if (deltaY > 0 && GameRender.buttonsPositionY[0] >= (0 + 200 + 50) ){
            deltaY = 0;
        }
        else if (deltaY < 0 && GameRender.buttonsPositionY[GameRender.buttonsPositionY.length-1] + GameRender.surfaceRectangle.y <= 2040 ){
            deltaY = 0;
        }

        GameRender.deltaY = deltaY;     //Set deltaY for scroll
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        GameRender.deltaY = 0;          //Stop scroll
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
