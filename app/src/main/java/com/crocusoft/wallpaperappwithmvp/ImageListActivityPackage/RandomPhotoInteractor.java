package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;


import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class RandomPhotoInteractor implements RandomPhotoContractor.Model {

    private ApiInterfaces apiService;

    public RandomPhotoInteractor() {
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
