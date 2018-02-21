package lebo_production.currencyexchange;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.*;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<BO_MainActivity> mActivityRule = new ActivityTestRule<>(
            BO_MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
        BO_RandomGenerator.setRandomPassword(null);
    }

    @Test
    public void should_check_empty_guest_name() throws Exception {
        BO_RandomGenerator.setRandomPassword("1234");
        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.confirmation)).perform(typeText("1234"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.guestName)).perform(click());
        onView(withText(R.string.error))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void should_check_empty_confirm_password() throws Exception {
        BO_RandomGenerator.setRandomPassword("1234");
        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.guestName)).perform(typeText("Lena"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.confirmation)).perform(click());
        onView(withText(R.string.error))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void should_open_second_activity() throws Exception{
        BO_RandomGenerator.setRandomPassword("1234");
        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.guestName)).perform(typeText("Lena"));
        onView(withId(R.id.confirmation)).perform(typeText("1234"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.greetings)).check(matches(isDisplayed()));
    }

}