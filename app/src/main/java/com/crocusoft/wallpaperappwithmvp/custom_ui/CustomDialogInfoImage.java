package com.crocusoft.wallpaperappwithmvp.custom_ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;
import com.crocusoft.wallpaperappwithmvp.util.Util;


/**
 * Created by seyidkanan on 4/10/18.
 */

public class CustomDialogInfoImage extends Dialog {

    private LinearLayout elementContainer;

    public CustomDialogInfoImage(@NonNull Context context) {
        super(context);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_image_desc);
        elementContainer = findViewById(R.id.linearLayout_container);
    }

    public void setData(PhotoPOJO photoPOJO) {
        try {
            if (photoPOJO == null) {
                return;
            }
            if (photoPOJO.getUser() != null) {
                if (Util.stringLength(photoPOJO.getUser().getName()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.user));
                    textViewData.setText(photoPOJO.getUser().getName());
                    elementContainer.addView(row);
                }
            }

            if (Util.stringLength(photoPOJO.getViews()) > 0) {
                ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                TextView textViewKey = row.findViewById(R.id.textViewKey);
                TextView textViewData = row.findViewById(R.id.textViewData);
                textViewKey.setText(getContext().getString(R.string.view_count));
                textViewData.setText(photoPOJO.getViews());
                elementContainer.addView(row);
            }

            if (Util.stringLength(photoPOJO.getDownloads()) > 0) {
                ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                TextView textViewKey = row.findViewById(R.id.textViewKey);
                TextView textViewData = row.findViewById(R.id.textViewData);
                textViewKey.setText(getContext().getString(R.string.donwloads_count));
                textViewData.setText(photoPOJO.getDownloads());
                elementContainer.addView(row);
            }

//            TextUtils.length(photoPOJO.getLikes());
            if (Util.stringLength(photoPOJO.getLikes()) > 0) {
                ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                TextView textViewKey = row.findViewById(R.id.textViewKey);
                TextView textViewData = row.findViewById(R.id.textViewData);
                textViewKey.setText(getContext().getString(R.string.likes_count));
                textViewData.setText(photoPOJO.getLikes());
                elementContainer.addView(row);
            }


            if (photoPOJO.getLocation() != null) {
                if (Util.stringLength(photoPOJO.getLocation().getTitle()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.location));
                    textViewData.setText(photoPOJO.getLocation().getTitle());
                    elementContainer.addView(row);
                }
            }


            if (photoPOJO.getExif() != null) {
                ConstraintLayout row1 = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                TextView textViewKey1 = row1.findViewById(R.id.textViewKey);
                TextView textViewData1 = row1.findViewById(R.id.textViewData);
                textViewKey1.setText(getContext().getString(R.string.camera_title));
                textViewData1.setText("");
                elementContainer.addView(row1);


                if (Util.stringLength(photoPOJO.getExif().getMake()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_make));
                    textViewData.setText(photoPOJO.getExif().getMake());
                    elementContainer.addView(row);
                }

                if (Util.stringLength(photoPOJO.getExif().getModel()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_model));
                    textViewData.setText(photoPOJO.getExif().getModel());
                    elementContainer.addView(row);
                }


                if (Util.stringLength(photoPOJO.getExif().getAperture()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_aperture));
                    textViewData.setText(photoPOJO.getExif().getAperture());
                    elementContainer.addView(row);
                }

                if (Util.stringLength(photoPOJO.getExif().getExposure_time()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_exposure_time));
                    textViewData.setText(photoPOJO.getExif().getExposure_time());
                    elementContainer.addView(row);
                }
                if (Util.stringLength(photoPOJO.getExif().getIso()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_iso));
                    textViewData.setText(photoPOJO.getExif().getIso());
                    elementContainer.addView(row);
                }

                if (Util.stringLength(photoPOJO.getExif().getFocal_length()) > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.camera_focal_length));
                    textViewData.setText(photoPOJO.getExif().getFocal_length());
                    elementContainer.addView(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
