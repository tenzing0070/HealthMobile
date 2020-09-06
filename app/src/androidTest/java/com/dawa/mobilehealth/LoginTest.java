package com.dawa.mobilehealth;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dawa.mobilehealth.login.LoginActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity>
            testRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void login() {

        onView(withId(R.id.username)).perform(typeText("ninja123"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin))
                .check(matches(isDisplayed()));

    }
}