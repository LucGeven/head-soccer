package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

    private ImageButton match,competition,tournament,about;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("0B89FBE8066B801ADC39642F5CA1A263")
                .build();
        mAdView.loadAd(adRequest);

        match = (ImageButton) findViewById(R.id.match);
        competition = (ImageButton) findViewById(R.id.competition);
        tournament = (ImageButton) findViewById(R.id.tournament);
        about = (ImageButton) findViewById(R.id.about);

        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.geven.headsoccer.LIBGDX"));
            }
        });
        competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });
        tournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.geven.headsoccer.game.android.ABOUT_ACTIVITY"));
            }
        });
    }
}
