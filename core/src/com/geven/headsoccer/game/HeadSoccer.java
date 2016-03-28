package com.geven.headsoccer.game;


import com.badlogic.gdx.Game;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.ScreenRotation;
import com.geven.headsoccer.handler.StartActivity;
import com.geven.headsoccer.handler.VariablesHandler;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.create_menu.CreateMenu;

public class HeadSoccer extends Game {

	public ScreenRotation screenRotation;					//Declare screenRotation
	public StartActivity startActivity;
	private VariablesHandler varHandler;

	public HeadSoccer(ScreenRotation screenRotation,StartActivity startActivity){
		this.screenRotation = screenRotation;				//Initalise this.screenRotation
		this.startActivity = startActivity;
		varHandler = new VariablesHandler();
	}

	@Override
	public void create() {
		AssetLoader.load();						//Load textures

		setScreen(new CreateMenu(this));		//Start CreateMenu with parameter Game

	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();					//Dispose Textures
	}

}
