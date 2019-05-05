package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.BaseScreenView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;

import java.util.List;

public interface RandomPhotoContractor {

    interface ScreenView extends BaseScreenView {

        void showSuccessMessage(String message);

        void onDataFetch(List<PhotoPOJO> photoPOJO, boolean isNeedClear);

    }

    interface Presenter {

        void fetchRandomData(boolean isNeedClear);

        void searchTextEntered(String query);

        void loadMoreSearchResult();

    }

}
