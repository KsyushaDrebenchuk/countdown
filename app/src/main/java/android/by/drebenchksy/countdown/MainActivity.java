package android.by.drebenchksy.countdown;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;

public class MainActivity extends Activity {
    private AdView mAdView;

    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinutes;
    private int endSeconds;
    private String actionName;

    private EditText editTextYear;
    private EditText editTextMonth;
    private EditText editTextDay;
    private EditText editTextHour;
    private EditText editTextMinutes;
    private EditText editTextSeconds;
    private EditText editTextActionName;
    private Button buttonSave;
    private Button buttonSeeCoundown;

    public static final String MILLIS_INSTALLED = "MillisInstalled";
    public static final String ACTION_NAME = "ActionName";
    public static final String STORAGE_NAME = "CountdownStorage";

    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntentEvent;
    private long diffForAlarm;

    public static SharedPreferences dateStorage;
    private SharedPreferences.Editor editor;

    private long millis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextYear = findViewById(R.id.editTextYearMain);
        editTextMonth = findViewById(R.id.editTextMonthMain);
        editTextDay = findViewById(R.id.editTextDayMain);
        editTextHour = findViewById(R.id.editTextHourMain);
        editTextMinutes = findViewById(R.id.editTextMinutesMain);
        editTextSeconds = findViewById(R.id.editTextSecondsMain);
        editTextActionName = findViewById(R.id.actionName);
        buttonSave = findViewById(R.id.buttonSaveEvent);
        buttonSeeCoundown = findViewById(R.id.buttonSeeCountdown);


        dateStorage = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = dateStorage.edit();


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextYear.getText().toString().equals("")) {
                    endYear = Integer.parseInt(editTextYear.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextMonth.getText().toString().equals("")) {
                    endMonth = Integer.parseInt(editTextMonth.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextDay.getText().toString().equals("")) {
                    endDay = Integer.parseInt(editTextDay.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextHour.getText().toString().equals("")) {
                    endHour = Integer.parseInt(editTextHour.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextMinutes.getText().toString().equals("")) {
                    endMinutes = Integer.parseInt(editTextMinutes.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextSeconds.getText().toString().equals("")) {
                    endSeconds = Integer.parseInt(editTextSeconds.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                actionName = editTextActionName.getText().toString();

                Calendar calendarSet = Calendar.getInstance();

                calendarSet.set(endYear, endMonth - 1, endDay, endHour, endMinutes, endSeconds);
                millis = calendarSet.getTimeInMillis();

                if (millis > System.currentTimeMillis()) {
                    diffForAlarm = millis - System.currentTimeMillis();
                    eventNotification(diffForAlarm);

                    Log.e("FFF", "Millis installed = " + millis);
                    editor.putLong(MILLIS_INSTALLED, millis);
                    editor.putString(ACTION_NAME, actionName);
                    editor.apply();

                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Невозможно задать обратный отсчет", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSeeCoundown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
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

    private void eventNotification(long diff) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimeNotification.class);
        pendingIntentEvent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntentEvent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + diff, pendingIntentEvent);
    }
}