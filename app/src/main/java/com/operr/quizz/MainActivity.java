package com.operr.quizz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final Integer FIVE_MINUTE = 1000 * 60 * 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setPackage(getApplication().getPackageName());
        boolean isWorking = (PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_NO_CREATE) != null);

        if (!isWorking) {
            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmMgr.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + FIVE_MINUTE,
                    FIVE_MINUTE,
                    alarmIntent);
        } else {
            Toast.makeText(this, "exist", Toast.LENGTH_LONG).show();
        }
    }
}
