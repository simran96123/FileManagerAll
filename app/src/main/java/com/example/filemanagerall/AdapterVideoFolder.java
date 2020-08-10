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

public class AdapterVideoFolder extends RecyclerView.Adapter<AdapterVideoFolder.ViewHolder>  implements View.OnClickListener{
   ArrayList<Model_Video> al_video;
    private long mLastClickTime = System.currentTimeMillis();
    private static final long CLICK_TIME_INTERVAL = 300;
//
    Context context;

  private videolistener videolistener;


public AdapterVideoFolder(Context context , ArrayList<Model_Video> al_video,AdapterVideoFolder.videolistener videolistener )
{
    this.al_video = al_video;
    this.context = context;
   this.videolistener = videolistener;

}

    @Override
    public void onClick(View view) {
        long now = System.currentTimeMillis();
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }
        mLastClickTime = now;


    }

    public static  class  ViewHolder extends RecyclerView.ViewHolder
{
    public ImageView iv_image;



    public ViewHolder(View v) {

        super(v);

       iv_image = (ImageView) v.findViewById(R.id.iv_image);



    }
}



    @NonNull
    @Override
    public AdapterVideoFolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videoitem, parent, false);

        ViewHolder viewHolder1 = new ViewHolder(view);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder Vholder , final int position) {


        Glide.with(context).load("file://" + al_video.get(position)
                .getStr_thumb())
                .skipMemoryCache(false)
                .into(Vholder.iv_image);




}

    @Override
    public int getItemCount() {
        return al_video.size();
    }

    public interface  videolistener
    {
        void onvideoclick(String path);
    }




}
