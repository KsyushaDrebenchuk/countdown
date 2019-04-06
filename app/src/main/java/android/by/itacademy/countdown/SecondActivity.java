package android.by.itacademy.countdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class SecondActivity extends Activity {

    private CountDownTimer countDownTimer;

    private TextView textViewYear;
    private TextView textViewMonth;
    private TextView textViewDay;
    private TextView textViewHour;
    private TextView textViewMinutes;
    private TextView textViewSeconds;
    private Button buttonDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        textViewYear = findViewById(R.id.textViewYearSecond);
        textViewMonth = findViewById(R.id.textViewMonthSecond);
        textViewDay = findViewById(R.id.textViewDaySecond);
        textViewHour = findViewById(R.id.textViewHourSecond);
        textViewMinutes = findViewById(R.id.textViewMinutesSecond);
        textViewSeconds = findViewById(R.id.textViewSecondsSecond);

        buttonDelete = findViewById(R.id.buttonDeleteEvent);

        Intent intent = getIntent();
        long millis = intent.getLongExtra(MainActivity.MILLIS, 0);

        startCountDownTimer(millis, 1);


    }


    private void startCountDownTimer(long duration, long interval) {
        countDownTimer = new CountDownTimer(duration, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.setTimeInMillis(millisUntilFinished);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                int seconds = calendar.get(Calendar.SECOND);


                textViewYear.setText(formatYear(year));
                textViewMonth.setText(formatNumber(month));
                textViewDay.setText(formatNumber(day));
                textViewHour.setText(formatNumber(hour));
                textViewMinutes.setText(formatNumber(minutes));
                textViewSeconds.setText(formatNumber(seconds));


            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();
    }


    private String formatYear(long year) {
        if (year < 10) {
            return "000" + year;
        } else if (year < 100 && year > 10) {
            return "00" + year;
        } else {
            return "0" + year;
        }
    }

    private String formatNumber(long value) {
        if (value < 10)
            return "0" + value;
        return value + "";
    }
}
