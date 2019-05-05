package com.crocusoft.wallpaperappwithmvp.util;

import android.content.Context;
import android.widget.ImageView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.squareup.picasso.Picasso;

public class PhotoUtil {

    public static void setImageWithPicasso(Context context, String url, ImageView target, boolean isPlaceHolderLarge) {
        try {
            Picasso.with(context)
                    .load(url)
                    .error(R.drawable.error)
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
