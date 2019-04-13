package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.util.FileDownloadAsync;

import java.io.File;

public class PhotoCacheManager implements FileDowloadingIndicaterInterface {

    private String photoUrl;
    private ImageView imageView;
    private Activity activity;
    private ProgressBar progressBar;

    public PhotoCacheManager() {
    }

    public void startCashing() {
        new FileDownloadAsync(this).execute(photoUrl);
    }

    @Override
    public void onImageDownloadProgress(final long percent) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress((int) percent);
            }
        });
    }

    @Override
    public void onImageDownloadSuccess(String filePath) {
        File imgFile = new File(filePath);
        if (imgFile.exists()) {
            final Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    imageView.setImageBitmap(myBitmap);
                }
            });
        }
    }

    @Override
    public void onImageDownloadError(Exception e, String error) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.error));
            }
        });
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
