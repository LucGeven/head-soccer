package com.geven.headsoccer.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static TextureAtlas countriesFlags;
    public static TextureAtlas teamslogos;
    public static Texture _GAMEBACKGROUND;
    public static TextureRegion gameBackground;
    public static Texture _GOAL;
    public static TextureRegion goalHome, goalOut;
    public static TextureAtlas sprite;
    public static Animation spriteAnimation;
    public static Texture _BALL;
    public static TextureRegion ball;
    public static Sprite spriteBall;

    public static Sound goal;
    public static Sound boo;
    public static Sound shoot;

    public static void load(){
        countriesFlags = new TextureAtlas(Gdx.files.internal("texture/countries.pack"));
        //teamslogos     = new TextureAtlas(Gdx.files.internal("texture/all.pack"));
        teamslogos = new TextureAtlas(Gdx.files.internal("texture/clubs/clubs.pack"));
        _GAMEBACKGROUND = new Texture(Gdx.files.internal("texture/game_background.png"));
        _GOAL = new Texture(Gdx.files.internal("texture/goal.png"));

        gameBackground = new TextureRegion(_GAMEBACKGROUND,_GAMEBACKGROUND.getWidth(),_GAMEBACKGROUND.getHeight());
        gameBackground.flip(false,true);

        goalHome = new TextureRegion(_GOAL, _GOAL.getWidth(), _GOAL.getHeight());
        goalHome.flip(false,true);
        goalOut = new TextureRegion(_GOAL, _GOAL.getWidth(), _GOAL.getHeight());
        goalOut.flip(true,true);

        sprite = new TextureAtlas(Gdx.files.internal("texture/sprite.pack"));
        TextureRegion sprites[] = {sprite.findRegion("sprite1"),sprite.findRegion("sprite2"),sprite.findRegion("sprite3"),sprite.findRegion("sprite4"),
                sprite.findRegion("sprite5"),sprite.findRegion("sprite6"),sprite.findRegion("sprite7"),sprite.findRegion("sprite8")};

        spriteAnimation = new Animation(0.03f,sprites);
        spriteAnimation.setPlayMode(Animation.PlayMode.LOOP);

        _BALL = new Texture(Gdx.files.internal("texture/ball.png"));
        ball = new TextureRegion(_BALL,_BALL.getWidth(),_BALL.getHeight());
        ball.flip(false,true);
        spriteBall = new Sprite(ball);

        goal = Gdx.audio.newSound(Gdx.files.internal("sound/goal.mp3"));
        boo = Gdx.audio.newSound(Gdx.files.internal("sound/boo.mp3"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("sound/shoot.mp3"));

    }
    public static void dispose(){
        countriesFlags.dispose();
        teamslogos.dispose();
        _GAMEBACKGROUND.dispose();
        _GOAL.dispose();
        sprite.dispose();
        _BALL.dispose();
        goal.dispose();
        boo.dispose();
        shoot.dispose();
    }
}
