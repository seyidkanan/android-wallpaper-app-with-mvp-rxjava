package com.crocusoft.wallpaperappwithmvp.interactors;


import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import io.reactivex.Observer;


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
