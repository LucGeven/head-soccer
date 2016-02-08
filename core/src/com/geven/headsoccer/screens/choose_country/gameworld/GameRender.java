package com.geven.headsoccer.screens.choose_country.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.choose_country.handler.GestureHandler;

public class GameRender {
    public static OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    //Declare for get width and height of screen
    private GlyphLayout layout;

    private String countries[];

    private Vector2 positionRectangle;
    private Vector2 positionText;
    public static Vector2 surfaceRectangle;
    private int margeRectangle, margeText;

    private int i;              //Variable for check which value in country array
    private boolean draw;       //Check if country array isn't empty

    private TextureRegion atlasCountries;

    public static float buttonsPositionX[];
    public static float buttonsPositionY[];
    int arrayValue;

    public static float deltaY;

    public GameRender(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 1360, 2040);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        //Initialize Font:
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.flip = true;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCÇDEËFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font = generator.generateFont(parameter);

        countries = VariablesHandler.countries;

        layout = new GlyphLayout();
        layout.setText(font,countries[0]);

        positionRectangle = new Vector2(1360/2-400,50);
        positionText = new Vector2((280+ 500), (50) + (layout.height + layout.height /4));
        surfaceRectangle = new Vector2(1000,150);
        margeRectangle = margeText = 20;

        draw = true;
        i = 1;

        atlasCountries = new TextureRegion();

        deltaY = 0;

        //buttonsPosition = new float[countries.length][3];
        buttonsPositionX = new float[countries.length];
        buttonsPositionY = new float[countries.length];
        arrayValue = 0;

    }

    public void render(){
        Gdx.gl.glClearColor(0, 150 / 255f, 136 / 255f, 1);              //Material Color Background Hex Code = #009688
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        positionRectangle.y += deltaY;              //for scroll
        positionText.y += deltaY;                   //for scroll


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);

        //Draw rectangles:
        while (draw){
            if (i <= countries.length){
                shapeRenderer.rect(positionRectangle.x,positionRectangle.y + margeRectangle, surfaceRectangle.x, surfaceRectangle.y);   // Draw rectangle in de middle of the x-as

                //buttonsPosition[i-1][0] = arrayValue;
                buttonsPositionX[i-1] = positionRectangle.x;
                buttonsPositionY[i-1] = positionRectangle.y + margeRectangle;

                margeRectangle += surfaceRectangle.y + 20;  //Add the marge for the new rectangle;
                i++;                                        //i++ for the country array
                arrayValue++;
            }
            else {
                draw = false;                               //Go ahead
                i = 1;                                      //again with the array for the country
                margeRectangle = 20;                        //Set the marge again on 20

                arrayValue = 0;
            }

        }
        draw = true;                                        //Next time again in the while loop

        shapeRenderer.end();

        spriteBatch.begin();                                            //Begin Spritebatch

        font.setColor(Color.BLACK);                                     //Make The font black

        //Draw Text:
        while (draw){
            if (i<= countries.length){
                layout.setText(font,countries[i-1]);        //Set text from country array for get the middle of the screen
                font.draw(spriteBatch,countries[i-1],positionText.x - (layout.width/2),positionText.y + margeText);     //Set the text in de middle of the rectangle
                margeText += surfaceRectangle.y + 20;       //Marge for the next text
                i++;                                        //Variable + 1
            }
            else {
                draw = false;                               //Not yet any more in this loop
                i = 1;                                      //Reset the i variable
                margeText = 20;                             //Reset the variable
            }
        }
        draw = true;                                        //Go in this loop next time

        //Draw flags:
        while (draw){
            if (i<= countries.length){
                atlasCountries = AssetLoader.countriesFlags.findRegion(countries[i - 1]);
                atlasCountries.flip(false,true);
                spriteBatch.draw(atlasCountries, 20, positionRectangle.y + margeRectangle, 240, surfaceRectangle.y);
                atlasCountries.flip(false,true);
                margeRectangle += surfaceRectangle.y + 20;
                i++;
            }
            else {
                draw = false;
                i = 1;
                margeRectangle = 20;
            }
        }
        draw = true;
        spriteBatch.end();                                              //End Spritebatch



    }
}