package com.crocusoft.wallpaperappwithmvp.util;

import android.content.Context;
import android.widget.ImageView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.squareup.picasso.Picasso;

public class Util {

    public static void setImageWithPicasso(Context context, String url, ImageView target) {
        try {
            Picasso.with(context)
                    .load(url)
                    //.resize(200, 200)
                    .placeholder(R.drawable.downloading)
                    .error(R.drawable.error)
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
