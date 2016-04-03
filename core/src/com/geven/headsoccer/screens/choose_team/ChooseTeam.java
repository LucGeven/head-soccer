package com.geven.headsoccer.screens.choose_team;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.choose_team.gameworld.GameRender;
import com.geven.headsoccer.screens.choose_team.handler.GestureHandler;
import com.geven.headsoccer.screens.game.GameScreen;

public class ChooseTeam implements Screen {
    private GameRender gamerender;
    private static HeadSoccer headsoccer;

    public ChooseTeam(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;

        Gdx.input.setInputProcessor(new GestureDetector(new GestureHandler()));
        gamerender = new GameRender();

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gamerender.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public static void startGameScreen(){
        headsoccer.screenRotation.rotateScreen("landscape");            //Set screen orientation to landscape mode
        headsoccer.setScreen(new GameScreen(headsoccer));
    }
    public static void startChooseCountry(){            //Start ChooseCountry
        headsoccer.setScreen(new ChooseCountry(headsoccer));
    }

}
