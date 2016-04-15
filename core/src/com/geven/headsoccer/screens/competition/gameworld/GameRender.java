package com.geven.headsoccer.screens.competition.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.competition.handler.DataLoader;

public class GameRender {

    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private GlyphLayout layout;
    private float height;

    private VariablesHandler varHandler;

    float positionY;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false,1360,2040);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        //Initialize Font:
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        font = generator.generateFont(parameter);

        layout = new GlyphLayout();
        layout.setText(font,"PSV");
        height = layout.height + 20;

        varHandler = new VariablesHandler();

        positionY = 2020;

    }

    public void render(){
        Gdx.gl.glClearColor(35 / 255f, 53 / 255f, 83 / 255f, 1);           //Material Color Background Hex Code = #233553
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        positionY = 2020;

        spriteBatch.begin();
        font.setColor(Color.WHITE);

        font.draw(spriteBatch,"P",860,2020);
        font.draw(spriteBatch,"W",940,2020);
        font.draw(spriteBatch,"D",1020,2020);
        font.draw(spriteBatch,"L", 1100,2020);
        font.draw(spriteBatch,"GD",1180,2020);
        font.draw(spriteBatch,"PTS",1260,2020);

        positionY -= height;

        //font.draw(spriteBatch,)
        for (String club : DataLoader.competition){
            font.draw(spriteBatch,club,20,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).matchs),860,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).win),940,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).draw),1020,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).lose),1100,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).goal),1180,positionY);
            font.draw(spriteBatch,Integer.toString(DataLoader.teams.get(club).points),1260,positionY);
            positionY -= height;
        }

        //Draw buttons:
        spriteBatch.draw(AssetLoader.buttonBack,20,50,300,200);
        spriteBatch.draw(AssetLoader.buttonDelete,20 + 300 + 20,50,300,200);
        spriteBatch.draw(AssetLoader.buttonFixture,1360 - 20 - 300 - 20 - 300,50,300,200);
        spriteBatch.draw(AssetLoader.buttonPlay,1360 - 20 - 300,50,300,200);
        spriteBatch.end();
    }
}
