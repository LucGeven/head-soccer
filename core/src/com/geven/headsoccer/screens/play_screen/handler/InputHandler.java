package com.geven.headsoccer.screens.play_screen.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.geven.headsoccer.handler.Button;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;

public class InputHandler implements InputProcessor {

    private static float touchX, touchY;
    private static float _TouchX, _TouchY;
    private static boolean touchActive = false;
    private static boolean _TouchActive = false;


    public InputHandler(){
        //Empty constructor
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

        if (pointer == 0) {
            touchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            touchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;
            touchActive = true;
        }
        else if (pointer == 1){
            _TouchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            _TouchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;
            _TouchActive = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer == 0) {
            touchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            touchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;

            if (Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.shootIsPressed(false);
            }
            if (Button.isPressed(20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.leftIsPressed(false);
            }
            if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.rightIsPressed(false);
            }
            if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.jumpIsPressed(false);
            }

            touchActive = false;
        }
        if (pointer == 1){
            _TouchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            _TouchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;

            if (Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.shootIsPressed(false);
            }
            if (Button.isPressed(20, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.leftIsPressed(false);
            }
            if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.rightIsPressed(false);
            }
            if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.jumpIsPressed(false);
            }
            _TouchActive = false;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (pointer == 0) {
            touchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            touchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;
        }
        else if (pointer == 1){
            _TouchX = new Float(screenX) / Gdx.graphics.getWidth() * 2040;
            _TouchY = new Float(screenY) / Gdx.graphics.getHeight() * 1360;
        }

        /*if (Button.isPressed(20, 1360 - 20 - 150, 350, 150,touchX,touchY)){
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
        }*/

        return true;
    }
    public void update (float delta){

        if (touchActive) {
            if (Button.isPressed(20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.leftIsPressed(true);
            }
            else {
                SpriteHome.leftIsPressed(false);
            }
            if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.rightIsPressed(true);
            }
            else {
                SpriteHome.rightIsPressed(false);
            }
            if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.jumpIsPressed(true);
            }
            else {
                SpriteHome.jumpIsPressed(false);
            }
            if (Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.shootIsPressed(true);
            }
            else {
                SpriteHome.shootIsPressed(false);
            }
            /*if (!Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                SpriteHome.shootIsPressed(false);
            }*/

        }
        if (_TouchActive){
            if (Button.isPressed(20, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.leftIsPressed(true);
            }
            else {
                if (Button.isPressed(20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                    SpriteHome.leftIsPressed(true);
                }
                else {
                    SpriteHome.leftIsPressed(false);
                }
            }
            if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.rightIsPressed(true);
            }
            else {
                if (Button.isPressed(370 + 20, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                    SpriteHome.rightIsPressed(true);
                }
                else {
                    SpriteHome.rightIsPressed(false);
                }
            }
            if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.jumpIsPressed(true);
            }
            else {
                if (Button.isPressed(2040 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                    SpriteHome.jumpIsPressed(true);
                }
                else {
                    SpriteHome.jumpIsPressed(false);
                }
            }
            if (Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.shootIsPressed(true);
            }
            else {
                if (Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, touchX, touchY)) {
                    SpriteHome.shootIsPressed(true);
                }
                else {
                    SpriteHome.shootIsPressed(false);
                }
            }
            /*if (!Button.isPressed(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150, _TouchX, _TouchY)) {
                SpriteHome.shootIsPressed(false);
            }*/

        }
        if (!touchActive && !_TouchActive){
                SpriteHome.shootIsPressed(false);
                SpriteHome.leftIsPressed(false);
                SpriteHome.rightIsPressed(false);
                SpriteHome.jumpIsPressed(false);
        }
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
