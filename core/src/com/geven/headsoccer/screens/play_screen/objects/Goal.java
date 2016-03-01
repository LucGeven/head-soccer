package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.COLLISION;
import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.SPRITE_HOME;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class Goal {

    private static Vector2 positionHome, positionOut;
    private static Vector2 sizeHome, sizeOut;

    //Box2D:
    public static Body hBody1, hBody2, oBody1, oBody2;
    private BodyDef hBodyDef1, hBodyDef2, oBodyDef1, oBodyDef2;
    private FixtureDef hFixtureDef1, hFixtureDef2, oFixtureDef1, oFixtureDef2;
    private PolygonShape hShape1, hShape2, oShape1, oShape2;        //h = home, o = out

    public Goal(){
        positionHome = new Vector2(0, (1360 - 400 - 190) / PPM);            //190 came from the button x position + the marge: 20;
        sizeHome = new Vector2(200 / PPM, 400 / PPM);

        positionOut = new Vector2((2040 - 200) / PPM, (1360 - 400 - 190) / PPM);
        sizeOut = new Vector2(200 / PPM, 400 / PPM);

        //Box2D:

        //hGoal1:
        hBodyDef1 = new BodyDef();
        hBodyDef1.type = BodyDef.BodyType.StaticBody;
        hBodyDef1.position.set(positionHome.x + ((13.33f / 2) / PPM),positionHome.y + ((400 / 2) / PPM));

        hShape1 = new PolygonShape();
        hShape1.setAsBox((13.33f / 2) / PPM, (400 / 2) / PPM);

        hFixtureDef1 = new FixtureDef();
        hFixtureDef1.shape = hShape1;
        hFixtureDef1.friction = 0.5f;
        hFixtureDef1.restitution = 0;
        hFixtureDef1.filter.categoryBits = COLLISION;
        hFixtureDef1.filter.maskBits = SPRITE_HOME | COLLISION;


        hBody1 = PlayScreen.getWorld().createBody(hBodyDef1);
        hBody1.createFixture(hFixtureDef1);

        //hGoal2:
        hBodyDef2 = new BodyDef();
        hBodyDef2.type = BodyDef.BodyType.StaticBody;
        hBodyDef2.position.set(positionHome.x + ((200 / 2) / PPM),positionHome.y + ((13.33f / 2) / PPM));

        hShape2 = new PolygonShape();
        hShape2.setAsBox((200 / 2) / PPM, (13.33f / 2) / PPM);

        hFixtureDef2 = new FixtureDef();
        hFixtureDef2.shape = hShape2;
        hFixtureDef2.friction = 0.5f;
        hFixtureDef2.restitution = 0;
        hFixtureDef2.filter.categoryBits = COLLISION;
        hFixtureDef2.filter.maskBits = SPRITE_HOME | COLLISION;

        hBody2 = PlayScreen.getWorld().createBody(hBodyDef2);
        hBody2.createFixture(hFixtureDef2);

        //oGoal1:
        oBodyDef1 = new BodyDef();
        oBodyDef1.type = BodyDef.BodyType.StaticBody;
        oBodyDef1.position.set(positionOut.x + (186.66f / PPM) + ((13.33f / 2) / PPM), positionOut.y + ((400 / 2) / PPM));

        oShape1 = new PolygonShape();
        oShape1.setAsBox((13.33f / 2) / PPM, (400 / 2) / PPM);

        oFixtureDef1 = new FixtureDef();
        oFixtureDef1.shape = oShape1;
        oFixtureDef1.friction = 0.5f;
        oFixtureDef1.restitution = 0;
        oFixtureDef1.filter.categoryBits = COLLISION;
        oFixtureDef1.filter.maskBits = SPRITE_HOME | COLLISION;

        oBody1 = PlayScreen.getWorld().createBody(oBodyDef1);
        oBody1.createFixture(oFixtureDef1);

        //oGoal2
        oBodyDef2 = new BodyDef();
        oBodyDef2.type = BodyDef.BodyType.StaticBody;
        oBodyDef2.position.set(positionOut.x + ((200 / 2) / PPM),positionOut.y + ((13.33f / 2) / PPM));

        oShape2 = new PolygonShape();
        oShape2.setAsBox((200 / 2) / PPM, (13.33f / 2) / PPM);

        oFixtureDef2 = new FixtureDef();
        oFixtureDef2.shape = oShape2;
        oFixtureDef2.friction = 0.5f;
        oFixtureDef2.restitution = 0;
        oFixtureDef2.filter.categoryBits = COLLISION;
        oFixtureDef2.filter.maskBits = SPRITE_HOME | COLLISION;

        oBody2 = PlayScreen.getWorld().createBody(oBodyDef2);
        oBody2.createFixture(oFixtureDef2);
    }


    public static Vector2 getPositionHome(){
        return positionHome;
    }
    public static Vector2 getPositionOut(){
        return positionOut;
    }
    public static Vector2 getSizeHome(){
        return sizeHome;
    }
    public static Vector2 getSizeOut(){
        return sizeOut;
    }
}
