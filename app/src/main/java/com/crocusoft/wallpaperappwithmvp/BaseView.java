package com.crocusoft.wallpaperappwithmvp;

import android.app.Activity;
import android.content.Context;

public interface BaseView {

    Context getContext();

    Activity getActivity();

    void showErrorView(String message);

    void showErrorMessage(String errorMessage);

}
