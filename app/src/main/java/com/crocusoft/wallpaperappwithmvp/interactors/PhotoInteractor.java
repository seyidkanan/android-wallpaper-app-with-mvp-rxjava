package com.crocusoft.wallpaperappwithmvp.interactors;


import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class PhotoInteractor {

    private ApiInterfaces apiService;

    private FetchingIdlingResource fetchingIdlingResource;

    public PhotoInteractor() {
        apiService = ApiInitRx.getClient()
                .create(ApiInterfaces.class);

    }

    public void setFetchingIdlingResource(FetchingIdlingResource fetchingIdlingResource) {
        this.fetchingIdlingResource = fetchingIdlingResource;
    }

    public void getDataFromRandomApi(DisposableSingleObserver disposableSingleObserver) {
        apiService.getRandomPhotosRX(
                Constant.CLIENT_ID,
                Constant.PAGINATION_ITEM_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (fetchingIdlingResource != null) {
                            fetchingIdlingResource.beginFetching();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (fetchingIdlingResource != null) {
                            fetchingIdlingResource.doneFetching();
                        }
                    }
                })
                .subscribe(disposableSingleObserver);
    }

    public void getSearchResultFromApi(String query, final int page, DisposableSingleObserver observer) {
        apiService.searchPhotosRX(
                Constant.CLIENT_ID,
                page,
                query,
                Constant.PAGINATION_ITEM_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (fetchingIdlingResource != null) {
                            fetchingIdlingResource.beginFetching();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (fetchingIdlingResource != null) {
                            fetchingIdlingResource.doneFetching();
                        }
                    }
                })
                .subscribe(observer);

    }

}
