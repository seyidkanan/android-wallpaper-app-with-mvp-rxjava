package com.crocusoft.wallpaperappwithmvp.util;

import com.crocusoft.wallpaperappwithmvp.BaseScreenView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.ErrorPOJO;

public class HttpResponseHandlerUtil {

    public static void onAPIError(BaseScreenView view, ErrorPOJO errorPOJO, Throwable t) {
        if (view == null) {
            return;
        }

        if (errorPOJO != null) {
            handleErrorWithApiMessage(view, errorPOJO);
            return;
        }

        if (t != null) {
            handleThrowableError(view, t);
        }
    }

    private static void handleThrowableError(BaseScreenView view, Throwable t) {
        if (t != null) {
            view.showErrorMessage(t.getMessage());
        }
    }

    private static void handleErrorWithApiMessage(BaseScreenView view, ErrorPOJO errorPOJO) {
        StringBuilder builder = new StringBuilder();
        for (String errorText : errorPOJO.getErrors()) {
            builder.append(errorText).append(" ");
        }
        view.showErrorMessage(builder.toString());
    }

}
