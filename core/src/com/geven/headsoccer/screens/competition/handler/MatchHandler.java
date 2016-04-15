package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.competition.Competition;
import com.geven.headsoccer.screens.play_screen.objects.Score;

public class MatchHandler {

    private HeadSoccer headsoccer;
    private String computerTeam;

    private CompetitionHandler competitionHandler;

    private Json json;

    private Preferences prefsCompetition, prefsFixture;

    public MatchHandler(HeadSoccer headsoccer, String computerTeam){
        this.headsoccer = headsoccer;
        this.computerTeam = computerTeam;

        json = new Json();

        prefsCompetition = Gdx.app.getPreferences("competition");
        prefsFixture = Gdx.app.getPreferences("fixture");
    }
    public void handle(){

        if (Score.getScore().x > Score.getScore().y){   //Home win
            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(DataLoader.myTeam));
            competitionHandler.points += 3;
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().x;
            competitionHandler.win += 1;

            prefsCompetition.putString(DataLoader.myTeam,json.toJson(competitionHandler));

            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(computerTeam));
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().y;
            competitionHandler.lose += 1;

            prefsCompetition.putString(computerTeam,json.toJson(competitionHandler));
        }
        else if (Score.getScore().x == Score.getScore().y){ //Draw
            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(DataLoader.myTeam));
            competitionHandler.points += 1;
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().x;
            competitionHandler.draw += 1;
            prefsCompetition.putString(DataLoader.myTeam,json.toJson(competitionHandler));

            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(computerTeam));
            competitionHandler.points += 1;
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().y;
            competitionHandler.draw += 1;
            prefsCompetition.putString(computerTeam,json.toJson(competitionHandler));
        }
        else if (Score.getScore().x < Score.getScore().y){  //Out win
            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(DataLoader.myTeam));
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().x;
            competitionHandler.lose += 1;
            prefsCompetition.putString(DataLoader.myTeam,json.toJson(competitionHandler));

            competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(computerTeam));
            competitionHandler.points += 3;
            competitionHandler.matchs += 1;
            competitionHandler.goal += Score.getScore().y;
            competitionHandler.win += 1;
            prefsCompetition.putString(computerTeam,json.toJson(competitionHandler));
        }

        prefsCompetition.flush();
        prefsFixture.flush();


        DataLoader.load();

        SimulateMatchs.simulate();

        headsoccer.screenRotation.rotateScreen("portrait");
        headsoccer.setScreen(new Competition(headsoccer));
    }
}
