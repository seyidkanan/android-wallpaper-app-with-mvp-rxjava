package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.PhotoUtil;
import com.crocusoft.wallpaperappwithmvp.util.Util;

public class RandomPhotoViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private View view;

    public RandomPhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        imageView = itemView.findViewById(R.id.imageView_photo);
    }

    public void bind(final PhotoPOJO photoPOJO, final RVClickListener rvClickListener, Context context) {
        if (photoPOJO.getUrls() != null) {
            if (photoPOJO.getUrls().getSmall() != null) {
                PhotoUtil.setImageWithPicasso(context, photoPOJO.getUrls().getSmall(), imageView, false);
            }
        }
        view.setOnClickListener(v -> rvClickListener.onItemClick(photoPOJO));
    }

}
