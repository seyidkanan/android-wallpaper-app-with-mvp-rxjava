package com.crocusoft.wallpaperappwithmvp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotosActivity;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestForSearchFlow {

    @Rule
    public ActivityTestRule<RandomPhotosActivity> activityActivityTestRule
            = new ActivityTestRule<>(RandomPhotosActivity.class, false, false);

    private FetchingIdlingResource fetchingIdlingResource = new FetchingIdlingResource();

    @Before
    public void setup() {
        activityActivityTestRule.launchActivity(null);
        IdlingRegistry.getInstance().register(fetchingIdlingResource);
        activityActivityTestRule.getActivity().setFetcherListener(fetchingIdlingResource);
    }

    @Test
    public void testIdleResourcing() {
        onView(withId(R.id.editTextSearch)).check(VisibilityAssertion.isVisible());
        onView(withId(R.id.editTextSearch)).perform(typeText("mountain"), pressImeActionButton());
        onView(withId(R.id.recyclerView)).check(new RecyclerViewItemCountAssertion(10));
        onView(withId(R.id.progressBar)).check(VisibilityAssertion.isGone());
    }

    @After
    public void onFinish() {
        IdlingRegistry.getInstance().unregister(fetchingIdlingResource);
    }
}
