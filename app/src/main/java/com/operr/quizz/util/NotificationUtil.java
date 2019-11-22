package com.operr.quizz.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.operr.quizz.MainActivity;
import com.operr.quizz.R;

public class NotificationUtil {
    private static String CHANNEL_ID = "NotificationWorker";

    /**
     * Best practise set up notification follow to google developer
     * link ref: https://developer.android.com/training/notify-user/build-notification#java
     */
    public static void showNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setColor(context.getResources().getColor(R.color.colorAccent))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                        BitmapFactory.decodeResource(
                                context.getResources(),
                                R.drawable.ic_launcher_foreground
                        )
                )
                .setContentTitle("OPERR Driver")
                .setContentText("You are on the break...")
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                                .bigText("You are on the break")
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        createNotificationChannel(context);

        // notificationId is a unique int for each notification that you must define
        Long notificationId = System.currentTimeMillis();
        notificationManager.notify(notificationId.intValue(), builder.build());
    }

    /**
     * Before you can deliver the notification on Android 8.0 and higher,
     * you must register your app's notification channel with the system by passing an instance of
     * NotificationChannel to createNotificationChannel()
     */
    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "name_channel";
            String description = "description_channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
