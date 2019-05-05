package com.crocusoft.wallpaperappwithmvp;


import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotoContractor;
import com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage.RandomPhotoPresenter;
import com.crocusoft.wallpaperappwithmvp.data.api.ApiInterfaces;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomApiTest {

    @Mock
    RandomPhotoContractor.View view;

    @Mock
    ApiInterfaces apiInterface;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRandomApi() {
//        List<PhotoPOJO> photoPOJOS = new ArrayList<>();
//        Object o = photoPOJOS;
//
//        when(apiInterface.getRandomPhotosRX(Constant.CLIENT_ID, Constant.PAGINATION_ITEM_COUNT))
//                .thenReturn(Observable.just(o));
//
//        RandomPhotoPresenter presenter = new RandomPhotoPresenter(view);
//
//        presenter.fetchRandomData(true);
//
//        InOrder inOrder = Mockito.inOrder(view);
//        inOrder.verify(view, times(1)).showProgress();

//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//
//                return null;
//            }
//        }).when(apiInterface).getRandomPhotosRX(Constant.CLIENT_ID, Constant.PAGINATION_ITEM_COUNT);

//        inOrder.verify(view, times(1)).onDataFetch(any(), anyBoolean());
//        inOrder.verify(view, times(1)).showErrorMessage(any());
//        inOrder.verify(view, times(1)).hideProgress();
    }


}
