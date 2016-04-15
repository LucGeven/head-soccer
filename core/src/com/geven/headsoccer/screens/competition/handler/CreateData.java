package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.competition.Competition;

import java.util.ArrayList;
import java.util.Hashtable;

public class CreateData {

    private HeadSoccer headsoccer;

    private String[] competition;
    private String myTeam;

    private int positionInFinalCompetition, lengthPositionInFinalCompetition;

    private Hashtable<String,String> teams;
    private Hashtable<String,String> fixture;

    private int currentRound;

    private Json json;

    private Preferences prefsCompetition, prefsFixture;

    public CreateData(HeadSoccer headsoccer,String[] competition, String myTeam, int positionInFinalCompetition, int lengthPositionInFinalCompetition){
        this.headsoccer = headsoccer;
        this.competition = competition;
        this.myTeam = myTeam;
        this.positionInFinalCompetition = positionInFinalCompetition;
        this.lengthPositionInFinalCompetition = lengthPositionInFinalCompetition;
        teams = new Hashtable<String, String>();
        fixture = new Hashtable<String, String>();

        currentRound = 1;

        json = new Json();
        prefsCompetition = Gdx.app.getPreferences("competition");
        prefsFixture = Gdx.app.getPreferences("fixture");
    }
    public void create(){
        for (String team : competition){
            teams.put(team,json.toJson(new CompetitionHandler(team,0,0,0,0,0,0)));
        }

        prefsCompetition.put(teams);
        prefsCompetition.putString("competition", json.toJson(competition));

        prefsCompetition.putString("MyTeam", myTeam);
        prefsCompetition.putInteger("PositionInFinalCompetition", positionInFinalCompetition);
        prefsCompetition.putInteger("LengthPositionInFinalCompetition",lengthPositionInFinalCompetition);
        prefsCompetition.putString("State","Running");
        prefsCompetition.flush();

        generateFixture();
        prefsFixture.put(fixture);
        prefsFixture.putString("TotalRounds", Integer.toString((competition.length - 1) * 2));
        prefsFixture.putString("MatchesPerRound", Integer.toString(competition.length / 2));
        prefsFixture.putInteger("CurrentRound",currentRound);
        prefsFixture.flush();

        headsoccer.setScreen(new Competition(headsoccer));
    }
    private void generateFixture() {

        // Find out how many teams we want fixtures for.
        int teams = competition.length;

        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }


        int iRounds = 0;

        ArrayList<String> matchs = new ArrayList<String>();

        for (String[] test : rounds){
            matchs.clear();
            iRounds++;
            //System.out.println("Round " + iRounds);
            for (String lol : test){
                String[] split = lol.split(" v ");
                //System.out.println(competition[Integer.valueOf(split[0]) - 1] + " v " + competition[Integer.valueOf(split[1]) - 1]);
                matchs.add(competition[Integer.valueOf(split[0]) - 1] + " v " + competition[Integer.valueOf(split[1]) - 1]);
            }
            //fixture.put(iRounds, matchs.toArray(new String[0]));
            fixture.put(Integer.toString(iRounds), json.toJson(matchs.toArray(new String[0])));
        }
        for (String[] test : rounds){
            matchs.clear();
            iRounds++;
            //System.out.println("Round " + iRounds);
            for (String lol : test){
                String[] split = flip(lol).split(" v ");
                //System.out.println(competition[Integer.valueOf(split[0]) - 1] + " v " + competition[Integer.valueOf(split[1]) - 1]);
                matchs.add(competition[Integer.valueOf(split[0]) - 1] + " v " + competition[Integer.valueOf(split[1]) - 1]);
            }
            //fixture.put(iRounds,matchs.toArray(new String[0]));
            fixture.put(Integer.toString(iRounds), json.toJson(matchs.toArray(new String[0])));
        }

    }

    private String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }
}
