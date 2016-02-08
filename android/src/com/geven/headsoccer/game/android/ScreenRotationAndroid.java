package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.geven.headsoccer.handler.ScreenRotation;

public class ScreenRotationAndroid implements ScreenRotation {              //implements ScreenRotation, the interface in LIBGdx.

    private Activity activity;                                              //Declare an activity for get the method setRequestedOrientation

    public ScreenRotationAndroid(Activity activity){                        //Parameter Activity from the AndroidLauncher
        this.activity = activity;                                           //Initialise this.activity
    }

    @Override
    public void rotateScreen(String rotation) {                             //@Override in ScreenRotation,
                                                                            //Parameter is portrait or landscape

        if (rotation == "portrait"){                                        //Check if rotation is portrait
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);                 //Method to set the screen orientation
        }
        else if (rotation == "landscape"){                                                              //Check if rotation is landscape
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);                //Method to set the screen orientation
        }
    }
}
