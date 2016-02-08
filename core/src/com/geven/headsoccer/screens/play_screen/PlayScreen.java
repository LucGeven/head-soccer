package com.geven.headsoccer.screens.play_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geven.headsoccer.screens.play_screen.gameworld.GameRender;
import com.geven.headsoccer.screens.play_screen.gameworld.GameWorld;
import com.geven.headsoccer.screens.play_screen.handler.InputHandler;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;

public class PlayScreen implements Screen {

    private GameRender gamerender;
    private GameWorld gameworld;
    private float runTime;


    public PlayScreen(){
        gamerender = new GameRender();
        gameworld = new GameWorld();

        Gdx.input.setInputProcessor(new InputHandler());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gamerender.render(runTime);
        gameworld.update(delta);
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
