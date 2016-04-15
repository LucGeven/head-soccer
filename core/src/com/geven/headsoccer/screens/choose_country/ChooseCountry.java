package com.geven.headsoccer.screens.choose_country;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.choose_country.gameworld.GameRender;
import com.geven.headsoccer.screens.choose_country.handler.GestureHandler;
import com.geven.headsoccer.screens.choose_team.ChooseTeam;

public class ChooseCountry implements Screen {
    private GameRender render;
    private static HeadSoccer headsoccer;

    public ChooseCountry(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;

        Gdx.input.setInputProcessor(new GestureDetector(new GestureHandler()));
        render = new GameRender();


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        render.render();

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
    public static void startChooseTeam(){            //Start ChooseTeam
        headsoccer.setScreen(new ChooseTeam(headsoccer));
    }
}
