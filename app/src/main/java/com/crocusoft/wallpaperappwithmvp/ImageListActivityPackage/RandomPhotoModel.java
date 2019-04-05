package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;


import com.crocusoft.wallpaperappwithmvp.data.api.ApiInitRx;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.pojo.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.SearchResponsePOJO;
import com.crocusoft.wallpaperappwithmvp.util.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class RandomPhotoModel implements RandomPhotoContractor.Model {

    private PhotoAPICallBackInterfces apiCallBackInterfaces;

    private ApiInterfaces apiService;

    public RandomPhotoModel(PhotoAPICallBackInterfces apiCallBackInterfaces) {
        this.apiCallBackInterfaces = apiCallBackInterfaces;
        apiService = ApiInitRx.getClient()
                .create(ApiInterfaces.class);

    }

    @Override
    public void getDataFromRandomApi(DisposableSingleObserver disposableSingleObserver) {
        if (apiCallBackInterfaces != null) {
            apiService.getRandomPhotosRX(
                    Constant.CLIENT_ID,
                    Constant.PAGINATION_ITEM_COUNT)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
        }
    }

    @Override
    public void getSearchResultFromApi(String query, final int page, DisposableSingleObserver observer) {
        if (apiCallBackInterfaces != null) {
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

}
