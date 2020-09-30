package com.dawa.mobilehealth;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.dawa.mobilehealth.DoctorDetailActivity;
import com.dawa.mobilehealth.R;

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
public class DoctorAppointmentTest {
    @Rule
    public ActivityTestRule<DoctorDetailActivity>
            testRule = new ActivityTestRule<>(DoctorDetailActivity.class);

    @Test
    public void appointment() {
        onView(withId(R.id.txtPurpose)).perform(typeText("Migrane"), closeSoftKeyboard());
        onView(withId(R.id.etDate)).perform(typeText("14/02/2020"), closeSoftKeyboard());
        onView(withId(R.id.etTime)).perform(typeText("15:30"), closeSoftKeyboard());

        onView(withId(R.id.btncontinue_booking))
                .check(matches(isDisplayed()));
    }
}
