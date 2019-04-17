package android.by.drebenchksy.countdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SecondActivity extends Activity {

    private CountDownTimer countDownTimer;

    private TextView textViewDay;
    private TextView textViewHour;
    private TextView textViewMinutes;
    private TextView textViewSeconds;
    private TextView textViewActionName;
    private Button buttonDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewDay = findViewById(R.id.dayTextView);
        textViewHour = findViewById(R.id.hourTextView);
        textViewMinutes = findViewById(R.id.minTextView);
        textViewSeconds = findViewById(R.id.secTextView);
        textViewActionName = findViewById(R.id.textViewActionName);

        buttonDelete = findViewById(R.id.buttonDeleteEvent);

        Log.e("FFF", "onCreate");

        buttonDelete.setOnClickListener(buttonDeleteClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        long millis = MainActivity.dateStorage.getLong(MainActivity.MILLIS_INSTALLED, 0);
        String actionName = MainActivity.dateStorage.getString(MainActivity.ACTION_NAME, null);

        if (actionName != null) {
            textViewActionName.setText(actionName);
        } else {
            textViewActionName.setText("Название события не задано");
        }

        if (millis > 0) {
            long diff = millis - System.currentTimeMillis();
            startCountDownTimer(diff, 1);
        } else {
            textViewDay.setText("00");
            textViewHour.setText("00");
            textViewMinutes.setText("00");
            textViewSeconds.setText("00");
        }
    }

    private void startCountDownTimer(final long duration, long interval) {
        countDownTimer = new CountDownTimer(duration, interval) {

            @Override
            public void onTick(long millisUntilFinished) {

                long day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                long hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(day);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                textViewDay.setText(formatNumber(day));
                textViewHour.setText(formatNumber(hour));
                textViewMinutes.setText(formatNumber(minutes));
                textViewSeconds.setText(formatNumber(seconds));
            }

            @Override
            public void onFinish() {
                textViewActionName.setText("Название события не задано");
                textViewDay.setText("00");
                textViewHour.setText("00");
                textViewMinutes.setText("00");
                textViewSeconds.setText("00");
            }
        };
        countDownTimer.start();
    }

    private String formatNumber(long value) {
        if (value < 10)
            return "0" + value;
        return value + "";
    }

    private View.OnClickListener buttonDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            countDownTimer.cancel();
            MainActivity.dateStorage.edit().clear().apply();
            MainActivity.alarmManager.cancel(MainActivity.pendingIntentEvent);
            startActivity(new Intent(SecondActivity.this, MainActivity.class));
        }
    };
}