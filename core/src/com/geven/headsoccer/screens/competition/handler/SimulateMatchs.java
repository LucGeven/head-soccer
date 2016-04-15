package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.geven.headsoccer.screens.competition.Competition;

import java.util.Random;

public class SimulateMatchs {

    private static Random random;

    private static Preferences prefsCompetition, prefsFixture;
    private static Json json;

    private static String[] roundFixture;

    private static final String[] WIN = {"1 - 0","2 - 0","3 - 0","4 - 0","2 - 1","3 - 1","4 - 1","3 - 2", "4 - 2","5 - 2","5 - 0","6 - 0"};
    private static final String[] DRAW = {"0 - 0","1 - 1","2 - 2","3 - 3","4 - 4"};

    public static void simulate(){

        random = new Random();

        prefsCompetition = Gdx.app.getPreferences("competition");
        prefsFixture = Gdx.app.getPreferences("fixture");
        json = new Json();

        roundFixture = DataLoader.fixture.get(DataLoader.currentRound).clone();

        for (String fixture : roundFixture){
            if (!fixture.contains(DataLoader.myTeam)){

                String team1 = fixture.replaceAll(" v (.*)","");
                String team2 = fixture.replaceAll("(.*) v ","");

                simulateMatch(team1,team2);
            }
        }
        if (prefsFixture.getInteger("CurrentRound") < Integer.valueOf(prefsFixture.getString("TotalRounds"))){
            prefsFixture.putInteger("CurrentRound",prefsFixture.getInteger("CurrentRound") + 1);
        }

        prefsCompetition.flush();
        prefsFixture.flush();
    }
    private static void simulateMatch(String team1, String team2){

        int positionTeam1 = 0, positionTeam2 = 0;
        float procentTeam1, procentTeam2;

        //Initialize the position from team1 and team2 in the competition
        for (int i = 0; i < DataLoader.competition.length; i++){
            if (DataLoader.competition[i].equals(team1)){
                positionTeam1 = i;
            }
            if (DataLoader.competition[i].equals(team2)){
                positionTeam2 = i;
            }
        }

        //Formula: 50 + ((positionOut - PositionHome) * (100 / Competition.length / 2))

        int randomProcent = random.nextInt(110) + 1;

        if (positionTeam2 > positionTeam1){     //team1 is better than team2
            procentTeam1 = 50 + ((positionTeam2 - positionTeam1) * (100 / DataLoader.competition.length / 2));
            procentTeam2 = 100 - procentTeam1;
            if (randomProcent >= 100){
                //Draw
                handleScore("DRAW",getScore("DRAW"), team1, team2, team1);
            }
            else if (randomProcent <= procentTeam1){
                //team1 win
                handleScore("WIN",getScore("WIN"), team1, team2, team1);
            }
            else {
                //team 1 lose;
                handleScore("LOSE",getScore("LOSE"),team1,team2,team1);
            }
        }
        else if (positionTeam1 > positionTeam2){    //Team 2 is better than team1
            procentTeam2 = 50 + ((positionTeam1 - positionTeam2) * (100 / DataLoader.competition.length / 2));
            procentTeam1 = 100 - procentTeam2;
            if (randomProcent >= 100){
                //Draw
                handleScore("DRAW",getScore("DRAW"),team1,team2,team2);
            }
            else if (randomProcent <= procentTeam2){
                //Team 2 win
                handleScore("WIN",getScore("WIN"),team1,team2,team2);
            }
            else {
                //Team 1 win
                handleScore("LOSE",getScore("LOSE"),team1,team2,team2);
            }
        }
    }

    private static void handleScore(String type,String result, String team1, String team2, String isBetter){

        Gdx.app.log("DITISEENTEST",result + " " + team1 + " " + team2 + " " + isBetter);
        if (team1.equals(isBetter)){

            if (type.equals("WIN")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team1));
                competitionHandler.points += 3;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.win += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(team2));
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll("(.*) - ",""));
                competitionHandler.lose += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));
            }
            else if (type.equals("DRAW")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team1));
                competitionHandler.points += 1;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.draw += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team2));
                competitionHandler.points += 1;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.draw += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));
            }
            else if (type.equals("LOSE")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team1));
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.lose += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team2));
                competitionHandler.points += 3;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll("(.*) - ",""));
                competitionHandler.win += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));
            }
        }
        else if (team2.equals(isBetter)){
            if (type.equals("WIN")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team2));
                competitionHandler.points += 3;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.win += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class, prefsCompetition.getString(team1));
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll("(.*) - ",""));
                competitionHandler.lose += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));
            }
            else if (type.equals("DRAW")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team2));
                competitionHandler.points += 1;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.draw += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team1));
                competitionHandler.points += 1;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.draw += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));
            }
            else if (type.equals("LOSE")){
                CompetitionHandler competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team2));
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll(" - (.*)",""));
                competitionHandler.lose += 1;
                prefsCompetition.putString(team2,json.toJson(competitionHandler));

                competitionHandler = json.fromJson(CompetitionHandler.class,prefsCompetition.getString(team1));
                competitionHandler.points += 3;
                competitionHandler.matchs += 1;
                competitionHandler.goal += Integer.valueOf(result.replaceAll("(.*) - ",""));
                competitionHandler.win += 1;
                prefsCompetition.putString(team1,json.toJson(competitionHandler));
            }
        }
    }

    private static String getScore(String result){
        if (result.equals("WIN")){
            int score = random.nextInt(WIN.length - 1);
            return WIN[score];
        }
        else if (result.equals("DRAW")){
            int score = random.nextInt(DRAW.length - 1);
            return DRAW[score];
        }
        else if (result.equals("LOSE")){
            int score = random.nextInt(WIN.length - 1);
            return flip(WIN[score]);
        }
        else {
            return null;
        }
    }
    private static String flip(String result) {
        String[] components = result.split(" - ");
        return components[1] + " - " + components[0];
    }
}
