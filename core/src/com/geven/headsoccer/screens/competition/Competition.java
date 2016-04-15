package com.geven.headsoccer.screens.competition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.competition.gameworld.GameRender;
import com.geven.headsoccer.screens.competition.handler.DataLoader;
import com.geven.headsoccer.screens.competition.handler.InputHandler;

public class Competition implements Screen {

    public static HeadSoccer headsoccer;

    private GameRender render;

    public Competition(HeadSoccer headsoccer){

        Gdx.input.setInputProcessor(new InputHandler(headsoccer));

        this.headsoccer = headsoccer;
        render = new GameRender();
        DataLoader.load();
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
}
