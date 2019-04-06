package android.by.itacademy.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Main extends Activity {
    private AdView mAdView;

    private int endYear = 2019; //значения берутся от пользователя
    private int endMonth = 5; //значения берутся от пользователя
    private int endDay = 5; //значения берутся от пользователя
    private int endHour = 15; //значения берутся от пользователя
    private int endMinutes = 30; //значения берутся от пользователя
    private int endSeconds = 30; //значения берутся от пользователя


    private Converter converter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        converter = new Converter();
        converter.covertDataToMillis(endYear, endMonth, endDay, endHour, endMinutes, endSeconds);

        MobileAds.initialize(this, "ca-app-pub-1501215034144631~1349082130");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("AAA", "" + errorCode);
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
            }
        });
    }
}