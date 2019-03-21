package com.crocusoft.wallpaperappwithmvp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.crocusoft.wallpaperappwithmvp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Util {

    public static void setImageWithPicasso(Context context, String url, ImageView target, boolean isPlaceHolderLarge) {
        try {
            Picasso.with(context)
                    .load(url)
                    //.resize(200, 200)
                    .placeholder(isPlaceHolderLarge ? R.drawable.placeholder_big : R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImageWithPicassoWithTarget(Context context, String url, Target target, boolean isPlaceHolderLarge) {
        try {
            Picasso.with(context)
                    .load(url)
                    //.resize(200, 200)
                    .placeholder(isPlaceHolderLarge ? R.drawable.placeholder_big : R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Returns length of input string
     *
     * @param s the string to be examined
     * @return 0 if str is null or zero length
     */
    public static int stringLength(String s) {
        return isEmpty(s) ? 0 : s.trim().length();
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }


}
