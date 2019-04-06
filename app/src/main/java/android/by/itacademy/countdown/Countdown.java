package android.by.itacademy.countdown;

import android.os.CountDownTimer;

import java.util.Calendar;

public class Countdown extends CountDownTimer {


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Countdown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisUntilFinished);
        String date = "" + calendar.get(Calendar.DAY_OF_MONTH) + ":" + calendar.get(Calendar.MONTH) + ":" + calendar.get(Calendar.YEAR);
        String time = "" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);


//        long seconds = millisUntilFinished/1000;//convert to seconds
//        long minutes = seconds / 60;//convert to minutes
//        long hours = minutes / 60;//convert to hours
//
//        if(minutes > 0)//if we have minutes, then there might be some remainder seconds
//            seconds = seconds % 60;//seconds can be between 0-60, so we use the % operator to get the remainder
//        if(hours > 0)
//            minutes = minutes % 60;//similar to seconds

//        String time = formatNumber(hours) + ":" + formatNumber(minutes) + ":" +
//                formatNumber(seconds);
//        textViewCountDown.setText(time);//set value to text
    }

    @Override
    public void onFinish() {

    }

    private String formatYear(long year) {
        if (year < 10 ) {
            return "000" + year;
        } else if (year < 100 && year > 10){
            return "00" + year;
        }else {
            return "0" + year;
        }
    }

    private String formatNumber(long value){
        if(value < 10)
            return "0" + value;
        return value + "";
    }
}
