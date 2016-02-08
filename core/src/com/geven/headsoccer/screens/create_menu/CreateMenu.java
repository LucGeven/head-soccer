package com.geven.headsoccer.screens.create_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.create_menu.gameworld.GameRender;
import com.geven.headsoccer.screens.create_menu.handler.InputHandler;

public class CreateMenu implements Screen {
    private GameRender render;
    private static HeadSoccer headsoccer;

    public CreateMenu(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;                       //extends Game
        Gdx.input.setInputProcessor(new InputHandler());    //Set Input Processor
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
    public static void startChooseCountry(){            //Start ChooseCountry
        headsoccer.setScreen(new ChooseCountry(headsoccer));
    }
}
