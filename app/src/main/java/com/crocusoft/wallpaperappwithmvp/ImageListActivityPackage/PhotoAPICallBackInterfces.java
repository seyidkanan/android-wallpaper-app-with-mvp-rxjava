package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.pojo.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.SearchResponsePOJO;

import java.util.List;

public interface PhotoAPICallBackInterfces {

    void onRandomApiSuccess(List<PhotoPOJO> photoPOJOS, boolean isNeedClear);

    void onAPIError(ErrorPOJO o, Throwable t);

    void onSearchResultSuccess(SearchResponsePOJO searchResponsePOJO, int page);

}
