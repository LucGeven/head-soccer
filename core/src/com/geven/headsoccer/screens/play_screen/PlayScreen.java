package com.geven.headsoccer.screens.play_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.geven.headsoccer.screens.play_screen.gameworld.GameRender;
import com.geven.headsoccer.screens.play_screen.gameworld.GameWorld;
import com.geven.headsoccer.screens.play_screen.handler.InputHandler;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;

public class PlayScreen implements Screen {

    //Box2D:
    private static World world;

    private GameRender gamerender;
    private GameWorld gameworld;
    private float runTime;


    public PlayScreen(){
        world = new World(new Vector2(0,9.81f),true);
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
    public static World getWorld(){
        return world;
    }
}
