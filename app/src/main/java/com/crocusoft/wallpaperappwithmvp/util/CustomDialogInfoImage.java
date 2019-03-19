package com.crocusoft.wallpaperappwithmvp.util;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.PhotoPOJO;


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
//            elementContainer.removeAllViews();
//            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
            if (photoPOJO.getUser() != null) {
                if (photoPOJO.getUser().getName() != null) {
                    if (photoPOJO.getUser().getName().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.user));
                        textViewData.setText(photoPOJO.getUser().getName());
                        elementContainer.addView(row);
                    }
                }
            }

            if (photoPOJO.getViews() != null) {
                if (photoPOJO.getViews().length() > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.view_count));
                    textViewData.setText(photoPOJO.getViews());
                    elementContainer.addView(row);
                }
            }

            if (photoPOJO.getDownloads() != null) {
                if (photoPOJO.getDownloads().length() > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.donwloads_count));
                    textViewData.setText(photoPOJO.getDownloads());
                    elementContainer.addView(row);
                }
            }

            if (photoPOJO.getLikes() != null) {
                if (photoPOJO.getLikes().length() > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.likes_count));
                    textViewData.setText(photoPOJO.getLikes());
                    elementContainer.addView(row);
                }
            }

            if (photoPOJO.getLocation() != null) {
                if (photoPOJO.getLocation().getTitle() != null) {
                    if (photoPOJO.getLocation().getTitle().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.location));
                        textViewData.setText(photoPOJO.getLocation().getTitle());
                        elementContainer.addView(row);
                    }
                }
            }


            if (photoPOJO.getExif() != null) {
                ConstraintLayout row1 = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                TextView textViewKey1 = row1.findViewById(R.id.textViewKey);
                TextView textViewData1 = row1.findViewById(R.id.textViewData);
                textViewKey1.setText(getContext().getString(R.string.camera_title));
                textViewData1.setText("");
                elementContainer.addView(row1);


                if (photoPOJO.getExif().getMake() != null) {
                    if (photoPOJO.getExif().getMake().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_make));
                        textViewData.setText(photoPOJO.getExif().getMake());
                        elementContainer.addView(row);
                    }
                }
                if (photoPOJO.getExif().getModel() != null) {
                    if (photoPOJO.getExif().getModel().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_model));
                        textViewData.setText(photoPOJO.getExif().getModel());
                        elementContainer.addView(row);
                    }
                }


                if (photoPOJO.getExif().getAperture() != null) {
                    if (photoPOJO.getExif().getAperture().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_aperture));
                        textViewData.setText(photoPOJO.getExif().getAperture());
                        elementContainer.addView(row);
                    }
                }
                if (photoPOJO.getExif().getExposure_time() != null) {
                    if (photoPOJO.getExif().getExposure_time().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_exposure_time));
                        textViewData.setText(photoPOJO.getExif().getExposure_time());
                        elementContainer.addView(row);
                    }
                }
                if (photoPOJO.getExif().getIso() != null) {
                    if (photoPOJO.getExif().getIso().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_iso));
                        textViewData.setText(photoPOJO.getExif().getIso());
                        elementContainer.addView(row);
                    }
                }
                if (photoPOJO.getExif().getFocal_length() != null) {
                    if (photoPOJO.getExif().getFocal_length().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.camera_focal_length));
                        textViewData.setText(photoPOJO.getExif().getFocal_length());
                        elementContainer.addView(row);
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
