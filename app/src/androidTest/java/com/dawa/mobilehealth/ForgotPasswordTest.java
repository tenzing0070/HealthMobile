package com.dawa.mobilehealth;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dawa.mobilehealth.login.ForgotPasswordActivity;

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
public class ForgotPasswordTest {
    @Rule
    public ActivityTestRule<ForgotPasswordActivity>
            testRule = new ActivityTestRule<>(ForgotPasswordActivity.class);

    @Test
    public void password() {
        onView(withId(R.id.etfpemail)).perform(typeText("dawa@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.btnfpsubmit))
                .check(matches(isDisplayed()));
    }
}
