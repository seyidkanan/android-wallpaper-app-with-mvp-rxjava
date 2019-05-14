package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage.ImageDescriptionActivity;
import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.idlingResource.FetchingIdlingResource;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
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

    private RandomPhotoPresenter presenter;

    private List<PhotoPOJO> photoPOJOList;

    private EditText editTextSearch;

    private ConstraintLayout constraintLayoutErrorView, constraintLayoutDataContainer;

    private TextView textViewErrorMessage;

    private FetchingIdlingResource fetchingIdlingResource;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_list_main);

        presenter = new RandomPhotoPresenter(this);

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fetchRandomData(false);
    }

    public void onClickTryAgainButton(android.view.View view) {

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
                    if (editTextSearch.getText().toString().trim().length() > 0) {
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

        initSwipeRefreshLayout();

        initRV();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.fetchRandomData(true);
        });
    }

    private void hideKeyboard() {
        android.view.View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void initRV() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        photoPOJOList = new ArrayList<>();
        randomPhotoRVAdapter = new RandomPhotoRVAdapter(photoPOJOList, this, photoPOJO -> {
            Intent intent = new Intent(RandomPhotosActivity.this, ImageDescriptionActivity.class);
            intent.putExtra(Constant.BUNDLE_PHOTO_DATA, photoPOJO);
            startActivity(intent);
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
                if (editTextSearch.getText().toString().trim().length() > 0) {
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
        constraintLayoutErrorView.setVisibility(android.view.View.VISIBLE);
        constraintLayoutDataContainer.setVisibility(android.view.View.GONE);

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
        constraintLayoutErrorView.setVisibility(android.view.View.GONE);
        constraintLayoutDataContainer.setVisibility(android.view.View.VISIBLE);

        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        constraintLayoutErrorView.setVisibility(android.view.View.GONE);
        constraintLayoutDataContainer.setVisibility(android.view.View.VISIBLE);

        progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void onDataFetch(List<PhotoPOJO> photoPOJOS, boolean isNeedClear) {
        swipeRefreshLayout.setRefreshing(false);
        constraintLayoutErrorView.setVisibility(android.view.View.GONE);
        constraintLayoutDataContainer.setVisibility(android.view.View.VISIBLE);

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

    public void setFetcherListener(FetchingIdlingResource fetchingIdlingResource) {
        this.fetchingIdlingResource = fetchingIdlingResource;
        presenter.setFetchingIdlingResource(fetchingIdlingResource);
    }
}
