package com.crocusoft.wallpaperappwithmvp.ImageListActivityPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crocusoft.wallpaperappwithmvp.R;
import com.crocusoft.wallpaperappwithmvp.pojo.response.PhotoPOJO;

import java.util.List;

public class RandomPhotoRVAdapter extends RecyclerView.Adapter<RandomPhotoViewHolder> {

    private List<PhotoPOJO> photoPOJOS;
    private Context context;
    private RVClickListener rvClickListener;

    public RandomPhotoRVAdapter(List<PhotoPOJO> photoPOJOS, Context context, RVClickListener rvClickListener) {
        this.photoPOJOS = photoPOJOS;
        this.context = context;
        this.rvClickListener = rvClickListener;
    }

    @NonNull
    @Override
    public RandomPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_view_holder, viewGroup, false);
        return new RandomPhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomPhotoViewHolder randomPhotoViewHolder, int i) {
        randomPhotoViewHolder.bind(photoPOJOS.get(i), rvClickListener, context);
    }

    @Override
    public int getItemCount() {
        return photoPOJOS.size();
    }


}
