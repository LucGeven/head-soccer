package com.geven.headsoccer.game.android;

import android.os.Bundle;

		import com.badlogic.gdx.backends.android.AndroidApplication;
		import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
		import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.ScreenRotation;

public class AndroidLauncher extends AndroidApplication {					//Superclass is AndroidApplication, an Activity

	private ScreenRotationAndroid screenRotationAndroid;					//Declare the android specific code screenRotationAndroid
	private StartActivity startActivityAndroid;								//Declare the android specific code for startActivityAndroid

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		screenRotationAndroid = new ScreenRotationAndroid(this);			//Initialise screenRotationAndroid with parameter the Activity
		startActivityAndroid = new StartActivity(this);						//Initialise StartActivity with parameter the Activity

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new HeadSoccer(screenRotationAndroid,startActivityAndroid,MainActivity.situation), config);			//Parameter from headsoccer is the screenRotationAndroid
	}																		//I'll say that because that the HeadSoccer class know that the screenRotation = screenRotationAndroid
	@Override
	public void onBackPressed() {
		//Nothing when back pressed
	}
}
