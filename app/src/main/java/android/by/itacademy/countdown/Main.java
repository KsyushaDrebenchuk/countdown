package android.by.itacademy.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class Main extends Activity {

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
    }
}
