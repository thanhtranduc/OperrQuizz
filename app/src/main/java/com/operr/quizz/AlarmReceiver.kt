package com.operr.quizz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private val CHANNEL_ID: String = "test.NotificationWorker"
        fun showNotify(applicationContext: Context) {
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(applicationContext, 0, intent, 0)
            val currentDate = SimpleDateFormat("MM-dd-HH_mm", Locale.getDefault()).format(Date())
            val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setColor(applicationContext.resources.getColor(R.color.colorAccent))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_launcher_foreground
                    )
                )
                .setContentText("$currentDate content .......")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("$currentDate Much longer text that cannot fit one line...")
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannel(notificationManager)
            val notificationId = System.currentTimeMillis().toInt()
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notificationId, builder.build())
        }

        private fun createNotificationChannel(notificationManager: NotificationManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "applicationContext.getString(R.string.channel_name)"
                val descriptionText = "applicationContext.getString(R.string.channel_description)"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            showNotify(context)
        }
    }

}