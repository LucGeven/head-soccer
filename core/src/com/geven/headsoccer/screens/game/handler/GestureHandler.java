package com.geven.headsoccer.screens.game.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.Button;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class GestureHandler implements InputProcessor{

    private HeadSoccer headsoccer;

    public GestureHandler(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;
    }

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        float touchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
        float touchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;

        if (Button.isPressed(120,1360-250,225,125,touchX,touchY)){          //If button back is pressed:
            VariablesHandler.myCountry = "";
            VariablesHandler.myTeam = "";
            VariablesHandler.computerTeam = "";
            headsoccer.screenRotation.rotateScreen("portrait");
            headsoccer.setScreen(new ChooseCountry(headsoccer));
        }
        else if (Button.isPressed(2040 - 120 - 225, 1360 - 250, 225, 125,touchX,touchY)){
            headsoccer.setScreen(new PlayScreen(headsoccer));
        }

        return true;
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
