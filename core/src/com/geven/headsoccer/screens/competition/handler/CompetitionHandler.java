package com.geven.headsoccer.screens.competition.handler;

public class CompetitionHandler {

    public String team;
    public int matchs, points, win, draw, lose, goal;

    public CompetitionHandler(String team,int matchs,int points,int win, int draw, int lose,int goal){
        this.matchs = matchs;
        this.team = team;
        this.points = points;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.goal = goal;
    }
    public CompetitionHandler(){
        //Empty constructor for serialize json
    }
}
