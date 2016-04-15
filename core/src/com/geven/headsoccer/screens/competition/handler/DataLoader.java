package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.Hashtable;

public class DataLoader {

    public static String[] competition;
    public static Hashtable<String,CompetitionHandler> teams;
    public static String myTeam;
    public static int positionInFinalCompetition, lengthPositionInFinalCompetition;

    private static Json json;

    public static Hashtable<Integer,String[]> fixture;
    public static int totalRounds;
    public static int matchesPerRound;
    public static int currentRound;

    public static void load() {


        json = new Json();

        competition = json.fromJson(String[].class, Gdx.app.getPreferences("competition").getString("competition"));
        myTeam = Gdx.app.getPreferences("competition").getString("MyTeam");
        positionInFinalCompetition = Gdx.app.getPreferences("competition").getInteger("PositionInFinalCompetition");
        lengthPositionInFinalCompetition = Gdx.app.getPreferences("competition").getInteger("LengthPositionInFinalCompetition");

        teams = new Hashtable<String, CompetitionHandler>();
        for (String team : competition){
            CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,Gdx.app.getPreferences("competition").getString(team));
            teams.put(team,competitionHandler);
        }

        totalRounds = Integer.valueOf(Gdx.app.getPreferences("fixture").getString("TotalRounds"));
        matchesPerRound = Integer.valueOf(Gdx.app.getPreferences("fixture").getString("MatchesPerRound"));
        currentRound = Gdx.app.getPreferences("fixture").getInteger("CurrentRound");

        fixture = new Hashtable<Integer, String[]>();
        for (int i = 1; i <= totalRounds; i++){
            fixture.put(i,json.fromJson(String[].class,Gdx.app.getPreferences("fixture").getString(Integer.toString(i))));
        }

    }
}
