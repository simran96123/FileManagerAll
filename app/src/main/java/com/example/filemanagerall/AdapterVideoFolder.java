package com.example.filemanagerall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterVideoFolder extends RecyclerView.Adapter<AdapterVideoFolder.ViewHolder> {
    ArrayList<Model_Video> al_video;
    Context context;
    Activity activity;


public AdapterVideoFolder(Context context , ArrayList<Model_Video> al_video , Activity activity)
{
    this.al_video = al_video;
    this.context = context;
    this.activity = activity;
}

public static  class  ViewHolder extends RecyclerView.ViewHolder
{
    public ImageView iv_image;
    RelativeLayout rl_select;

    public ViewHolder(View v) {

        super(v);

        iv_image = (ImageView) v.findViewById(R.id.iv_image);
        rl_select = (RelativeLayout) v.findViewById(R.id.r1_select);


    }
}



    @NonNull
    @Override
    public AdapterVideoFolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);

        ViewHolder viewHolder1 = new ViewHolder(view);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder Vholder , final int position) {
        Glide.with(context).load("file://" + al_video.get(position).getStr_thumb())
                .skipMemoryCache(false)
                .into(Vholder.iv_image);
        Vholder.rl_select.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Vholder.rl_select.setAlpha(0);



     Vholder.rl_select.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_gallery = new Intent(context,Activity_galleryview.class);
            intent_gallery.putExtra("video",al_video.get(position).getStr_path());
            activity.startActivity(intent_gallery);

        }
    });

}

    @Override
    public int getItemCount() {
        return al_video.size();
    }
}
