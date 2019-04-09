package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.response.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.SearchResponsePOJO;
import com.crocusoft.wallpaperappwithmvp.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class RandomPhotoPresenter implements RandomPhotoContractor.Presenter, PhotoAPICallBackInterfces {

    private RandomPhotoContractor.View view;

    private RandomPhotoInteractor interactor;

    private int pageForSearchAPIPagination = 1;

    private String searchQuery = "";

    public RandomPhotoPresenter(RandomPhotoContractor.View view) {
        if (view == null) {
            return;
        }
        this.view = view;
        view.setPresenter(this);
    }

    private void checkInitModel() {
        if (interactor == null) {
            interactor = new RandomPhotoInteractor();
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
        interactor.getDataFromRandomApi(new RandomPhotoSingleObserver(isNeedClear));
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

    @Override
    public void onRandomApiSuccess(List<PhotoPOJO> photoPOJOS, boolean isNeedClear) {
        if (view == null) {
            return;
        }
        view.hideProgress();
        view.onDataFetch(photoPOJOS, isNeedClear);
    }

    @Override
    public void onAPIError(ErrorPOJO errorPOJO, Throwable t) {
        if (view == null) {
            return;
        }
        view.hideProgress();

        if (errorPOJO != null) {
            StringBuilder builder = new StringBuilder();
            for (String errorText : errorPOJO.getErrors()) {
                builder.append(errorText).append(" ");
            }
            view.showErrorMessage(builder.toString());
            return;
        }

        if (t != null) {
            view.showErrorMessage(t.getMessage());
        }
    }


    @Override
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
            interactor.getSearchResultFromApi(searchQuery, pageForSearchAPIPagination, new SearchPhotoObserver(pageForSearchAPIPagination));
        }
    }

    private class RandomPhotoSingleObserver extends DisposableSingleObserver<Object> {

        private boolean isNeedClear;

        public RandomPhotoSingleObserver(boolean isNeedClear) {
            this.isNeedClear = isNeedClear;
        }

        @Override
        public void onSuccess(Object o) {
            Gson gson = new Gson();
            try {
                String res = new Gson().toJson(o);
                List<PhotoPOJO> photoPOJOS = gson.fromJson(res,
                        new TypeToken<List<PhotoPOJO>>() {
                        }.getType());
                onRandomApiSuccess(photoPOJOS, isNeedClear);
            } catch (Exception e) {
                onAPIError(null, e);
                e.printStackTrace();
            }

        }

        @Override
        public void onError(Throwable e) {
            try {
                Gson gson = new Gson();
                if (e instanceof HttpException) {
                    Response response = ((HttpException) e).response();
                    ResponseBody body = response.errorBody();
                    if (response.code() == 403) {
                        ErrorPOJO errorPOJO = new ErrorPOJO();
                        List<String> errors = new ArrayList<>();
                        errors.add(response.errorBody().string());
                        errorPOJO.setErrors(errors);
                        onAPIError(errorPOJO, e);
                    } else {
                        ErrorPOJO errorPOJO = gson.fromJson(body.string(), ErrorPOJO.class);
                        onAPIError(errorPOJO, e);
                    }
                } else {
                    onAPIError(null, e);
                }
            } catch (Exception ex) {
                onAPIError(null, ex);
                ex.printStackTrace();
            }
        }
    }

    private class SearchPhotoObserver extends DisposableSingleObserver<Object> {

        private int page;

        public SearchPhotoObserver(int page) {
            this.page = page;
        }

        @Override
        public void onSuccess(Object response) {
            try {
                SearchResponsePOJO searchResponsePOJO = parseToSearchResponsePOJO(response);
                onSearchResultSuccess(searchResponsePOJO, page);
            } catch (Exception e) {
                onAPIError(null, e);
                e.printStackTrace();
            }

        }

        @Override
        public void onError(Throwable e) {
            try {
                Gson gson = new Gson();
                if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    ErrorPOJO errorPOJO = gson.fromJson(body.string(), ErrorPOJO.class);
                    onAPIError(errorPOJO, e);
                } else {
                    onAPIError(null, e);
                }
            } catch (Exception ex) {
                onAPIError(null, ex);
                ex.printStackTrace();
            }
        }
    }

    private SearchResponsePOJO parseToSearchResponsePOJO(Object response) throws Exception {
        Gson gson = new Gson();
        String res = new Gson().toJson(response);
        SearchResponsePOJO searchResponsePOJO = gson.fromJson(res, SearchResponsePOJO.class);
        return searchResponsePOJO;
    }

}



