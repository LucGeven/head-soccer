package com.geven.headsoccer.screens.create_menu.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameRender {
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    public static Vector2 position_CHOOSECOUNTRY;
    public static Vector2 surface_CHOOSECOUNTRY;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private GlyphLayout layout;
    private float width_ChooseCountry;
    private float height_ChooseCountry;
    private String chooseCountry;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1360, 2040);                //ScreenSize is 136 by 204
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        position_CHOOSECOUNTRY = new Vector2();
        surface_CHOOSECOUNTRY = new Vector2();
        surface_CHOOSECOUNTRY.set(600,150);               //Surface from Button
        position_CHOOSECOUNTRY.set(((1360/2)-(surface_CHOOSECOUNTRY.x/2)),(2040/2)-(surface_CHOOSECOUNTRY.y/2));  //set position to middle

        //Initialize Font:
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        font = generator.generateFont(parameter);

        //Get Width of text:
        layout = new GlyphLayout();
        chooseCountry = "AFTRAP";
        layout.setText(font,chooseCountry);
        width_ChooseCountry = layout.width;
        height_ChooseCountry = layout.height;

    }
    public void render(){
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClearColor(0, 150 / 255f, 136 / 255f, 1);           //Material Color Background Hex Code = #009688
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw a button:
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(position_CHOOSECOUNTRY.x, position_CHOOSECOUNTRY.y, surface_CHOOSECOUNTRY.x, surface_CHOOSECOUNTRY.y);   //Draw button ChooseCountry

        shapeRenderer.end();

        spriteBatch.begin();
        //Set text:
        font.setColor(Color.BLACK);
        font.draw(spriteBatch,chooseCountry,(1360/2) - (width_ChooseCountry/2),(2040/2)+(height_ChooseCountry/2));

        spriteBatch.end();

    }

}
