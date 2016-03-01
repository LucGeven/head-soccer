package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

import static com.geven.headsoccer.handler.Vars.PPM;

public class WorldSize {

    private Body left, right, up;
    private BodyDef bodyDefLeft, bodyDefRight, bodyDefUp;
    private FixtureDef fixtureDefLeft, fixtureDefRight, fixtureDefUp;
    private ChainShape shapeLeft, shapeRight, shapeUp;

    public WorldSize(){
        //Left:

        //Body definition:
        bodyDefLeft = new BodyDef();
        bodyDefLeft.type = BodyDef.BodyType.StaticBody;
        bodyDefLeft.position.set(0, 0);

        //Shape definition:
        shapeLeft = new ChainShape();
        shapeLeft.createChain(new Vector2[]{new Vector2(0,-50 / PPM), new Vector2(0,(1360 + 50) / PPM)});

        //Fixture definition:
        fixtureDefLeft = new FixtureDef();
        fixtureDefLeft.shape = shapeLeft;

        left = PlayScreen.getWorld().createBody(bodyDefLeft);
        left.createFixture(fixtureDefLeft);

        //Right:

        //Body definition:
        bodyDefRight = new BodyDef();
        bodyDefRight.type = BodyDef.BodyType.StaticBody;
        bodyDefRight.position.set(2040 / PPM,0);

        //Shape definition:
        shapeRight = new ChainShape();
        shapeRight.createChain(new Vector2[]{new Vector2(0,-50 / PPM),new Vector2(0,(1360 + 50) / PPM)});

        //Fixture definition:
        fixtureDefRight = new FixtureDef();
        fixtureDefRight.shape = shapeRight;

        right = PlayScreen.getWorld().createBody(bodyDefRight);
        right.createFixture(fixtureDefRight);

        //Up:

        //Body definition:
        bodyDefUp = new BodyDef();
        bodyDefUp.type = BodyDef.BodyType.StaticBody;
        bodyDefRight.position.set(0,0);

        //Shape definition:
        shapeUp = new ChainShape();
        shapeUp.createChain(new Vector2[] {new Vector2(-50 / PPM,0),new Vector2((2040 + 50) / PPM,0)});

        //Fixture definition:
        fixtureDefUp = new FixtureDef();
        fixtureDefUp.shape = shapeUp;

        up = PlayScreen.getWorld().createBody(bodyDefUp);
        up.createFixture(fixtureDefUp);

    }
}
