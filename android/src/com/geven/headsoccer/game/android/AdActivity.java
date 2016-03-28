package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity extends Activity {

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9208655874199429/4648638991");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayInterstitial();
            }

            @Override
            public void onAdClosed() {
                //requestNewInterstitial();
                startActivity(new Intent("com.geven.headsoccer.LIBGDX"));
            }
        });

        requestNewInterstitial();

    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("0B89FBE8066B801ADC39642F5CA1A263")
                .build();

        interstitialAd.loadAd(adRequest);
    }
    private void displayInterstitial(){
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}
