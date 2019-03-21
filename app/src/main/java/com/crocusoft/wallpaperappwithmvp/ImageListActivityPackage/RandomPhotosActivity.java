package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage.ImageDescriptionActivity;
import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.ImageTechnicalInfoPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Constant;
import com.crocusoft.wallpaperappwithmvp.util.EndlessScrollListener;
import com.crocusoft.wallpaperappwithmvp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RandomPhotosActivity extends AppCompatActivity implements RandomPhotoContractor.View {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private RandomPhotoRVAdapter randomPhotoRVAdapter;

    private RandomPhotoContractor.Presenter presenter;

    private List<PhotoPOJO> photoPOJOList;

    private EditText editTextSearch;

    private ConstraintLayout constraintLayoutErrorView, constraintLayoutDataContainer;

    private TextView textViewErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_list_main);

        new RandomPhotoPresenter(this);

        if (presenter == null) {
            // TODO: 3/21/19 show fail init presenter view
            return;
        }
        initViews();
        presenter.fetchRandomData(false);
    }


    public void onClickTryAgainButton(View view) {

    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        editTextSearch = findViewById(R.id.editTextSearch);
        textViewErrorMessage = findViewById(R.id.textViewErrorMessage);
        constraintLayoutDataContainer = findViewById(R.id.constraintLayoutDataContainer);
        constraintLayoutErrorView = findViewById(R.id.constraintLayoutErrorView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (editTextSearch.getText().toString().length() > 0) {
                        presenter.searchTextEntered(editTextSearch.getText().toString());
                    } else {
                        presenter.fetchRandomData(true);
                    }
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        initRV();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void initRV() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        photoPOJOList = new ArrayList<>();
        randomPhotoRVAdapter = new RandomPhotoRVAdapter(photoPOJOList, this, new RVClickListener() {
            @Override
            public void onItemClick(PhotoPOJO photoPOJO) {
                Intent intent = new Intent(RandomPhotosActivity.this, ImageDescriptionActivity.class);
                intent.putExtra(Constant.BUNDLE_PHOTO_DATA, photoPOJO);
                startActivity(intent);
            }
        });

        initRVScrollListener();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(randomPhotoRVAdapter);
    }

    private void initRVScrollListener() {
        recyclerView.setOnScrollListener(new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (editTextSearch.getText().toString().length() > 0) {
                    presenter.loadMoreSearchResult();
                } else {
                    presenter.fetchRandomData(false);
                }
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }
        });
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showErrorView(String message) {
        constraintLayoutErrorView.setVisibility(View.VISIBLE);
        constraintLayoutDataContainer.setVisibility(View.GONE);

        textViewErrorMessage.setText(message);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Util.showToast(this, errorMessage);
    }

    @Override
    public void showSuccessMessage(String message) {
        Util.showToast(this, message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDataFetch(List<PhotoPOJO> photoPOJOS, boolean isNeedClear) {
        constraintLayoutErrorView.setVisibility(View.GONE);
        constraintLayoutDataContainer.setVisibility(View.VISIBLE);

        unPlugRVListener();
        if (isNeedClear) {
            photoPOJOList.clear();
        }
        photoPOJOList.addAll(photoPOJOS);
        randomPhotoRVAdapter.notifyDataSetChanged();
        initRVScrollListener();
    }

    private void unPlugRVListener() {
        recyclerView.setOnScrollListener(null);
    }

    @Override
    public void setPresenter(RandomPhotoContractor.Presenter presenter) {
        this.presenter = presenter;
    }
}
