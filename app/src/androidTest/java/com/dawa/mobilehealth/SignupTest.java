package com.dawa.mobilehealth;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dawa.mobilehealth.login.SignupActivity;

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
public class SignupTest {
    @Rule
    public ActivityTestRule<SignupActivity>
            testRule = new ActivityTestRule<>(SignupActivity.class);
    @Test
    public void signUp() {
        onView(withId(R.id.txtfirstname)).perform(typeText("Dawa"), closeSoftKeyboard());
        onView(withId(R.id.txtlastname)).perform(typeText("Sherpa"), closeSoftKeyboard());
        onView(withId(R.id.txtaddress)).perform(typeText("Kathmandu"), closeSoftKeyboard());
        onView(withId(R.id.txtage)).perform(typeText("25"), closeSoftKeyboard());
        onView(withId(R.id.txtgender)).perform(typeText("Male"), closeSoftKeyboard());
        onView(withId(R.id.txtphone)).perform(typeText("9839493832"), closeSoftKeyboard());
        onView(withId(R.id.txtemail)).perform(typeText("dawa@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.txtweight)).perform(typeText("75"), closeSoftKeyboard());
        onView(withId(R.id.txtheight)).perform(typeText("180"), closeSoftKeyboard());
        onView(withId(R.id.txtbloodgroup)).perform(typeText("0+"), closeSoftKeyboard());
        onView(withId(R.id.txtusername)).perform(typeText("dawa123"), closeSoftKeyboard());
        onView(withId(R.id.txtpassword)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.etConfirmPassword)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btnsignup))
                .check(matches(isDisplayed()));
    }
}
