package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AboutActivity extends Activity {

    private ImageButton ibPlayStore, ibMoreApps, ibBack;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ibPlayStore = (ImageButton) findViewById(R.id.playstore);
        ibPlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.geven.headsoccer.game.android"));
                startActivity(intent);
            }
        });

        ibMoreApps = (ImageButton) findViewById(R.id.moreapps);
        ibMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://search?q=pub:Luc%20Geven"));
                startActivity(intent);
            }
        });

        ibBack = (ImageButton) findViewById(R.id.back);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.geven.headsoccer.game.android.MAIN_ACTIVITY"));
            }
        });

        mAdView = (AdView) findViewById(R.id.banner_ad);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("0B89FBE8066B801ADC39642F5CA1A263")
                .build();
        mAdView.loadAd(adRequest);
    }
}
