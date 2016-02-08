package com.geven.headsoccer.screens.play_screen.collision;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;

public class SpriteHomeCollision {
    private float face[];

    public SpriteHomeCollision(){
        face = new float[] {0.8f,155.2f,0};

    }

    public void update(){
        if (SpriteHome.getPosition().x  + face[0] <= 0){
            SpriteHome.setPositionX(0 + face[0]);
        }
        if (SpriteHome.getPosition().x + face[1] >= 2040){
            SpriteHome.setPositionX(2040 - 200 + (200 - face[1]));
        }
    }
    public static void draw(ShapeRenderer shaperenderer){
        shaperenderer.setColor(Color.RED);
        shaperenderer.begin(ShapeRenderer.ShapeType.Line);
        shaperenderer.rect(SpriteHome.getPosition().x + 24, SpriteHome.getPosition().y + 130, 64, 60);
        shaperenderer.circle(Ball.getPosition().x + Ball.getSize().x / 2, Ball.getPosition().y + Ball.getSize().x / 2, Ball.getSize().x / 2);

        shaperenderer.circle(SpriteHome.getPosition().x + 16.8f + (128.8f/2),SpriteHome.getPosition().y + 0 + (128.8f/2),128.8f/2);
        shaperenderer.circle(SpriteHome.getPosition().x + 16.8f + (128.8f/2),SpriteHome.getPosition().y + (161-128.8f) + (128.8f/2),128.8f/2);
        shaperenderer.end();

    }
}
