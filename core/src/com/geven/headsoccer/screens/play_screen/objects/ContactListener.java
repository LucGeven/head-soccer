package com.geven.headsoccer.screens.play_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    private float prevX1, prevY1, prevX2, prevY2;
    private static Vector2 position1;

    @Override
    public void beginContact(Contact contact) {

        //Fix ball and goal bug:
        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1) ||         //Collision with ball and goal
                (contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball)){
            SpriteHome.collision = true;
        }
        if (SpriteHome.collision){
            if (contact.getFixtureA().getBody() == SpriteHome.gethBody2() && contact.getFixtureB().getBody() == Ball.ball ||             //Collision with SpriteHome and ball
                    contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == SpriteHome.gethBody2()) {
                SpriteHome.collision1 = true;

            }
        }
        //------------------

        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == SpriteHome.gethShoe()) ||           //Collision with ball and shoe
                (contact.getFixtureA().getBody() == SpriteHome.gethShoe() && contact.getFixtureB().getBody() == Ball.ball)){
            Ball.ball.setLinearVelocity(0,0);
            if (SpriteHome.gethTime() >= 0.2f){
                if (Ball.ball.getLinearVelocity().x == 0){
                    Ball.ball.setLinearVelocity(SpriteHome.gethBody1().getLinearVelocity().x,Ball.ball.getLinearVelocity().y);
                }
                else {
                    Ball.ball.setLinearVelocity(SpriteHome.gethBody1().getLinearVelocity().x, Ball.ball.getLinearVelocity().y);
                }
            }
            else if (SpriteHome.gethTime() != 0){
                Ball.ball.setLinearVelocity(20,10);
            }
        }
        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == SpriteOut.getoShoe()) ||
                (contact.getFixtureA().getBody() == SpriteOut.getoShoe() && contact.getFixtureB().getBody() == Ball.ball)){
            Ball.ball.setLinearVelocity(0,0);
            if (SpriteOut.getoTime() >= 0.2f){
                if (Ball.ball.getLinearVelocity().x == 0){
                    Ball.ball.setLinearVelocity(SpriteOut.getoBody1().getLinearVelocity().x,Ball.ball.getLinearVelocity().y);
                }
                else {
                    Ball.ball.setLinearVelocity(SpriteOut.getoBody1().getLinearVelocity().x,Ball.ball.getLinearVelocity().y);
                }
            }
            else if (SpriteOut.getoTime() != 0){
                Ball.ball.setLinearVelocity(20,10);
            }
        }

        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1) ||           //Collision with ball and goal
                (contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball)){
            if (Ball.ball.getLinearVelocity().x == 0) {
                Ball.ball.setLinearVelocity(-1, 0);
            }
        }

        prevX1 = SpriteHome.gethBody1().getPosition().x;
        prevY1 = SpriteHome.gethBody1().getPosition().y;
        prevX2 = SpriteHome.gethBody2().getPosition().x;
        prevY2 = SpriteHome.gethBody2().getPosition().y;

        position1 = new Vector2(SpriteHome.gethBody1().getPosition());
        SpriteHome.jumping = false;
    }

    @Override
    public void endContact(Contact contact) {
        if (contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1 ||
                contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball){
            SpriteHome.collision = false;
            SpriteHome.collision1 = false;
        }
        if (contact.getFixtureA().getBody() == SpriteHome.gethBody1() && contact.getFixtureB().getBody() == Ball.ball ||
                contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == SpriteHome.gethBody2()){
            SpriteHome.collision1 = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1) ||         //Collision with ball and goal
                (contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball)){
            SpriteHome.collision = true;
        }
        if (SpriteHome.collision){
            if (contact.getFixtureA().getBody() == SpriteHome.gethBody2() && contact.getFixtureB().getBody() == Ball.ball ||             //Collision with SpriteHome and ball
                    contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == SpriteHome.gethBody2()) {
                SpriteHome.collision1 = true;
                SpriteHome.gethBody1().getPosition().set(prevX1,prevY2);
                SpriteHome.gethBody2().getPosition().set(prevX2,prevY2);
                Gdx.app.log("TEST","TEST");
            }
        }
        if ((contact.getFixtureA().getBody() == Ball.ball && contact.getFixtureB().getBody() == Goal.oBody1) ||           //Collision with ball and goal
                (contact.getFixtureA().getBody() == Goal.oBody1 && contact.getFixtureB().getBody() == Ball.ball)){
            if (Ball.ball.getLinearVelocity().x == 0) {
                Ball.ball.setLinearVelocity(-1, 0);
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
