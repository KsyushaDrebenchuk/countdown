package android.by.drebenchksy.countdown;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class TimeNotification extends BroadcastReceiver {

    public final static String NOTIFY_ID = "NotifyTimeIsUp";
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(NOTIFY_ID, "ChannelEvent", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(context, EventNotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFY_ID);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.outline_event_available_24px)
                .setContentTitle("Обратный отсчет")
                .setContentText("Время вышло")
                .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }
}