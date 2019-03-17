package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.content.Context;

import com.crocusoft.wallpaperappwithmvp.BaseView;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;

import java.util.List;

public interface RandomPhotoContractor {

    interface View extends BaseView<Presenter> {

        void showErrorMessage(String errorMessage);

        void showSuccessMessage(String message);

        void showProgress();

        void hideProgress();

        void onDataFetch(List<PhotoPOJO> photoPOJO, boolean isNeedClear);

    }

    interface Presenter {

        void fetchRandomData(boolean isNeedClear);

        void searchTextEntered(String query);

        void loadMoreSearchResult();

    }


    interface Model {

        void getDataFromRandomApi(boolean isNeedClear);

        void getSearchResultFromApi(String query, int page);

    }

}
