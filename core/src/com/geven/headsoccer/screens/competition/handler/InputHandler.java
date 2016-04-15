package com.geven.headsoccer.screens.competition.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.Button;

public class InputHandler implements InputProcessor {

    private HeadSoccer headsoccer;

    public InputHandler(HeadSoccer headsoccer){
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
        float touchX = new Float(screenX) / Gdx.graphics.getWidth() * 1360;
        float touchY = (2040) - (new Float(screenY) / Gdx.graphics.getHeight() * 2040);

        if (Button.isPressed(20,50,300,200,touchX,touchY)){        //Back is pressed
            headsoccer.startActivity.startActivity("com.geven.headsoccer.game.android.AD_ACTIVITY");
        }
        if (Button.isPressed(20 + 300 + 20,50,300,200,touchX,touchY)){     //Delete is pressed
            Preferences prefs = Gdx.app.getPreferences("competition");
            prefs.putString("State","Break");
            prefs.flush();
            headsoccer.startActivity.startActivity("com.geven.headsoccer.game.android.AD_ACTIVITY");
        }
        if (Button.isPressed(1360 - 20 - 300,50,300,200,touchX,touchY)){    //Play is pressed
            PlayHandler.play();
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
