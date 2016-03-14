package com.geven.headsoccer.screens.play_screen.gameworld;

import static com.geven.headsoccer.handler.Vars.PPM;
import static com.geven.headsoccer.handler.Vars.TIMESTEP;
import static com.geven.headsoccer.handler.Vars.VELOCITYITERATIONS;
import static com.geven.headsoccer.handler.Vars.POSITIONITERATIONS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.play_screen.PlayScreen;
import com.geven.headsoccer.screens.play_screen.objects.Goal;
import com.geven.headsoccer.screens.play_screen.objects.Score;
import com.geven.headsoccer.screens.play_screen.objects.SpriteHome;
import com.geven.headsoccer.screens.play_screen.objects.SpriteOut;
import com.geven.headsoccer.screens.play_screen.objects.TimerHandler;

public class GameRender {
    private OrthographicCamera cam, camTest;
    private SpriteBatch spritebatch, spritebatchFont;
    private ShapeRenderer shaperender;

    private BitmapFont font, font1;
    private FreeTypeFontGenerator generator, generator1;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter, parameter1;

    //Declare for get width and height of screen
    private GlyphLayout layout, layout1;

    //Box2d render bodies:
    private Array<Body> tmpBodies;
    private Box2DDebugRenderer box2DDebugRenderer;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true,2040 / PPM,1360 / PPM);

        camTest = new OrthographicCamera();
        camTest.setToOrtho(true, 2040 , 1360);

        spritebatchFont = new SpriteBatch();
        spritebatchFont.setProjectionMatrix(camTest.combined);

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

        //Box2D:
        tmpBodies = new Array<Body>();
        box2DDebugRenderer = new Box2DDebugRenderer();
    }
    public void render(float runTime){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Calculator box2d:
        PlayScreen.getWorld().step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        //SpriteHome.toDynamic();

        spritebatch.begin();
        spritebatch.disableBlending();
        spritebatch.draw(AssetLoader.gameBackground, 0, 0, 2040 / PPM, 1360 / PPM);         //Draw background

        spritebatch.enableBlending();

        //Draw SpriteHome:
        AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime()).flip(true, true);
        spritebatch.draw(AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime(), true), SpriteHome.getPosition().x, SpriteHome.getPosition().y, SpriteHome.getSize().x, SpriteHome.getSize().y);
        AssetLoader.spriteAnimation.getKeyFrame(SpriteHome.getRunTime()).flip(true, true);
        //Draw SpriteOut:
        AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime()).flip(false, true);
        spritebatch.draw(AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime(), true), SpriteOut.getPosition().x, SpriteOut.getPosition().y, SpriteOut.getSize().x, SpriteOut.getSize().y);
        AssetLoader.spriteAnimation.getKeyFrame(SpriteOut.getRunTime()).flip(false, true);

        //Draw ball:
        PlayScreen.getWorld().getBodies(tmpBodies);
        for (Body body : tmpBodies){
            if (body.getUserData() != null && body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - (sprite.getWidth() / 2),body.getPosition().y - (sprite.getHeight() / 2));
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(spritebatch);
            }
        }

        //Draw team logos:
        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam), 50 / PPM, 50 / PPM, 150 / PPM, 150 / PPM);
        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);

        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam), (2040 - 150 - 50) / PPM, 50 / PPM, 150 / PPM, 150 / PPM);
        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);

        //Draw goals:
        spritebatch.draw(AssetLoader.goalHome, Goal.getPositionHome().x,Goal.getPositionHome().y,Goal.getSizeHome().x,Goal.getSizeHome().y);
        spritebatch.draw(AssetLoader.goalOut,Goal.getPositionOut().x,Goal.getPositionOut().y,Goal.getSizeOut().x,Goal.getSizeOut().y);

        spritebatch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        //Draw rectangles for score
        shaperender.begin(ShapeRenderer.ShapeType.Filled);
        shaperender.setColor(new Color(1, 1, 1, 0.8f));
        shaperender.rect((2040 / 2 - 100) / PPM, 50 / PPM, 200 / PPM, 100 / PPM);

        shaperender.setColor(new Color(0, 51 / 255f, 102 / 255f, 0.8f));  // 0 51 102
        shaperender.rect((2040 / 2 - 100 - 200) / PPM, 50 / PPM, 200 / PPM, 100 / PPM);
        shaperender.rect((2040 / 2 + 100) / PPM, 50 / PPM, 200 / PPM, 100 / PPM);
        shaperender.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        spritebatchFont.begin();
        //Draw score:
        layout.setText(font, Float.toString(Score.getScore().x));
        layout.setText(font, Integer.toString((int) Score.getScore().x));
        font.draw(spritebatchFont, Integer.toString((int) Score.getScore().x), ((2040 / 2) - 300 + (200 / 2) - (layout.width / 2)), (50 + (100 / 2) - (layout.height / 2)));
        layout.setText(font, Integer.toString((int) Score.getScore().y));
        font.draw(spritebatchFont, Integer.toString((int) Score.getScore().y), ((2040 / 2) + 100 + (200 / 2) - (layout.width / 2)), (50 + (100 / 2) - (layout.height / 2)));

        //Draw time:
        layout.setText(font, Integer.toString(TimerHandler.getMinute()) + ":" + Integer.toString(TimerHandler.getTientallen()) + Integer.toString(TimerHandler.getSecond()));

        font.draw(spritebatchFont, Integer.toString(TimerHandler.getMinute()) + ":" + Integer.toString(TimerHandler.getTientallen()) + Integer.toString(TimerHandler.getSecond()),
                (2040 / 2 - 100 + (200 / 2) - (layout.width / 2)), (50 + (100 / 2) - (layout.height / 2)));


        spritebatchFont.end();

        //Draw buttons transparent:
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shaperender.begin(ShapeRenderer.ShapeType.Filled);
        shaperender.setColor(new Color(0, 0, 0, 0.5f)); // last argument is alpha channel
        shaperender.rect(20 / PPM, (1360 - 20 - 150) / PPM, 350 / PPM, 150 / PPM);       //Draw left
        shaperender.rect((370 + 20) / PPM, (1360 - 20 - 150) / PPM, 350 / PPM, 150 / PPM);   //Draw right
        shaperender.rect((2040 - 20 - 350) / PPM, (1360 - 20 - 150) / PPM, 350 / PPM, 150 / PPM);      //Draw shoot
        shaperender.rect((2040 - 370 - 20 - 350) / PPM, (1360 - 20 - 150) / PPM, 350 / PPM, 150 / PPM);  //Draw jump

        shaperender.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //Draw text in buttons
        spritebatchFont.begin();
        layout1.setText(font1, "LEFT");
        font1.draw(spritebatchFont, "LEFT", (20 + (350 / 2) - (layout1.width / 2)), (1360 - 20 - 150 + (150 / 2) - (layout1.height / 2)));
        layout1.setText(font1, "RIGHT");
        font1.draw(spritebatchFont, "RIGHT", (370 + 20 + (350 / 2) - (layout1.width / 2)), (1360 - 20 - 150 + (150 / 2) - (layout1.height / 2)));
        layout1.setText(font1, "SHOOT");
        font1.draw(spritebatchFont, "SHOOT", (2040 - 370 - 20 - 350 + (350 / 2) - (layout1.width / 2)), (1360 - 20 - 150 + (150 / 2) - (layout1.height / 2)));
        layout1.setText(font1, "JUMP");
        font1.draw(spritebatchFont, "JUMP", (2040 - 20 - 350 + (350 / 2) - (layout1.width / 2)), (1360 - 20 - 150 + (150 / 2) - (layout1.height / 2)));

        spritebatchFont.end();

        box2DDebugRenderer.render(PlayScreen.getWorld(),cam.combined);
    }
}
