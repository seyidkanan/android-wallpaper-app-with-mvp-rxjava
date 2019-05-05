package com.crocusoft.wallpaperappwithmvp.interactors;


import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class PhotoInteractor extends BaseInteractor {

    private ApiInterfaces apiService;

    private FetchingIdlingResource fetchingIdlingResource;

    public PhotoInteractor() {
        apiService = ApiInitRx.getClient()
                .create(ApiInterfaces.class);

    }

    public void setFetchingIdlingResource(FetchingIdlingResource fetchingIdlingResource) {
        this.fetchingIdlingResource = fetchingIdlingResource;
    }

    public void getDataFromRandomApi(Observer<Object> observer) {
        apiService.getRandomPhotosRX(Constant.CLIENT_ID, Constant.PAGINATION_ITEM_COUNT)
                .compose(baseCall())
                .doOnSubscribe(disposable -> {
                    if (fetchingIdlingResource != null) {
                        fetchingIdlingResource.beginFetching();
                    }
                })
                .doFinally(() -> {
                    if (fetchingIdlingResource != null) {
                        fetchingIdlingResource.doneFetching();
                    }
                })
                .subscribe(observer);
    }

    public void getSearchResultFromApi(String query, final int page, Observer<Object> observer) {
        apiService.searchPhotosRX(Constant.CLIENT_ID, page, query, Constant.PAGINATION_ITEM_COUNT)
                .compose(baseCall())
                .doOnSubscribe(disposable -> {
                    if (fetchingIdlingResource != null) {
                        fetchingIdlingResource.beginFetching();
                    }
                })
                .doFinally(() -> {
                    if (fetchingIdlingResource != null) {
                        fetchingIdlingResource.doneFetching();
                    }
                })
                .subscribe(observer);

    }

}
