package com.geven.headsoccer.screens.play_screen.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.play_screen.collision.SpriteHomeCollision;
import com.geven.headsoccer.screens.play_screen.objects.Ball;
import com.geven.headsoccer.screens.play_screen.objects.Goal;
import com.geven.headsoccer.screens.play_screen.objects.Score;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.SpriteOut;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;

public class GameRender {
    private OrthographicCamera cam;
    private SpriteBatch spritebatch;
    private ShapeRenderer shaperender;

    private BitmapFont font, font1;
    private FreeTypeFontGenerator generator, generator1;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter, parameter1;

    //Declare for get width and height of screen
    private GlyphLayout layout, layout1;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true,2040,1360);
        spritebatch = new SpriteBatch();
        spritebatch.setProjectionMatrix(cam.combined);
        shaperender = new ShapeRenderer();
        shaperender.setProjectionMatrix(cam.combined);

        //Initialize Font digital:
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_digital.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.color = Color.WHITE;
        parameter.flip = true;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font = generator.generateFont(parameter);

        layout = new GlyphLayout();

        //Initialize font bold:
        generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/font_bold.ttf"));
        parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 60;
        parameter1.color = Color.WHITE;
        parameter1.flip = true;
        parameter1.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font1 = generator1.generateFont(parameter1);

        layout1 = new GlyphLayout();
    }
    public void render(float runTime){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spritebatch.begin();
        spritebatch.disableBlending();
        spritebatch.draw(AssetLoader.gameBackground, 0, 0, 2040, 1360);

        spritebatch.enableBlending();

        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam), 50, 50, 150, 150);
        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);

        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam), 2040 - 150 - 50, 50, 150, 150);
        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);

        spritebatch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shaperender.begin(ShapeRenderer.ShapeType.Filled);
        shaperender.setColor(new Color(1, 1, 1, 0.8f));
        shaperender.rect(2040 / 2 - 100, 50, 200, 100);

        shaperender.setColor(new Color(0, 51 / 255f, 102 / 255f, 0.8f));  // 0 51 102
        shaperender.rect(2040 / 2 - 100 - 200, 50, 200, 100);
        shaperender.rect(2040 / 2 + 100, 50, 200, 100);
        shaperender.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        spritebatch.begin();
        //Draw score:
        layout.setText(font, Float.toString(Score.getScore().x));
        layout.setText(font, Integer.toString((int) Score.getScore().x));
        font.draw(spritebatch, Integer.toString((int) Score.getScore().x), (2040 / 2) - 300 + (200 / 2) - (layout.width / 2), 50 + (100 / 2) - (layout.height / 2));
        layout.setText(font, Integer.toString((int) Score.getScore().y));
        font.draw(spritebatch, Integer.toString((int) Score.getScore().y), (2040 / 2) + 100 + (200 / 2) - (layout.width / 2), 50 + (100 / 2) - (layout.height / 2));

        //Draw time:
        layout.setText(font, Integer.toString(TimerHandler.getMinute()) + ":" + Integer.toString(TimerHandler.getTientallen()) + Integer.toString(TimerHandler.getSecond()));

        font.draw(spritebatch, Integer.toString(TimerHandler.getMinute()) + ":" + Integer.toString(TimerHandler.getTientallen()) + Integer.toString(TimerHandler.getSecond()),
                2040 / 2 - 100 + (200 / 2) - (layout.width / 2), 50 + (100 / 2) - (layout.height / 2));

        //Draw spriteHome:
        AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime()).flip(true, true);
        spritebatch.draw(AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime(), true), SpriteHome.getPosition().x, SpriteHome.getPosition().y, SpriteHome.getSize().x, SpriteHome.getSize().y);
        AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime()).flip(true, true);
        //Draw spriteOut:
        AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime()).flip(false,true);
        spritebatch.draw(AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime(), true), SpriteOut.getPosition().x, SpriteOut.getPosition().y, SpriteOut.getSize().x, SpriteOut.getSize().y);
        AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime()).flip(false, true);

        //Draw ball:
        spritebatch.draw(AssetLoader.ball,Ball.getPosition().x,Ball.getPosition().y, Ball.getSize().x,Ball.getSize().y);

        //Draw goals:
        spritebatch.draw(AssetLoader.goalHome, Goal.getPositionHome().x,Goal.getPositionHome().y,Goal.getSizeHome().x,Goal.getSizeHome().y);
        spritebatch.draw(AssetLoader.goalOut, Goal.getPositionOut().x,Goal.getPositionOut().y,Goal.getSizeOut().x,Goal.getSizeOut().y);

        spritebatch.end();

        //Draw buttons transparent:
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shaperender.begin(ShapeRenderer.ShapeType.Filled);
        shaperender.setColor(new Color(0, 0, 0, 0.5f)); // last argument is alpha channel
        shaperender.rect(20, 1360 - 20 - 150, 350, 150);       //Draw left
        shaperender.rect(370 + 20, 1360 - 20 - 150, 350, 150);   //Draw right
        shaperender.rect(2040 - 20 - 350, 1360 - 20 - 150, 350, 150);      //Draw shoot
        shaperender.rect(2040 - 370 - 20 - 350, 1360 - 20 - 150, 350, 150);  //Draw jump

        shaperender.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //Draw text in buttons
        spritebatch.begin();
        layout1.setText(font1, "LEFT");
        font1.draw(spritebatch, "LEFT", 20 + (350 / 2) - (layout1.width / 2), 1360 - 20 - 150 + (150 / 2) - (layout1.height / 2));
        layout1.setText(font1, "RIGHT");
        font1.draw(spritebatch, "RIGHT", 370 + 20 + (350 / 2) - (layout1.width / 2), 1360 - 20 - 150 + (150 / 2) - (layout1.height / 2));
        layout1.setText(font1, "SHOOT");
        font1.draw(spritebatch, "SHOOT", 2040 - 370 - 20 - 350 + (350 / 2) - (layout1.width / 2), 1360 - 20 - 150 + (150 / 2) - (layout1.height / 2));
        layout1.setText(font1, "JUMP");
        font1.draw(spritebatch, "JUMP", 2040 - 20 - 350 + (350 / 2) - (layout1.width / 2), 1360 - 20 - 150 + (150 / 2) - (layout1.height / 2));

        spritebatch.end();

        //SpriteHomeCollision.draw(shaperender);

    }
}
