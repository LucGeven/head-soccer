package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.competition.Competition;
import com.geven.headsoccer.screens.game.GameScreen;

public class PlayHandler {

    private static String[] roundFixture;

    private static VariablesHandler variablesHandler;

    public static void play(){
        roundFixture = DataLoader.fixture.get(DataLoader.currentRound).clone();

        variablesHandler = new VariablesHandler();

        for (String fixture : roundFixture){
            if (fixture.contains(DataLoader.myTeam)){
                VariablesHandler.myTeam = DataLoader.myTeam;
                fixture = fixture.replace(DataLoader.myTeam,"");
                fixture = fixture.replace(" v ","");
                VariablesHandler.computerTeam = fixture;
            }
        }

        VariablesHandler.positionInFinalCompetition = DataLoader.positionInFinalCompetition;
        VariablesHandler.lenghtPositionInFinalCompetition = DataLoader.lengthPositionInFinalCompetition;

        for (int i = 0; i <= DataLoader.competition.length - 1;i++){                //Check position from computerteam in the competition
            if (DataLoader.competition[i].equals(VariablesHandler.computerTeam)){
                VariablesHandler.positionInFinalComputerCompetition = i;
                VariablesHandler.lengthPositionInFinalComputerCompetition = DataLoader.lengthPositionInFinalCompetition;
            }
        }

        Competition.headsoccer.screenRotation.rotateScreen("landscape");
        Competition.headsoccer.setScreen(new GameScreen(Competition.headsoccer));
    }
}
