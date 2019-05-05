package com.crocusoft.wallpaperappwithmvp.interactors;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseInteractor {

    protected <T> ObservableTransformer<T, T> baseCall() {
        return new BaseCallTransformer<>();
    }

    private final class BaseCallTransformer<T> implements ObservableTransformer<T, T> {
        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
