package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.SearchResponsePOJO;

import java.util.List;

public class RandomPhotoPresenter implements RandomPhotoContractor.Presenter, PhotoAPICallBackInterfces {

    private RandomPhotoContractor.View view;

    private RandomPhotoModel model;

    private int pageForPagination = 1;

    private String searchQuery = "";

    public RandomPhotoPresenter(RandomPhotoContractor.View view) {
        if (view == null) {
            return;
        }
        this.view = view;
        view.setPresenter(this);
    }

    private void checkInitModel() {
        if (model == null) {
            model = new RandomPhotoModel(this);
        }
    }


    @Override
    public void fetchRandomData(boolean isWithPagination) {
        if (view == null) {
            return;
        }
        view.showProgress();
        checkInitModel();
        searchQuery = "";
        model.getDataFromRandomApi(isWithPagination);
        pageForPagination = 1;
    }

    @Override
    public void searchTextEntered(String query) {
        if (view == null) {
            return;
        }
        checkInitModel();
        searchQuery = query;
        model.getSearchResultFromApi(query, pageForPagination);
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
                        pageForPagination++;
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
            model.getSearchResultFromApi(searchQuery, pageForPagination);
        }
    }

}



