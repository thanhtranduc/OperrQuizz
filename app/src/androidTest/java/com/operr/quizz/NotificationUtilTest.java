package com.operr.quizz;

import android.content.Context;

import androidx.core.app.NotificationManagerCompat;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.operr.quizz.util.NotificationUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 * <p>
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4.class)
public class NotificationUtilTest {
    @Test
    public void testNotification() throws InterruptedException {
        Context activityRule = InstrumentationRegistry.getInstrumentation().getTargetContext();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(activityRule);
        managerCompat.cancelAll();
        NotificationUtil.showNotification(activityRule);
        assertNotNull(onView(withText(activityRule.getString(R.string.app_name))));
        Thread.sleep(5000);
        managerCompat.cancelAll();
    }
}
