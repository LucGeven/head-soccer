package com.geven.headsoccer.screens.create_menu.handler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.create_menu.CreateMenu;
import com.geven.headsoccer.screens.create_menu.gameworld.GameRender;

public class InputHandler implements InputProcessor{

    private float x,y;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        x = (new Float(Integer.toString(screenX))/Gdx.graphics.getWidth()) * 1360;
        y = (new Float(Integer.toString(screenY))/Gdx.graphics.getHeight()) * 2040;

        //Button Choose Country:
        if (x >= GameRender.position_CHOOSECOUNTRY.x && x <= GameRender.position_CHOOSECOUNTRY.x + GameRender.surface_CHOOSECOUNTRY.x
                && y >= GameRender.position_CHOOSECOUNTRY.y && y <= GameRender.position_CHOOSECOUNTRY.y + GameRender.surface_CHOOSECOUNTRY.y){
            //Button clicked:
            CreateMenu.startChooseCountry();        //Open ChooseCountry
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
