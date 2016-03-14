package com.geven.headsoccer.screens.play_screen.objects;

import static com.geven.headsoccer.handler.Vars.COLLISION;
import static com.geven.headsoccer.handler.Vars.JUMPFORCE;
import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.SPRITE_HOME;
import static com.geven.headsoccer.handler.Vars.SPRITE_OUT;

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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

public class SpriteHome {

    //Box2D

    //Face:
    private static Body hBody1, hBody2;
    private static BodyDef bodyDef1, bodyDef2;
    private static FixtureDef fixtureDef1, fixtureDef2;
    private CircleShape shape1, shape2;

    //Shoe:
    private static Body hShoe;
    private static BodyDef hBodyDefShoe;
    private static FixtureDef hFixtureDefShoe;
    private static PolygonShape hShape;

    private static Vector2 position;
    private static Vector2 size;

    private float velocityWalk;

    private static float runTime;

    private static boolean shootIsPressed;
    private static boolean leftIsPressed;
    private static boolean rightIsPressed;
    private static boolean jumpIsPressed;

    public static boolean jumping;

    public static boolean collision, collision1;

    private static float hTime;
    public SpriteHome(){
        //Box2D
        b2dHead();

        position = new Vector2(hBody1.getPosition().x - (16.8f / PPM) - ((128.8f / 2) / PPM), hBody1.getPosition().y - ((128.8f / 2) / PPM));  //Position sprite
        size = new Vector2(200 / PPM,200 / PPM);

        velocityWalk = 200 / PPM;    //160

        runTime = 0;
        shootIsPressed = false;
        leftIsPressed = false;
        rightIsPressed = false;
        jumpIsPressed = false;

        collision = false;
        collision1 = false;

        hTime = 0;

        b2dShoe();

    }
    public void update(float delta){
        pressedHandler(delta);

        jumpHandlerUpdate();

        if (runTime <= 0.24 && runTime >= 0.18){        //Check if you kicked or walkt
            hTime += delta;
        }
        else {
            hTime = 0;
        }

        hBody1.setTransform(hBody1.getPosition().x, hBody1.getPosition().y, 0);      //Set the angle to 0
        hBody2.setTransform(hBody2.getPosition().x, hBody2.getPosition().y,0);

        position.set(hBody1.getPosition().x - (16.8f / PPM) - ((128.8f / 2) / PPM), hBody1.getPosition().y - ((128.8f / 2) / PPM));       //Update the sprite position

        //Update shoe position:
        //For example:
        //TODO:
        //hShoe.setLinearVelocity(1,0);
        updateShoePosition();
        //hShoe.setTransform(position.x + (24 / PPM) + ((64 / 2) / PPM), position.y + (130 / PPM) + ((60 / 2) / PPM), 0);


    }

    private void b2dHead(){
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
        fixtureDef1.filter.maskBits = COLLISION | SPRITE_OUT;

        hBody1 = PlayScreen.getWorld().createBody(bodyDef1);
        hBody1.createFixture(fixtureDef1);
        hBody1.setGravityScale(0);
        hBody1.setUserData("SPRITE_HOME");


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
        //fixtureDef2.density = 2.5f;
        fixtureDef2.filter.categoryBits = SPRITE_HOME;
        fixtureDef2.filter.maskBits = COLLISION | SPRITE_OUT;

        hBody2 = PlayScreen.getWorld().createBody(bodyDef2);
        hBody2.createFixture(fixtureDef2);
        hBody2.setGravityScale(0);
        hBody2.setUserData("SPRITE_HOME");
    }

    private void b2dShoe(){
        hBodyDefShoe = new BodyDef();
        hBodyDefShoe.type = BodyDef.BodyType.DynamicBody;
        hBodyDefShoe.position.set(position.x + (24 / PPM) + ((64 /2) / PPM) ,position.y + (130 / PPM) + ((60 / 2) / PPM));
        //hBodyDefShoe.position.set((24 / PPM) + (64 / 2 / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM),(130 / PPM) + (60 / 2 / PPM)+ ((128.8f / 2) / PPM));
        hBodyDefShoe.gravityScale = 0;

        hShape = new PolygonShape();
        hShape.setAsBox((64 / 2) / PPM,(60 / 2) / PPM);

        hFixtureDefShoe = new FixtureDef();
        hFixtureDefShoe.shape = hShape;
        hFixtureDefShoe.restitution = 0;
        hFixtureDefShoe.density = 200000;
        hFixtureDefShoe.filter.categoryBits = SPRITE_HOME;
        hFixtureDefShoe.filter.maskBits = COLLISION | SPRITE_OUT;

        hShoe = PlayScreen.getWorld().createBody(hBodyDefShoe);
        hShoe.createFixture(hFixtureDefShoe);

                //SpriteHome.getPosition().x + 24,SpriteHome.getPosition().y + 130,64,60
    }
    private void updateShoePosition(){
        //hShoe.destroyFixture(hShoe.getFixtureList().first());
        if (runTime >= (0.03f * 0) && runTime < (0.03f * 1)){
            hShoe.setTransform(position.x + (24 / PPM) + ((64 / 2) / PPM), position.y + (130 / PPM) + ((60 / 2) / PPM), 0);
            //hShape.setAsBox((64 / 2) / PPM, (60 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((64 / 2) / PPM, (60 / 2) / PPM);

            //SpriteHome.getPosition().x + 24,SpriteHome.getPosition().y + 130,64,60
        }
        if (runTime >= (0.03f * 1)&& runTime < (0.03f * 2)){
            hShoe.setTransform(position.x + (40 / PPM) + ((64 / 2) / PPM), position.y + (50 / PPM) + ((50 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((64 / 2) / PPM, (50 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //priteHome.getPosition().x + 40,SpriteHome.getPosition().y + 140,64,50
        }
        if (runTime >= (0.03f * 2) && runTime < (0.03f * 3)){
            hShoe.setTransform(position.x + (57.6f / PPM) + ((68.8f / 2) / PPM), position.y + (157 / PPM) + ((35 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((68.8f / 2) / PPM, (35 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 57.6f,SpriteHome.getPosition().y + 157,68.8f,35
        }
        if (runTime >= (0.03f * 3) && runTime < (0.03f * 4)){
            hShoe.setTransform(position.x + (71.2f / PPM) + ((68 / 2) / PPM), position.y + (147 / PPM) + ((53 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((68 / 2) / PPM, (53 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 71.2f,SpriteHome.getPosition().y + 147,68,53
        }
        if (runTime >= (0.03f * 4) && runTime < (0.03f * 5)){
            hShoe.setTransform(position.x + (84.8f / PPM) + ((73.6f / 2) / PPM), position.y + (130 / PPM) + ((65 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((73.6f / 2) / PPM, (65 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 84.8f,SpriteHome.getPosition().y + 130,73.6f,65
        }
        if (runTime >= (0.03f * 5) && runTime < (0.03f * 6)){
            hShoe.setTransform(position.x + (114.4f / PPM) + ((59.2f / 2) / PPM), position.y + (92 / PPM) + ((77 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((59.2f / 2) / PPM, (77 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 114.4f,SpriteHome.getPosition().y + 92,59.2f,77
        }
        if (runTime >= (0.03f * 6) && runTime < (0.03f * 7)){
            hShoe.setTransform(position.x + (128 / PPM) + ((38.4f / 2) / PPM), position.y + (63 / PPM) + ((81 / 2) / PPM), 0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((38.4f / 2) / PPM, (81 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 128,SpriteHome.getPosition().y + 63,38.4f,81
        }
        if (runTime >= (0.03f * 7) && runTime < (0.03f * 8)){
            hShoe.setTransform(position.x + (124 / PPM) + ((40 / 2) / PPM),position.y + (60 / PPM) + ((82 / 2) / PPM),0);
            ((PolygonShape) hShoe.getFixtureList().get(0).getShape()).setAsBox((40 / 2) / PPM, (82 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 124,SpriteHome.getPosition().y + 60,40,82
        }


    }

    private void pressedHandler(float delta){
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
            hBody1.setLinearVelocity(-velocityWalk, hBody1.getLinearVelocity().y);
            hBody2.setLinearVelocity(-velocityWalk, hBody2.getLinearVelocity().y);
        }
        else if (rightIsPressed){
            hBody1.setLinearVelocity(velocityWalk, hBody1.getLinearVelocity().y);
            hBody2.setLinearVelocity(velocityWalk, hBody2.getLinearVelocity().y);

        }
        else {
            hBody1.setLinearVelocity(0, hBody1.getLinearVelocity().y);
            hBody2.setLinearVelocity(0, hBody2.getLinearVelocity().y);
        }
    }

    private void jumpHandlerUpdate(){
        if (hBody1.getPosition().y >= ((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM)){   //Check if the sprite is jumping
            hBody1.setGravityScale(0);           //Turn of the gravity
            hBody2.setGravityScale(0);
            hBody1.setLinearVelocity(hBody1.getLinearVelocity().x, 0);        //Set linearvelocity to 0
            hBody2.setLinearVelocity(hBody2.getLinearVelocity().x, 0);
            hBody1.setTransform(hBody1.getPosition().x, ((1360 - 200 - 190) / PPM) + ((128.8f / 2) / PPM), 0);  //Set the position to the normal position
            hBody2.setTransform(hBody2.getPosition().x,((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM),0);
            jumping = false;
        }
    }


    public static void shootIsPressed(boolean pressed){
        shootIsPressed = pressed;
    }
    public static void leftIsPressed(boolean pressed){
            leftIsPressed = pressed;

    }
    public static void rightIsPressed(boolean pressed){
        if (collision1) {
            rightIsPressed = false;
        }
        else {
            rightIsPressed = pressed;
        }
    }
    public static void jumpIsPressed(boolean pressed) {
        if (pressed){
            if (hBody1.getPosition().y == ((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM) && !jumping){   //Check if the sprite isn't jumping
                hBody1.setGravityScale(1);           //Turn on the gravity
                hBody2.setGravityScale(1);
                hBody1.applyForceToCenter(0, JUMPFORCE, true);   //Apply the force for jumping
                hBody2.applyForceToCenter(0, JUMPFORCE, true);
                jumping = true;
            }
        }
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

    public static Body gethBody1() {
        return hBody1;
    }

    public static Body gethShoe() {
        return hShoe;
    }

    public static Body gethBody2() {
        return hBody2;

    }

    public static float gethTime() {
        return hTime;
    }
}
