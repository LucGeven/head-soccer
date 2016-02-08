package com.geven.headsoccer.screens.game.gameworld;

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

public class GameRender {
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spritebatch;

    //Font bold:
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    //Declare for get width and height of screen
    private GlyphLayout layout,layout1,layout2;

    //Font normal:
    private BitmapFont font1;
    private FreeTypeFontGenerator generator1;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter1;

    //Font normal white:
    private BitmapFont font2;
    private FreeTypeFontGenerator generator2;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter2;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true,2040,1360);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        spritebatch = new SpriteBatch();
        spritebatch.setProjectionMatrix(cam.combined);

        //Initialize Font Bold:
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 250;
        parameter.color = Color.BLACK;
        parameter.flip = true;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font = generator.generateFont(parameter);

        layout = new GlyphLayout();
        layout.setText(font, "VS");

        //Initialize Font Normal:
        generator1 = new FreeTypeFontGenerator(Gdx.files.internal("font/font_bold.ttf"));
        parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 60;
        parameter1.color = Color.BLACK;
        parameter1.flip = true;
        parameter1.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font1 = generator1.generateFont(parameter1);

        //Initialize Font Normal:
        generator2 = new FreeTypeFontGenerator(Gdx.files.internal("font/font_bold.ttf"));
        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 60;
        parameter2.color = Color.WHITE;
        parameter2.flip = true;
        parameter2.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font2 = generator2.generateFont(parameter2);

        layout1 = new GlyphLayout();
        layout2 = new GlyphLayout();
    }
    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw gameBackground:
        spritebatch.begin();
        spritebatch.disableBlending();
        spritebatch.draw(AssetLoader.gameBackground, 0, 0, 2040, 1360);
        spritebatch.enableBlending();
        spritebatch.end();

        //Draw rectangle transparent:
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f)); // last argument is alpha channel
        shapeRenderer.rect(100, 100, 2040 - 200, 1360 - 200);

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //Draw Logos and Text "VS" and club names
        spritebatch.begin();

        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam), 300, 1360 / 2 - 225, 450, 450);
        AssetLoader.teamslogos.findRegion(VariablesHandler.myTeam).flip(false, true);

        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);
        spritebatch.draw(AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam), 2040 - 750, 1360 / 2 - 225, 450, 450);
        AssetLoader.teamslogos.findRegion(VariablesHandler.computerTeam).flip(false, true);

        font.draw(spritebatch, "VS", 2040 / 2 - layout.width / 2, 1360 / 2 - layout.height / 2);
        layout1.setText(font1, VariablesHandler.myTeam);
        font2.draw(spritebatch, VariablesHandler.myTeam, 300 + (450/2) - (layout1.width/2), 1360 / 2 - 225 + 450 + 50);
        layout1.setText(font1, VariablesHandler.computerTeam);
        font2.draw(spritebatch, VariablesHandler.computerTeam, 2040 - 750 + (450/2) - (layout1.width / 2), 1360 / 2 - 225 + 450 + 50);

        spritebatch.end();

        //Draw buttons:
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(1, 1, 1, 0.5f)); // last argument is alpha channel

        shapeRenderer.rect(120, 1360 - 250, 225, 125);
        shapeRenderer.rect(2040 - 120 - 225, 1360 - 250, 225, 125);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //Draw text in buttons:
        spritebatch.begin();
        layout2.setText(font1, "BACK");
        font1.draw(spritebatch, "BACK", 120 + (225 / 2) - (layout2.width / 2), (1360 - 250) + (125 / 2) - (layout2.height / 2));
        layout2.setText(font1, "PLAY");
        font1.draw(spritebatch, "PLAY", (2040 - 120 - 225) + (225 / 2) - (layout2.width / 2), (1360 - 250) + (125 / 2) - (layout2.height / 2));
        spritebatch.end();

    }
}
