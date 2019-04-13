package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.BaseView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public interface RandomPhotoContractor {

    interface View extends BaseView<Presenter> {

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


    interface Interactor {

        void getDataFromRandomApi(DisposableSingleObserver observer);

        void getSearchResultFromApi(String query, int page, DisposableSingleObserver observer);

    }

}
