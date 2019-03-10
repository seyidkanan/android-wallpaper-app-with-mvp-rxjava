package com.crocusoft.wallpaperappwithmvp.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by seyidkanan on 3/13/18.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private final int VISIBLE_THRESHOLD = 5;

    private int mPreviousTotal = 0;
    private boolean mLoading = true;
    private int mCurrentPage = 0;
    private GridLayoutManager mLinearLayoutManager;

    private static final int HIDE_THRESHOLD = 10;

    private int mScrolledDistance = 0;
    private boolean mControlsVisible = true;

    public EndlessScrollListener(GridLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (!recyclerView.canScrollVertically(-1)) {
            onScrollUp();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrollDown();
        }

        if (dy < 0) {
            onScrollUp();
        } else if (dy > 0) {
            onScrollDown();
        }

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (firstVisibleItem == 0) {
            if(!mControlsVisible) {
                onScrollUp();
                mControlsVisible = true;
            }
        } else {
            if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) {
                onScrollUp();
                mControlsVisible = false;
                mScrolledDistance = 0;
            } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible) {
                onScrollDown();
                mControlsVisible = true;
                mScrolledDistance = 0;
            }
        }
        if((mControlsVisible && dy>0) || (!mControlsVisible && dy<0)) {
            mScrolledDistance += dy;
        }

//        Log.e("kanan", String
//                .format("Visib item count = %s, total item count = %s, first visible item = %s" +
//                                ", Previous Total=%s",
//                        visibleItemCount, totalItemCount, firstVisibleItem, mPreviousTotal));
        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }

        if (!mLoading && (totalItemCount - firstVisibleItem - visibleItemCount)
                <= VISIBLE_THRESHOLD) {
            mCurrentPage = mCurrentPage + 10;
            onLoadMore(mCurrentPage);

            mLoading = true;
        }
    }

    public void reset() {
        mCurrentPage = 0;
        mPreviousTotal = 0;
        mLoading = true;
    }

    public abstract void onLoadMore(int currentPage);

    public abstract void onScrollUp();

    public abstract void onScrollDown();

}
