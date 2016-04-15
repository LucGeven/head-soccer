package com.geven.headsoccer.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geven.headsoccer.LoadScreen;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.ScreenRotation;
import com.geven.headsoccer.handler.StartActivity;
import com.geven.headsoccer.handler.VariablesHandler;

public class HeadSoccer extends Game {

	public ScreenRotation screenRotation;					//Declare screenRotation
	public StartActivity startActivity;
	private VariablesHandler varHandler;
	public static String situation;


	public HeadSoccer(ScreenRotation screenRotation,StartActivity startActivity,String situation){
		this.screenRotation = screenRotation;				//Initalise this.screenRotation
		this.startActivity = startActivity;
		HeadSoccer.situation = situation;
		varHandler = new VariablesHandler();

	}

	@Override
	public void create() {
		//AssetLoader.load();						//Load textures
		setScreen(new LoadScreen(this));
		//setScreen(new ChooseCountry(this));		//Start ChooseCountry with parameter Game
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();					//Dispose Textures
	}

}
