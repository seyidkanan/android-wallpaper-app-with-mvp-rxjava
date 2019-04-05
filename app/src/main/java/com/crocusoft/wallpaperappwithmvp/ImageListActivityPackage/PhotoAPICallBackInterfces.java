package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.pojo.response.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.SearchResponsePOJO;

import java.util.List;

public interface PhotoAPICallBackInterfces {

    void onRandomApiSuccess(List<PhotoPOJO> photoPOJOS, boolean isNeedClear);

    void onAPIError(ErrorPOJO o, Throwable t);

    void onSearchResultSuccess(SearchResponsePOJO searchResponsePOJO, int page);

}
