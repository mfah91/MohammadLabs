package algonquin.cst2335.mohammadl041008763;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class MainActivityTest {
@Rule
public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFindMissingUpperCase(){
        // find the view
        ViewInteraction appCompatEditText = onView (withId(R.id.editText));
        // type is password123#$*"
        appCompatEditText.perform(replaceText("password123#$*"));
        //find the button
        ViewInteraction materialButton = onView (withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));


    }
    @Test
    public void testFindMissinglowerCase(){
        // find the view
        ViewInteraction appCompatEditText = onView (withId(R.id.editText));

        appCompatEditText.perform(replaceText("PASS123#$*"));
        //find the button
        ViewInteraction materialButton = onView (withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));


    }
    @Test
    public void testFindMissingNumberCase(){
        // find the view
        ViewInteraction appCompatEditText = onView (withId(R.id.editText));

        appCompatEditText.perform(replaceText("password#$*"));
        //find the button
        ViewInteraction materialButton = onView (withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));


    }
    @Test
    public void testFindMissingSpecialCase(){
        // find the view
        ViewInteraction appCompatEditText = onView (withId(R.id.editText));

        appCompatEditText.perform(replaceText("passwordA22"));
        //find the button
        ViewInteraction materialButton = onView (withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));


    }
    @Test
    public void testFindmatchingCase(){
        // find the view
        ViewInteraction appCompatEditText = onView (withId(R.id.editText));

        appCompatEditText.perform(replaceText("passwordA22*"));
        //find the button
        ViewInteraction materialButton = onView (withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("Your password meets the requirements")));


    }


}
