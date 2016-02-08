package com.geven.headsoccer.screens.play_screen.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.geven.headsoccer.handler.Button;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;

public class InputHandler implements InputProcessor {
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

        if (Button.isPressed(2040-370-20-350,1360-20-150,350,150,touchX,touchY)){
            SpriteHome.shootIsNotPressed();
        }
        if (!Button.isPressed(20, 1360 - 20 - 150, 350, 150,touchX,touchY) && !Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150,touchX,touchY)){
            Ball.hPressed = "NOTHING_PRESSED";
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float touchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
        float touchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;

        if (Button.isPressed(20, 1360 - 20 - 150, 350, 150,touchX,touchY)){
            SpriteHome.leftIsPressed();
        }
        if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150,touchX,touchY)){
            SpriteHome.rightIsPressed();
        }
        if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150,touchX,touchY)){
            SpriteHome.jumpIsPressed();
        }
        if (Button.isPressed(2040-370-20-350,1360-20-150,350,150,touchX,touchY)){
            SpriteHome.shootIsPressed();
        }
        if (!Button.isPressed(2040-370-20-350,1360-20-150,350,150,touchX,touchY)){
            SpriteHome.shootIsNotPressed();
        }
        if (!Button.isPressed(20, 1360 - 20 - 150, 350, 150,touchX,touchY) && !Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150,touchX,touchY)){
            Ball.hPressed = "NOTHING_PRESSED";
        }


        return true;
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
