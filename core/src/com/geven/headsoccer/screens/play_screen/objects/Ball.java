package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.COLLISION;
import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.SPRITE_HOME;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class Ball {

    public static Body ball;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private CircleShape shape;

    public Ball(){

        //Body definition:
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.position.set((2040 / 2 - (100 / 2)) / PPM, (1360 - 190 - 100) / PPM);
        bodyDef.position.set(2040 / 2 / PPM, (1360 - 190 - 50) / PPM);

        //Shape definition:
        shape = new CircleShape();
        shape.setRadius(50 / PPM);

        //Fixture definition:
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.75f;
        fixtureDef.filter.categoryBits = COLLISION;
        fixtureDef.filter.maskBits = SPRITE_HOME | COLLISION;

        ball = PlayScreen.getWorld().createBody(bodyDef);
        ball.createFixture(fixtureDef);
        AssetLoader.spriteBall.setSize(100 / PPM, 100 / PPM);
        AssetLoader.spriteBall.setOrigin(AssetLoader.spriteBall.getWidth() / 2, AssetLoader.spriteBall.getHeight() / 2);
        ball.setUserData(AssetLoader.spriteBall);
    }
        }