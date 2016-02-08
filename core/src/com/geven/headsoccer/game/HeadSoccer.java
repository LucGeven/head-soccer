package com.geven.headsoccer.game;


import com.badlogic.gdx.Game;
import com.geven.headsoccer.handler.AssetLoader;
import com.geven.headsoccer.handler.ScreenRotation;
import com.geven.headsoccer.screens.choose_country.ChooseCountry;
import com.geven.headsoccer.screens.create_menu.CreateMenu;

public class HeadSoccer extends Game {

	public ScreenRotation screenRotation;					//Declare screenRotation

	public HeadSoccer(ScreenRotation screenRotation){
		this.screenRotation = screenRotation;				//Initalise this.screenRotation
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
