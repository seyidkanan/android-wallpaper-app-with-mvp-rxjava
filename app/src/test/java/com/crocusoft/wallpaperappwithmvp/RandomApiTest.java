package com.crocusoft.wallpaperappwithmvp;


import android.util.Log;

import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotoContractor;
import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotoPresenter;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.interactors.PhotoInteractor;
import com.crocusoft.wallpaperappwithmvp.pojo.response.ErrorPOJO;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomApiTest {

    @Mock
    RandomPhotoContractor.View view;

    @Mock
    ApiInterfaces apiInterface;

    @Mock
    PhotoInteractor photoInteractor;

    @InjectMocks
    RandomPhotoPresenter presenter;

    @Captor
    ArgumentCaptor<RandomPhotoPresenter.RandomPhotoObserver> photoObserver;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter.setPhotoInteractor(photoInteractor);
    }

    @Test
    public void testRandomApi_Mock_resultSuccessFlow() {
        List<PhotoPOJO> photoPOJOS = new ArrayList<>();
        Object o = photoPOJOS;

        presenter.fetchRandomData(true);

        verify(view).showProgress();
        verify(photoInteractor).getDataFromRandomApi(photoObserver.capture());

        RandomPhotoPresenter.RandomPhotoObserver response = photoObserver.getValue();

        response.onNext(o);
        response.onComplete();

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).onDataFetch(any(), anyBoolean());
        inOrder.verify(view).hideProgress();
    }

    @Test
    public void testRandomApi_Mock_resultFailedFlow() {
        presenter.fetchRandomData(true);

        verify(view).showProgress();
        verify(photoInteractor).getDataFromRandomApi(photoObserver.capture());

        RandomPhotoPresenter.RandomPhotoObserver response = photoObserver.getValue();

        response.onError(new Throwable("mock exception"));

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).hideProgress();
        inOrder.verify(view, never()).onDataFetch(any(), anyBoolean());
        inOrder.verify(view).showErrorMessage(anyString());
    }

}
