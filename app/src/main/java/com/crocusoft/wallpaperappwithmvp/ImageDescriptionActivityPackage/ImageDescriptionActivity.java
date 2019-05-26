package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.custom_ui.CustomDialogInfoImage;
import com.crocusoft.wallpaperappwithmvp.util.Util;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDescriptionActivity
        extends AppCompatActivity
        implements ImageDescriptionContractor.ScreenView {

    private PhotoView imageViewPhoto;

    private ImageDescriptionPresenter presenter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);

        initViews();

        presenter = new ImageDescriptionPresenter(this);
        presenter.startShowPhoto();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar_image_description);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle(getString(R.string.photo_title));
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        imageViewPhoto.setMaximumScale(10.0f);

        progressBar = findViewById(R.id.progressBarCachingPercentIndicator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_desc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_description) {
            presenter.onDescClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showErrorMessage(String message) {
        Util.showToast(this, message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView(String message) {

    }

    @Override
    public void enableImage() {
        imageViewPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableImage() {
        imageViewPhoto.setVisibility(View.GONE);
    }

    @Override
    public ImageView getImageView() {
        return imageViewPhoto;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void showDescDialog() {
        CustomDialogInfoImage customDialogInfoImage = new CustomDialogInfoImage(this);
        customDialogInfoImage.setData(presenter.getPhotoPojo());
        customDialogInfoImage.show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
