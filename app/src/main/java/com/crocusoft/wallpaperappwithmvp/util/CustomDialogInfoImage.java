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

    public CustomDialogInfoImage(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_image_desc);
    }

    public void setData(PhotoPOJO photoPOJO) {
        try {
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
            if (photoPOJO.getUser() != null) {
                if (photoPOJO.getUser().getName() != null) {
                    if (photoPOJO.getUser().getName().length() > 0) {
                        ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                        TextView textViewKey = row.findViewById(R.id.textViewKey);
                        TextView textViewData = row.findViewById(R.id.textViewData);
                        textViewKey.setText(getContext().getString(R.string.user));
                        textViewData.setText(photoPOJO.getUser().getName());
                        addContentView(row, lp);
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
                    addContentView(row, lp);
                }
            }

            if (photoPOJO.getDownloads() != null) {
                if (photoPOJO.getDownloads().length() > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.donwloads_count));
                    textViewData.setText(photoPOJO.getDownloads());
                    addContentView(row, lp);
                }
            }

            if (photoPOJO.getLikes() != null) {
                if (photoPOJO.getLikes().length() > 0) {
                    ConstraintLayout row = (ConstraintLayout) getLayoutInflater().inflate(R.layout.element_of_image_desc, null);
                    TextView textViewKey = row.findViewById(R.id.textViewKey);
                    TextView textViewData = row.findViewById(R.id.textViewData);
                    textViewKey.setText(getContext().getString(R.string.likes_count));
                    textViewData.setText(photoPOJO.getLikes());
                    addContentView(row, lp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
