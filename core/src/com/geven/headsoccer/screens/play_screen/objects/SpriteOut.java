package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.play_screen.PlayScreen;

import static com.geven.headsoccer.handler.Vars.COLLISION;
import static com.geven.headsoccer.handler.Vars.JUMPFORCE;
import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.SPRITE_HOME;
import static com.geven.headsoccer.handler.Vars.SPRITE_OUT;

public class SpriteOut {

    //Box2D

    //Face:
    private static Body oBody1, oBody2;
    private static BodyDef bodyDef1, bodyDef2;
    private static FixtureDef fixtureDef1, fixtureDef2;
    private CircleShape shape1, shape2;

    //Shoe:
    private static Body oShoe;
    private static BodyDef oBodyDefShoe;
    private static FixtureDef oFixtureDefShoe;
    private static PolygonShape oShape;

    private static Vector2 position;
    private static Vector2 size;

    private float velocityWalk;

    private static float runTime;

    private static boolean shootIsPressed;
    private static boolean leftIsPressed;
    private static boolean rightIsPressed;
    private static boolean jumpIsPressed;

    private static boolean jumping;

    private static boolean collision, collision1;

    private static float oTime;

    public SpriteOut(){
        //Box2D
        b2dHead();
        position = new Vector2(oBody1.getPosition().x - (54.4f / PPM) - ((128.8f /2) / PPM), oBody1.getPosition().y - ((128.8f / 2) / PPM));  //Position sprite
        size = new Vector2(200 / PPM,200 / PPM);
        
        velocityWalk = (500 - ((200 / VariablesHandler.lengthPositionInFinalComputerCompetition) * VariablesHandler.positionInFinalComputerCompetition)) / PPM;

        runTime = 0;
        shootIsPressed = false;
        leftIsPressed = false;
        rightIsPressed = false;
        jumpIsPressed = false;

        collision = false;
        collision1 = false;

        oTime = 0;

        b2dShoe();


    }
    public void update(float delta){
        pressedHandler(delta);
        jumpHandlerUpdate();

        if (runTime <= 0.24 && runTime >= 0.18){        //Check if you kicked or walkt
            oTime += delta;
        }
        else {
            oTime = 0;
        }

        oBody1.setTransform(oBody1.getPosition().x, oBody1.getPosition().y, 0);      //Set the angle to 0
        oBody2.setTransform(oBody2.getPosition().x, oBody2.getPosition().y, 0);

        position.set(oBody1.getPosition().x - (54.4f / PPM) - ((128.8f / 2) / PPM), oBody1.getPosition().y - ((128.8f / 2) / PPM));       //Update the sprite position

        updateShoePosition();

        oBody2.setTransform(oBody1.getPosition().x, oBody2.getPosition().y, 0);

    }
    private void b2dHead(){
        //Body1:
        bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef1.position.set(((2040 - (250 + 20 + 200)) / PPM) + (54.4f / PPM) + ((128.8f / 2) / PPM),((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM));

        shape1 = new CircleShape();
        shape1.setRadius((128.8f / 2) / PPM);

        fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.restitution = 0;
        fixtureDef1.density = 200000;       //Infinity
        fixtureDef1.filter.categoryBits = SPRITE_OUT;
        fixtureDef1.filter.maskBits = COLLISION | SPRITE_HOME;

        oBody1 = PlayScreen.getWorld().createBody(bodyDef1);
        oBody1.createFixture(fixtureDef1);
        oBody1.setGravityScale(0);
        oBody1.setUserData("SPRITE_OUT");

        //Body2
        bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(((2040 - (250 + 20 + 200)) / PPM) + (54.4f / PPM) + ((128.8f / 2) / PPM), ((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM));
        shape2 = new CircleShape();
        shape2.setRadius((128.8f / 2) / PPM);

        fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.restitution = 0;
        fixtureDef2.density = 200000;       //Infinity
        //fixtureDef2.density = 2.5f;
        fixtureDef2.filter.categoryBits = SPRITE_OUT;
        fixtureDef2.filter.maskBits = COLLISION | SPRITE_HOME;

        oBody2 = PlayScreen.getWorld().createBody(bodyDef2);
        oBody2.createFixture(fixtureDef2);
        oBody2.setGravityScale(0);
        oBody2.setUserData("SPRITE_OUT");
    }
    private void b2dShoe(){
        oBodyDefShoe = new BodyDef();
        oBodyDefShoe.type = BodyDef.BodyType.DynamicBody;
        oBodyDefShoe.position.set(position.x + (112 / PPM) + ((64 /2) / PPM) ,position.y + (130 / PPM) + ((60 / 2) / PPM));
        //hBodyDefShoe.position.set((24 / PPM) + (64 / 2 / PPM) + (16.8f / PPM) + ((128.8f / 2) / PPM),(130 / PPM) + (60 / 2 / PPM)+ ((128.8f / 2) / PPM));
        oBodyDefShoe.gravityScale = 0;

        oShape = new PolygonShape();
        oShape.setAsBox((64 / 2) / PPM,(60 / 2) / PPM);

        oFixtureDefShoe = new FixtureDef();
        oFixtureDefShoe.shape = oShape;
        oFixtureDefShoe.restitution = 0;
        oFixtureDefShoe.density = 200000;
        oFixtureDefShoe.filter.categoryBits = SPRITE_OUT;
        oFixtureDefShoe.filter.maskBits = COLLISION | SPRITE_HOME;

        oShoe = PlayScreen.getWorld().createBody(oBodyDefShoe);
        oShoe.createFixture(oFixtureDefShoe);
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
            oBody1.setLinearVelocity(-velocityWalk, oBody1.getLinearVelocity().y);
            oBody2.setLinearVelocity(-velocityWalk, oBody2.getLinearVelocity().y);
        }
        else if (rightIsPressed){
            oBody1.setLinearVelocity(velocityWalk, oBody1.getLinearVelocity().y);
            oBody2.setLinearVelocity(velocityWalk, oBody2.getLinearVelocity().y);

        }
        else {
            oBody1.setLinearVelocity(0, oBody1.getLinearVelocity().y);
            oBody2.setLinearVelocity(0, oBody2.getLinearVelocity().y);
        }
    }
    private void jumpHandlerUpdate(){
        if (oBody1.getPosition().y >= ((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM) || ContactListener.test){   //Check if the sprite is jumping
            oBody1.setGravityScale(0);           //Turn of the gravity
            oBody2.setGravityScale(0);
            oBody1.setLinearVelocity(oBody1.getLinearVelocity().x, 0);        //Set linearvelocity to 0
            oBody2.setLinearVelocity(oBody2.getLinearVelocity().x, 0);
            if (!ContactListener.test) {
                oBody1.setTransform(oBody1.getPosition().x, ((1360 - 200 - 190) / PPM) + ((128.8f / 2) / PPM), 0);  //Set the position to the normal position
                oBody2.setTransform(oBody2.getPosition().x, ((1360 - 200 - 190) / PPM) + ((161 - 128.8f) / PPM) + ((128.8f / 2) / PPM), 0);
            }
                jumping = false;
        }
        if (oBody1.getPosition().y < ((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM) && !ContactListener.test){
            oBody1.setGravityScale(1);
            oBody2.setGravityScale(1);
        }
    }
    private void updateShoePosition(){
        if (runTime >= (0.03f * 0) && runTime < (0.03f * 1)){
            oShoe.setTransform(position.x + (112 / PPM) + ((64 / 2) / PPM), position.y + (130 / PPM) + ((60 / 2) / PPM), 0);
            //hShape.setAsBox((64 / 2) / PPM, (60 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((64 / 2) / PPM, (60 / 2) / PPM);

            //SpriteHome.getPosition().x + 112,SpriteHome.getPosition().y + 130,64,60
        }
        if (runTime >= (0.03f * 1)&& runTime < (0.03f * 2)){
            oShoe.setTransform(position.x + (96 / PPM) + ((64 / 2) / PPM), position.y + (50 / PPM) + ((50 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((64 / 2) / PPM, (50 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //priteHome.getPosition().x + 96,SpriteHome.getPosition().y + 140,64,50
        }
        if (runTime >= (0.03f * 2) && runTime < (0.03f * 3)){
            oShoe.setTransform(position.x + (73.6f / PPM) + ((68.8f / 2) / PPM), position.y + (157 / PPM) + ((35 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((68.8f / 2) / PPM, (35 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 73.6f,SpriteHome.getPosition().y + 157,68.8f,35
        }
        if (runTime >= (0.03f * 3) && runTime < (0.03f * 4)){
            oShoe.setTransform(position.x + (60.8f / PPM) + ((68 / 2) / PPM), position.y + (147 / PPM) + ((53 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((68 / 2) / PPM, (53 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 60.8f,SpriteHome.getPosition().y + 147,68,53
        }
        if (runTime >= (0.03f * 4) && runTime < (0.03f * 5)){
            oShoe.setTransform(position.x + (41.6f / PPM) + ((73.6f / 2) / PPM), position.y + (130 / PPM) + ((65 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((73.6f / 2) / PPM, (65 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 41.6f,SpriteHome.getPosition().y + 130,73.6f,65
        }
        if (runTime >= (0.03f * 5) && runTime < (0.03f * 6)){
            oShoe.setTransform(position.x + (26.4f / PPM) + ((59.2f / 2) / PPM), position.y + (92 / PPM) + ((77 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((59.2f / 2) / PPM, (77 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 26.4f,SpriteHome.getPosition().y + 92,59.2f,77
        }
        if (runTime >= (0.03f * 6) && runTime < (0.03f * 7)){
            oShoe.setTransform(position.x + (33.6f / PPM) + ((38.4f / 2) / PPM), position.y + (63 / PPM) + ((81 / 2) / PPM), 0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((38.4f / 2) / PPM, (81 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 33.6f,SpriteHome.getPosition().y + 63,38.4f,81
        }
        if (runTime >= (0.03f * 7) && runTime < (0.03f * 8)){
            oShoe.setTransform(position.x + (36 / PPM) + ((40 / 2) / PPM),position.y + (60 / PPM) + ((82 / 2) / PPM),0);
            ((PolygonShape) oShoe.getFixtureList().get(0).getShape()).setAsBox((40 / 2) / PPM, (82 / 2) / PPM);
            //hFixtureDefShoe.shape = hShape;
            //hShoe.createFixture(hFixtureDefShoe);
            //SpriteHome.getPosition().x + 36,SpriteHome.getPosition().y + 60,40,82
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
            if (oBody1.getPosition().y == ((1360 - 200 - 190) / PPM ) + ((128.8f / 2) / PPM) && !jumping){   //Check if the sprite isn't jumping
                oBody1.setGravityScale(1);           //Turn on the gravity
                oBody2.setGravityScale(1);
                oBody1.applyForceToCenter(0, JUMPFORCE, true);   //Apply the force for jumping
                oBody2.applyForceToCenter(0, JUMPFORCE, true);
                jumping = true;
            }
        }
    }

    public static Body getoBody1() {
        return oBody1;
    }

    public static Body getoBody2() {
        return oBody2;
    }

    public static Body getoShoe() {
        return oShoe;
    }

    public static float getoTime() {
        return oTime;
    }

    public static float getRunTime() {
        return runTime;
    }

    public static Vector2 getPosition() {
        return position;
    }

    public static Vector2 getSize() {
        return size;
    }
}
