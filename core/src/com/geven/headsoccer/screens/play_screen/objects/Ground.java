package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.PPM;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class Ground {

    private Body ground;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;  /*Test*/
    private ChainShape shape;

    public Ground(){

        //Body definition:
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, (1360 - 190) / PPM);

        //Shape definition:
        shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-50 / PPM,0),new Vector2((2040 + 50) / PPM,0)});

        //Fixture definition:
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

        ground = PlayScreen.getWorld().createBody(bodyDef);
        ground.createFixture(fixtureDef);

    }
}
