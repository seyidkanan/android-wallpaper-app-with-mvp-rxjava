package com.crocusoft.wallpaperappwithmvp.idlingResource;

import android.support.test.espresso.IdlingResource;

public class FetchingIdlingResource implements IdlingResource, FetcherListener {

    private boolean idle = true;

    private IdlingResource.ResourceCallback resourceCallback = null;

    @Override
    public String getName() {
        return FetchingIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    @Override
    public void doneFetching() {
        idle = true;
        resourceCallback.onTransitionToIdle();
    }

    @Override
    public void beginFetching() {
        idle = false;
    }
}
