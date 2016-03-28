package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.Intent;

public class StartActivity implements com.geven.headsoccer.handler.StartActivity {

    private Activity activity;

    public StartActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    public void startActivity(String intent) {
        activity.startActivity(new Intent(intent));
    }
}
