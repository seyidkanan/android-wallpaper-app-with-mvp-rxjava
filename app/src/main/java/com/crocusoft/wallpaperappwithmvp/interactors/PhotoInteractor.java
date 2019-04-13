package com.crocusoft.wallpaperappwithmvp.interactors;


import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotoContractor;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class PhotoInteractor implements RandomPhotoContractor.Interactor {

    private ApiInterfaces apiService;

    public PhotoInteractor() {
        apiService = ApiInitRx.getClient()
                .create(ApiInterfaces.class);

    }

    @Override
    public void getDataFromRandomApi(DisposableSingleObserver disposableSingleObserver) {
        apiService.getRandomPhotosRX(
                Constant.CLIENT_ID,
                Constant.PAGINATION_ITEM_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableSingleObserver);
    }

    @Override
    public void getSearchResultFromApi(String query, final int page, DisposableSingleObserver observer) {
        apiService.searchPhotosRX(
                Constant.CLIENT_ID,
                page,
                query,
                Constant.PAGINATION_ITEM_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);

    }

}
