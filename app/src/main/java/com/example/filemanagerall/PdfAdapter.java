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

import java.io.File;
import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    private Context context;
    private ArrayList<File> filelist;


    public PdfAdapter(Context context, ArrayList<File> filelist) {
        this.context = context;
        this.filelist = filelist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PdfAdapter.ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.pdf_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(R.drawable.fileicon).into(holder.fileImage);

        holder.filename.setText(filelist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fileImage;
        TextView filename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.tv_name);
            fileImage = itemView.findViewById(R.id.iv_image);


        }
    }
}
