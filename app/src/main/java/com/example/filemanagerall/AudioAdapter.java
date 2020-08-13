package com.example.filemanagerall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AudioAdapter extends  RecyclerView.Adapter<AudioAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ModelAudio> audiolist;

    public AudioAdapter(Context context, ArrayList<ModelAudio> audiolist) {
        this.context = context;
        this.audiolist = audiolist;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.audioitem, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ModelAudio audio = audiolist.get(position);

        Glide.with(context).load(R.drawable.audioimage).into(holder.audioimageBtn);

        holder.setSongTitle(audio.SongTitle);
        holder.setArtistName(audio.SongArtist);


    }

    @Override
    public int getItemCount() {
        return audiolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView audioimageBtn;
        TextView titleName,artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audioimageBtn = itemView.findViewById(R.id.audioImage);
            titleName = itemView.findViewById(R.id.textTitle);
            artistName = itemView.findViewById(R.id.textArtist);
        }
        public void setSongTitle(String name) {
            titleName.setText(name);
        }
        public void setArtistName(String artist) {
            artistName.setText(artist);
        }


    }
}
