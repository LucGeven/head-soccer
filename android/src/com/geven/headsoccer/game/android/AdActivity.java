package com.geven.headsoccer.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class AdActivity extends Activity {

    private InterstitialAd interstitialAd;
    private boolean adblockerActive;

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
                startActivity(new Intent("com.geven.headsoccer.game.android.MAIN_ACTIVITY"));
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                adblockerActive = false;
                if (errorCode == 2) {
                    BufferedReader in;
                    try {
                        in = new BufferedReader(new InputStreamReader(new FileInputStream("/etc/hosts")));
                        String line;
                        while ((line = in.readLine()) != null) {
                            if (line.contains("admob")) {
                                Toast.makeText(getApplicationContext(), "Disable AdBlocker! Wait 10 seconds!", Toast.LENGTH_LONG).show();
                                adblockerActive = true;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent("com.geven.headsoccer.game.android.MAIN_ACTIVITY"));
                                    }
                                }, 10000);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!adblockerActive) {
                        startActivity(new Intent("com.geven.headsoccer.game.android.MAIN_ACTIVITY"));
                    }
                }
            }
        });

        requestNewInterstitial();

    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("0B89FBE8066B801ADC39642F5CA1A263")
                .build();

        interstitialAd.loadAd(adRequest);
    }
    private void displayInterstitial(){
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}
