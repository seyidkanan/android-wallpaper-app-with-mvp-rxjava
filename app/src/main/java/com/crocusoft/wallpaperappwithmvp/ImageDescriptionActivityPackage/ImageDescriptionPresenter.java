package com.crocusoft.wallpaperappwithmvp.ImageDescriptionActivityPackage;


import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Constant;

public class ImageDescriptionPresenter implements ImageDescriptionContractor.Presenter {

    private ImageDescriptionContractor.View view;

    private PhotoPOJO photoPOJO;

    public ImageDescriptionPresenter(ImageDescriptionContractor.View view) {
        if (view == null) {
            return;
        }
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void startShowPhoto() {
        if (view == null) {
            return;
        }

        try {
            if (view.getActivity().getIntent() != null) {
                PhotoPOJO photoPOJO = (PhotoPOJO) view.getActivity().getIntent().getSerializableExtra(Constant.BUNDLE_PHOTO_DATA);
                if (photoPOJO != null) {
                    setPhotoPojo(photoPOJO);


                    PhotoCacheManager photoCacheManager
                            = new PhotoCacheManager();

                    photoCacheManager.setActivity(view.getActivity());
                    photoCacheManager.setImageView(view.getImageView());
                    photoCacheManager.setPhotoUrl((photoPOJO.getWidth() < 4096 && photoPOJO.getHeight() < 4096) ? photoPOJO.getUrls().getFull() : photoPOJO.getUrls().getRegular());
                    photoCacheManager.setProgressBar(view.getProgressBar());
                    photoCacheManager.startCashing();


//                    Util.setImageWithPicasso(view.getContext(),
//                            (photoPOJO.getWidth() < 4096 && photoPOJO.getHeight() < 4096) ? photoPOJO.getUrls().getFull() : photoPOJO.getUrls().getRegular(),
//                            view.getImageView(),
//                            true);


                } else {
                    view.showErrorMessage(view.getContext().getString(R.string.no_data));
                }
            } else {
                view.showErrorMessage(view.getContext().getString(R.string.no_data));
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public void onDescClick() {
        view.showDescDialog();
    }

    @Override
    public PhotoPOJO getPhotoPojo() {
        return photoPOJO;
    }

    @Override
    public void setPhotoPojo(PhotoPOJO photoPojo) {
        this.photoPOJO = photoPojo;
    }


}
