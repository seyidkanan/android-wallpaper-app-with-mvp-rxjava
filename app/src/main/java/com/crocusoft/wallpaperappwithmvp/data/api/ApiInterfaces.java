package com.crocusoft.wallpaperappwithmvp.data.api;

import com.squareup.okhttp.ResponseBody;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaces {

    String CLIENT_ID = "client_id";//9ae57af26040030499057b98f370bb7b67f57c770b72423a12b8ad5f275b617f
    String COUNT = "count";//10
    String ORIENTATION = "orientation";//squarish, landscape, portrait
    String PAGE = "page";
    String QUERY = "query";
    String PER_PAGE = "per_page";


    //Header Accept-Version: v1
    //  https://api.unsplash.com/photos/random
    //https://api.unsplash.com/photos/?client_id=9ae57af26040030499057b98f370bb7b67f57c770b72423a12b8ad5f275b617f

    /*
     * 401 Unauthorized
     * 422 Unprocessable Entity
     * */
    @Headers(value = "Accept-Version: v1")
    @GET("photos/random")
    Call<Object> getRandomImages(
            @Query(CLIENT_ID) String clientId,
            @Query(COUNT) int count,
            @Query(ORIENTATION) String orientation
    );

    @GET("photos/random")
    Single<Object> getRandomPhotosRX(
            @Query(CLIENT_ID) String clientId,
            @Query(COUNT) int count
    );


    @GET("search/photos")
    Single<Object> searchPhotosRX(
            @Query(CLIENT_ID) String clientId,
            @Query(PAGE) int page,
            @Query(QUERY) String query,
            @Query(PER_PAGE) int perPage

    );

    //for download photo
    //https://api.unsplash.com/photos/rXXSIr8-f9w/download?client_id=9ae57af26040030499057b98f370bb7b67f57c770b72423a12b8ad5f275b617f
    /*
     * 401 Unauthorized
     * 422 Unprocessable Entity
     * */
    @Headers(value = "Accept-Version: v1")
    @GET("photos/{photoId}/download")
    Call<ResponseBody> getLinkForDownload(
            @Path("photoId") String photoId,
            @Query(CLIENT_ID) String clientId
    );


}
