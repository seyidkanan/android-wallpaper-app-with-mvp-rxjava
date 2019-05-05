package com.crocusoft.wallpaperappwithmvp.util;

import com.crocusoft.wallpaperappwithmvp.BaseScreenView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.ErrorPOJO;

public class HttpResponseHandlerUtil {

    public static void onAPIError(BaseScreenView view, ErrorPOJO errorPOJO, Throwable t) {
        if (view == null) {
            return;
        }
        view.hideProgress();

        if (errorPOJO != null) {
            StringBuilder builder = new StringBuilder();
            for (String errorText : errorPOJO.getErrors()) {
                builder.append(errorText).append(" ");
            }
            view.showErrorMessage(builder.toString());
            return;
        }

        if (t != null) {
            view.showErrorMessage(t.getMessage());
        }
    }


}
