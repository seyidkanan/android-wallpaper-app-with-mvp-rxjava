package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.BaseScreenView;
import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.data.api.BaseSubscriber;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;
import com.crocusoft.wallpaperappwithmvp.interactors.PhotoInteractor;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.SearchResponsePOJO;
import com.crocusoft.wallpaperappwithmvp.util.HttpResponseHandlerUtil;
import com.crocusoft.wallpaperappwithmvp.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class RandomPhotoPresenter implements RandomPhotoContractor.Presenter {

    private RandomPhotoContractor.ScreenView view;

    private PhotoInteractor interactor;

    private int pageForSearchAPIPagination = 1;

    private String searchQuery = "";

    private FetchingIdlingResource fetchingIdlingResource;

    public RandomPhotoPresenter(RandomPhotoContractor.ScreenView view) {
        if (view == null) {
            return;
        }
        this.view = view;
    }

    private void checkInitModel() {
        if (interactor == null) {
            interactor = new PhotoInteractor();
            interactor.setFetchingIdlingResource(fetchingIdlingResource);
        }
    }


    @Override
    public void fetchRandomData(boolean isNeedClear) {
        if (view == null) {
            return;
        }

        if (!Util.isConnected(view.getContext())) {
            view.showErrorView(view.getContext().getString(R.string.no_internet_connection));
            return;
        }

        view.showProgress();
        checkInitModel();
        searchQuery = "";

        interactor.getDataFromRandomApi(new RandomPhotoObserver(isNeedClear, view));
        pageForSearchAPIPagination = 1;
    }

    @Override
    public void searchTextEntered(String query) {
        if (view == null) {
            return;
        }
        checkInitModel();
        searchQuery = query;
        interactor.getSearchResultFromApi(query, pageForSearchAPIPagination, new SearchPhotoObserver(pageForSearchAPIPagination));
    }

    public void onRandomApiSuccess(List<PhotoPOJO> photoPOJOS, boolean isNeedClear) {
        if (view == null) {
            return;
        }
        view.hideProgress();
        view.onDataFetch(photoPOJOS, isNeedClear);
    }

    public void onSearchResultSuccess(SearchResponsePOJO searchResponsePOJO, int page) {
        if (view == null) {
            return;
        }
        try {
            if (searchResponsePOJO != null) {
                if (searchResponsePOJO.getResults() != null) {
                    if (searchResponsePOJO.getResults().size() > 0) {
                        pageForSearchAPIPagination++;
                        onRandomApiSuccess(searchResponsePOJO.getResults(), page == 1);
                    } else {
                        view.showErrorMessage(view.getContext().getString(R.string.empty_result));
                    }
                } else {
                    view.showErrorMessage(view.getContext().getString(R.string.empty_result));
                }
            }
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void loadMoreSearchResult() {
        if (view == null) {
            return;
        }
        if (searchQuery.length() > 0) {
            checkInitModel();
            interactor.setFetchingIdlingResource(fetchingIdlingResource);
            interactor.getSearchResultFromApi(searchQuery, pageForSearchAPIPagination, new SearchPhotoObserver(pageForSearchAPIPagination));
        }
    }

    private class RandomPhotoObserver extends BaseSubscriber<Object> {

        private boolean isNeedClear;

        public RandomPhotoObserver(boolean isNeedClear, BaseScreenView view) {
            super(view);
            this.isNeedClear = isNeedClear;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            super.onNext(o);
            Gson gson = new Gson();
            try {
                String res = new Gson().toJson(o);
                List<PhotoPOJO> photoPOJOS = gson.fromJson(res,
                        new TypeToken<List<PhotoPOJO>>() {
                        }.getType());
                onRandomApiSuccess(photoPOJOS, isNeedClear);
            } catch (Exception e) {
                HttpResponseHandlerUtil.onAPIError(view, null, e);
                e.printStackTrace();
            }
        }
    }

    private class SearchPhotoObserver extends BaseSubscriber<Object> {

        private int page;

        public SearchPhotoObserver(int page) {
            super(view);
            this.page = page;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object response) {
            try {
                SearchResponsePOJO searchResponsePOJO = parseToSearchResponsePOJO(response);
                onSearchResultSuccess(searchResponsePOJO, page);
            } catch (Exception e) {
                HttpResponseHandlerUtil.onAPIError(view, null, e);
                e.printStackTrace();
            }
        }
    }

    private SearchResponsePOJO parseToSearchResponsePOJO(Object response) {
        Gson gson = new Gson();
        String res = new Gson().toJson(response);
        return gson.fromJson(res, SearchResponsePOJO.class);
    }


    public void setFetchingIdlingResource(FetchingIdlingResource fetchingIdlingResource) {
        this.fetchingIdlingResource = fetchingIdlingResource;
        interactor.setFetchingIdlingResource(fetchingIdlingResource);
    }
}



