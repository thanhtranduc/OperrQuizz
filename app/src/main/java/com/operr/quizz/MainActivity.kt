package com.operr.quizz

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * In this project I using alarmManager to control push notification because WorkManage namely
 * PeriodicWorkRequest work has a minimum interval of 15 minutes
 * {@link https://developer.android.com/reference/androidx/work/PeriodicWorkRequest}
 *
 * NOTE: This app don't run notification when device restart because this config has not implemented
 *
 * **/

class MainActivity : AppCompatActivity() {

    companion object {
        const val FIVE_MINUTE: Long = 1000 * 60 * 5
    }

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java).apply {
            setPackage(application.packageName)
        }
        val isWorking =
            (PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_NO_CREATE) != null)
        if (!isWorking) {
            alarmIntent =
                PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmMgr?.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + FIVE_MINUTE,
                FIVE_MINUTE,
                alarmIntent
            )
        } else {
            Toast.makeText(this, "exxist    ", Toast.LENGTH_LONG).show()
        }
    }
}
