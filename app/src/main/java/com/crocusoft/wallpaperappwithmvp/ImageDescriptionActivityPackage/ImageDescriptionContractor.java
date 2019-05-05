package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;

import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crocusoft.wallpaperappwithmvp.BaseScreenView;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;

public interface ImageDescriptionContractor {

    interface ScreenView extends BaseScreenView {

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
