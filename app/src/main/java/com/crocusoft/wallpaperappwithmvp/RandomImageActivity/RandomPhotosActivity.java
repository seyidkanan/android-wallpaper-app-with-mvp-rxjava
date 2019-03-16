package com.crocusoft.wallpaperappwithmvp.RandomImageActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.EndlessScrollListener;
import com.paginate.Paginate;

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

//    private Paginate.Callbacks callbacks;

    private boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_photos_list_main);

        new RandomPhotoPresenter(this);
        initViews();
        isLoading = true;
        presenter.fetchRandomData(false);
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        editTextSearch = findViewById(R.id.editTextSearch);

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

            }
        });

//        callbacks = new Paginate.Callbacks() {
//            @Override
//            public void onLoadMore() {
//                isLoading = true;
//                if (editTextSearch.getText().toString().length() > 0) {
//                    presenter.loadMoreSearchResult();
//                } else {
//                    presenter.fetchRandomData(false);
//                }
//            }
//
//            @Override
//            public boolean isLoading() {
//                // Indicate whether new page loading is in progress or not
//                return isLoading;
//            }
//
//            @Override
//            public boolean hasLoadedAllItems() {
//                // Indicate whether all data (pages) are loaded or not
//                return false;
//            }
//        };


        initRVScrollListener();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(randomPhotoRVAdapter);

//        Paginate.with(recyclerView, callbacks)
//                .setLoadingTriggerThreshold(5)
//                .addLoadingListItem(true)
////                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
////                .setLoadingListItemSpanSizeLookup(new CustomLoadingListItemSpanLookup())
//                .build();
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
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
        unPlugRVListener();
        isLoading = false;
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
