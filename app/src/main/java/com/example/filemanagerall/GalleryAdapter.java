package com.example.filemanagerall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter  extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    // for videos
    private Context context;
    private List<String> images;
    private  photoListener  photoListener;

    public GalleryAdapter(Context context, List<String> images, GalleryAdapter.photoListener photoListener) {
        this.context = context;
        this.images = images;
        this.photoListener = photoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.gallery_item , parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String image = images.get(position);
        Glide.with(context).load(image).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoListener.onPhotoClick(image);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }



    public class ViewHolder  extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);

        }
    }
    public interface  photoListener
    {
        void onPhotoClick(String path);
    }

}