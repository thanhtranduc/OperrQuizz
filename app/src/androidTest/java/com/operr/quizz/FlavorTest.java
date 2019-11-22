package com.operr.quizz;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4.class)
public class FlavorTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testTextViewValue() throws Exception {
        Thread.sleep(2000);
        String suffix = activityTestRule.getActivity().getApplicationContext().getPackageName().replace("com.operr.quizz.", "");
        switch (suffix) {
            case "dev":
                onView(withId(R.id.container)).check(matches(withText("Developer")));
                break;

            case "prod":
                onView(withId(R.id.container)).check(matches(withText("Production")));
                break;

            case "qa":
                onView(withId(R.id.container)).check(matches(withText("QA")));
                break;

            default:
                throw new Exception("not exist view, build wrong type.");
        }

    }
}
