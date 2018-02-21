package lebo_production.currencyexchange;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class BO_ExchangeActivityTest {
    @Rule
    public ActivityTestRule<BO_ExchangeActivity> mActivityRule = new ActivityTestRule<>(
            BO_ExchangeActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void check_choose_date() throws Exception{
        onView(withId(R.id.nextButton)).perform(click());
        onView(withText(R.string.emptyDate)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void check_less_fteenth_year() throws Exception{
        onView(withId(R.id.tvDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2004,1,1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        onView(withText(R.string.incompleteDate)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @Test
    public void check_current_exchange() throws Exception{
        onView(withId(R.id.tvDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2003,1,1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.data)).perform(typeText("300"));
        onView(withId(R.id.calculate)).perform(click());
        onView(withId(R.id.viewResult)).check(matches(isDisplayed()));
    }
}