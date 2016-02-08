package com.geven.headsoccer.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.game.gameworld.GameRender;
import com.geven.headsoccer.screens.game.handler.GestureHandler;

public class GameScreen implements Screen {                     //This screen is in landscape mode

    private GameRender gameRender;
    private HeadSoccer headsoccer;

    public GameScreen(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;
        gameRender = new GameRender();
        Gdx.input.setInputProcessor(new GestureHandler(this.headsoccer));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameRender.render();
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
