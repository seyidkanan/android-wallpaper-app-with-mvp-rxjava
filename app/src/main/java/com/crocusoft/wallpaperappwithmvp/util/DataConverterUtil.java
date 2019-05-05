package com.crocusoft.wallpaperappwithmvp.util;

import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.SearchResponsePOJO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DataConverterUtil {


    public static List<PhotoPOJO> parseSuccessResponse(Object o) {
        Gson gson = new Gson();
        String res = new Gson().toJson(o);
        return gson.fromJson(res,
                new TypeToken<List<PhotoPOJO>>() {
                }.getType());
    }

    public static SearchResponsePOJO parseToSearchResponsePOJO(Object response) {
        Gson gson = new Gson();
        String res = gson.toJson(response);
        return gson.fromJson(res, SearchResponsePOJO.class);
    }
}
