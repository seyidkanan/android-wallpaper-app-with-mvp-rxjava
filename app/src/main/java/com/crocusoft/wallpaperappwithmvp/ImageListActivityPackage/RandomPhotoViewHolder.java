package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Util;

public class RandomPhotoViewHolder extends RecyclerView.ViewHolder {

    private TextView descriptionTV, locationTV;
    private ImageView imageView;
    private View view;

    public RandomPhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        descriptionTV = itemView.findViewById(R.id.textView_description_of_photo);
        locationTV = itemView.findViewById(R.id.textView_location_of_photo);
        imageView = itemView.findViewById(R.id.imageView_photo);
    }

    public void bind(final PhotoPOJO photoPOJO, final RVClickListener rvClickListener, Context context) {
        if (photoPOJO.getDescription() != null) {
            descriptionTV.setVisibility(View.VISIBLE);
            descriptionTV.setText(photoPOJO.getDescription());
        }
        if (photoPOJO.getLocation() != null) {
            if (photoPOJO.getLocation().getTitle() != null) {
                if (photoPOJO.getLocation().getTitle().length() > 0) {
                    locationTV.setVisibility(View.VISIBLE);
                    locationTV.setText(photoPOJO.getLocation().getTitle());
                }
            }
        }

        if (photoPOJO.getUrls() != null) {
            if (photoPOJO.getUrls().getSmall() != null) {
                Util.setImageWithPicasso(context, photoPOJO.getUrls().getSmall(), imageView, false);
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvClickListener.onItemClick(photoPOJO);
            }
        });
    }

}
