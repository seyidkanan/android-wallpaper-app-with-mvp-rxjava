package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;

import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crocusoft.wallpaperappwithmvp.BaseView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;

public interface ImageDescriptionContractor {

    interface View extends BaseView<Presenter> {

        void enableImage();

        void disableImage();

        ImageView getImageView();

        ProgressBar getProgressBar();

        void showDescDialog();

    }

    interface Presenter {

        void startShowPhoto();

        void onDescClick();

        PhotoPOJO getPhotoPojo();

        void setPhotoPojo(PhotoPOJO photoPojo);
    }

}
