package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.COLLISION;
import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.SPRITE_HOME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class SpriteHome {

    //Box2D
    private static Body body1, body2;
    private static BodyDef bodyDef1, bodyDef2;
    private static FixtureDef fixtureDef1, fixtureDef2;
    private CircleShape shape1, shape2;

    private static Vector2 position;
    private static Vector2 size;

    private float velocityWalk;
    private float velocityJump;
    private float jumpDistance;

    private static float runTime;

    private static boolean shootIsPressed;
    private static boolean leftIsPressed;
    private static boolean rightIsPressed;
    private static boolean jumpIsPressed;


    private float prevX, prevY;

    private static boolean collision, collision1;
    public SpriteHome(){
        //Box2D

        //Body1
        bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef1.position.set(((250 + 20) / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM),((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM));

        shape1 = new CircleShape();
        shape1.setRadius((128.8f / 2) / PPM);

        fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.restitution = 0;
        fixtureDef1.density = 200000;       //Infinity
        fixtureDef1.filter.categoryBits = SPRITE_HOME;
        fixtureDef1.filter.maskBits = COLLISION;

        body1 = PlayScreen.getWorld().createBody(bodyDef1);
        body1.createFixture(fixtureDef1);
        body1.setGravityScale(0);
        body1.setUserData("SPRITE_HOME");


        //Body2
        bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(((250 + 20) / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM));
        shape2 = new CircleShape();
        shape2.setRadius((128.8f / 2) / PPM);

        fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.restitution = 0;
        fixtureDef2.density = 200000;       //Infinity
        fixtureDef2.filter.categoryBits = SPRITE_HOME;
        fixtureDef2.filter.maskBits = COLLISION;

        body2 = PlayScreen.getWorld().createBody(bodyDef2);
        body2.createFixture(fixtureDef2);
        body2.setGravityScale(0);
        body2.setUserData("SPRITE_HOME");




        position = new Vector2(body1.getPosition().x - (16.8f / PPM) - ((128.8f / 2) / PPM),body1.getPosition().y - ((128.8f / 2) / PPM));  //Position sprite
        size = new Vector2(200 / PPM,200 / PPM);

        velocityWalk = 160 / PPM;    //4
        velocityJump = 7 / PPM;
        jumpDistance = 200 / PPM;

        runTime = 0;
        shootIsPressed = false;
        leftIsPressed = false;
        rightIsPressed = false;
        jumpIsPressed = false;

        collision = false;
        collision1 = false;
        PlayScreen.getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1) ||
                        (contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball)){
                    collision = true;
                    Gdx.app.log("TEST","COLLISION");
                }
                if (collision){
                    if (contact.getFixtureA().getBody() == body2 && contact.getFixtureB().getBody() == Ball.ball ||
                            contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == body2) {
                        collision1 = true;
                        body1.setLinearVelocity(0, 0);
                        body2.setLinearVelocity(0, 0);
                    }


                }
            }

            @Override
            public void endContact(Contact contact) {
                if (contact.getFixtureA().getBody() == body2 && contact.getFixtureB().getBody() == Goal.oBody1 ||
                        contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == body2){
                    collision = false;
                    collision1 = false;
                }
                if (contact.getFixtureA().getBody() == body2 && contact.getFixtureB().getBody() == Ball.ball ||
                        contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == body2){
                    collision = false;
                    collision1 = false;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }
    public void update(float delta){
        //Code to handle the shoe position:
        if (shootIsPressed){
            if (runTime >= 0.21){
                runTime = (0.03f * 8) - 0.03f ;
            }
            else {
                runTime += delta;
            }
        }
        else {
            if (runTime <= 0){
                runTime = 0;
            }
            else {
                runTime -= delta;
            }
        }
        if (leftIsPressed){
                body1.setLinearVelocity(-velocityWalk, 0);
                body2.setLinearVelocity(-velocityWalk, 0);
        }
        else if (rightIsPressed){
                body1.setLinearVelocity(velocityWalk, 0);
                body2.setLinearVelocity(velocityWalk, 0);

        }
        else {
            body1.setLinearVelocity(0,0);
            body2.setLinearVelocity(0, 0);
        }

        position.set(body1.getPosition().x - (16.8f / PPM) - ((128.8f / 2) / PPM), body1.getPosition().y - ((128.8f / 2) / PPM));

    }
    public static void toStatic(){
        bodyDef1.type = BodyDef.BodyType.StaticBody;
        bodyDef2.type = BodyDef.BodyType.StaticBody;
    }
    public static void toDynamic(){
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
    }
    public static void shootIsPressed(boolean pressed){
        shootIsPressed = pressed;
    }
    public static void leftIsPressed(boolean pressed){
        if (!collision1) {
            leftIsPressed = pressed;
        }
    }
    public static void rightIsPressed(boolean pressed){
        if (!collision1) {
            rightIsPressed = pressed;
        }
    }
    public static void jumpIsPressed(boolean pressed){
        //TODO:
    }
    public static float getRunTime(){
        return runTime;
    }
    public static Vector2 getPosition(){
        return position;
    }
    public static Vector2 getSize(){
        return size;
    }
}
