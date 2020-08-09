package com.example.filemanagerall;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class Activity_galleryview extends Activity {

    String str_video;
    VideoView vv_video;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.gallery_item);
        init();


    }

    private void init()
    {
       // vv_video = (VideoView) findViewById(R.id.vv_video);

        str_video = getIntent().getStringExtra("video");
        vv_video.setVideoPath(str_video);
        vv_video.start();

    }
}
