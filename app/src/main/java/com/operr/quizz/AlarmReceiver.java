package com.operr.quizz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.operr.quizz.util.NotificationUtil;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null){
            NotificationUtil.showNotification(context);
        }
    }
}
