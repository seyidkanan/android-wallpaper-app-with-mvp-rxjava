package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RandomPhotosActivity extends AppCompatActivity implements RandomPhotoContractor.View {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RandomPhotoRVAdapter randomPhotoRVAdapter;
    private RandomPhotoPresenter presenter;
    private List<PhotoPOJO> photoPOJOList;

    @BindView(R.id.constraintLayoutErrorView)
    ConstraintLayout constraintLayoutErrorView;

    @BindView(R.id.constraintLayoutDataContainer)
    ConstraintLayout constraintLayoutDataContainer;

    @BindView(R.id.textViewErrorMessage)
    TextView textViewErrorMessage;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String searchQuery="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_list_main);
        ButterKnife.bind(this);

        presenter = new RandomPhotoPresenter(this);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fetchRandomData(false);
    }

    public void onClickTryAgainButton(android.view.View view) {

    }

    private void initViews() {
        setSupportActionBar(toolbar);

        initSwipeRefreshLayout();

        initRV();

        initSearchIcons();
    }

    private void initSearchIcons() {
        searchView.setEllipsize(true);
        searchView.setHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query;
                presenter.searchTextEntered(query.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchQuery = "";
            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void initSwipeRefreshLayout() {
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
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        photoPOJOList = new ArrayList<>();
        randomPhotoRVAdapter = new RandomPhotoRVAdapter(photoPOJOList, this, photoPOJO -> {
            Intent intent = new Intent(RandomPhotosActivity.this, ImageDescriptionActivity.class);
            intent.putExtra(Constant.BUNDLE_PHOTO_DATA, photoPOJO);
            startActivity(intent);
        });

        initRVScrollListener();

//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(randomPhotoRVAdapter);
    }

    private void initRVScrollListener() {
        recyclerView.setOnScrollListener(new EndlessScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (searchQuery.trim().length() > 0) {
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
        presenter.setFetchingIdlingResource(fetchingIdlingResource);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
