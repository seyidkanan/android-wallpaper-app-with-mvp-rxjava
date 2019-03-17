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
    public void getDataFromRandomApi(final boolean isNeedClear) {
        if (apiCallBackInterfaces != null) {
            apiService.getRandomPhotosRX(
                    Constant.CLIENT_ID,
                    Constant.PAGINATION_ITEM_COUNT)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Gson gson = new Gson();
                            try {
                                String res = new Gson().toJson(o);
                                List<PhotoPOJO> photoPOJOS = gson.fromJson(res,
                                        new TypeToken<List<PhotoPOJO>>() {
                                        }.getType());
                                apiCallBackInterfaces.onRandomApiSuccess(photoPOJOS, isNeedClear);
                            } catch (Exception e) {
                                apiCallBackInterfaces.onAPIError(null, e);
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
                                        apiCallBackInterfaces.onAPIError(errorPOJO, e);
                                    } else {
                                        ErrorPOJO errorPOJO = gson.fromJson(body.string(), ErrorPOJO.class);
                                        apiCallBackInterfaces.onAPIError(errorPOJO, e);
                                    }
                                } else {
                                    apiCallBackInterfaces.onAPIError(null, e);
                                }
                            } catch (Exception ex) {
                                apiCallBackInterfaces.onAPIError(null, ex);
                                ex.printStackTrace();
                            }
                        }
                    });
        }
    }

    @Override
    public void getSearchResultFromApi(String query, final int page) {
        if (apiCallBackInterfaces != null) {
            apiService.searchPhotosRX(
                    Constant.CLIENT_ID,
                    page,
                    query,
                    Constant.PAGINATION_ITEM_COUNT)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Gson gson = new Gson();
                            try {
                                String res = new Gson().toJson(o);
                                SearchResponsePOJO searchResponsePOJO = gson.fromJson(res, SearchResponsePOJO.class);
                                apiCallBackInterfaces.onSearchResultSuccess(searchResponsePOJO, page);
                            } catch (Exception e) {
                                apiCallBackInterfaces.onAPIError(null, e);
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
                                    apiCallBackInterfaces.onAPIError(errorPOJO, e);
                                } else {
                                    apiCallBackInterfaces.onAPIError(null, e);
                                }
                            } catch (Exception ex) {
                                apiCallBackInterfaces.onAPIError(null, ex);
                                ex.printStackTrace();
                            }
                        }
                    });
        }
    }

}
