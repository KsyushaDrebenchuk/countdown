package android.by.itacademy.countdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {
    private AdView mAdView;

    public static final String MILLIS  = "Millis";
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinutes;
    private int endSeconds;

    private EditText editTextYear;
    private EditText editTextMonth;
    private EditText editTextDay;
    private EditText editTextHour;
    private EditText editTextMinutes;
    private EditText editTextSeconds;
    private Button buttonSave;

    private Converter converter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        editTextYear = findViewById(R.id.editTextYearMain);
        editTextMonth = findViewById(R.id.editTextMonthMain);
        editTextDay = findViewById(R.id.editTextDayMain);
        editTextHour = findViewById(R.id.editTextHourMain);
        editTextMinutes = findViewById(R.id.editTextMinutesMain);
        editTextSeconds = findViewById(R.id.editTextSecondsMain);
        buttonSave = findViewById(R.id.buttonSaveEvent);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endYear = Integer.parseInt(editTextYear.getText().toString());
                endMonth = Integer.parseInt(editTextMonth.getText().toString());
                endDay = Integer.parseInt(editTextDay.getText().toString());
                endHour = Integer.parseInt(editTextHour.getText().toString());
                endMinutes = Integer.parseInt(editTextMinutes.getText().toString());;
                endSeconds = Integer.parseInt(editTextSeconds.getText().toString());

                converter = new Converter();
                long millis = converter.covertDataToMillis(endYear, endMonth, endDay, endHour, endMinutes, endSeconds);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(MILLIS, millis);
                startActivity(intent);
            }
        });


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
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