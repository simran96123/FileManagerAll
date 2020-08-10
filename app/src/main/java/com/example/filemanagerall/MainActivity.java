package com.example.filemanagerall;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //For videos
    AdapterVideoFolder obj_adapter;
    ArrayList<Model_Video> al_video = new ArrayList<>();
    RecyclerView.LayoutManager recyclerViewLayoutManager_Video;
    RecyclerView recyclerView_Video;
   // VideoView vvVideo;


    Button imagebtn ,  videobtn;
    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    TextView gallery_number;


    public static final int MY_READ_PERMISSION_CODE = 101;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // init();

        setContentView(R.layout.activity_main);
        gallery_number = findViewById(R.id.galleryNumber);

        recyclerView = findViewById(R.id.recyclerView);//
        imagebtn = findViewById(R.id.imageButton);
        videobtn = findViewById(R.id.videoButton);
//        document = findViewById(R.id.Document);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, MY_READ_PERMISSION_CODE);
        } else {

            imagebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadImages();
                }
            });

            videobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    init();
                   // fn_video();
                }
            });




        }

    }

    private void init(){

        recyclerView_Video= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewLayoutManager_Video = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView_Video.setLayoutManager(recyclerViewLayoutManager_Video);
        fn_video();



    }



    public void fn_video() {

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name,column_id,thum;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media._ID,MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));
            Log.e("column_id", cursor.getString(column_id));
            Log.e("thum", cursor.getString(thum));

            Model_Video obj_model = new Model_Video();
            obj_model.setBoolean_selected(false);
            obj_model.setStr_path(absolutePathOfImage);
            obj_model.setStr_thumb(cursor.getString(thum));

            al_video.add(obj_model);

        }

        obj_adapter = new AdapterVideoFolder(getApplicationContext(),al_video,MainActivity.this);
        recyclerView_Video.setAdapter(obj_adapter);


    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadImages() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        images = ImagesGallery.listofImages(this);
        galleryAdapter = new GalleryAdapter(this, images, new GalleryAdapter.photoListener() {

            @Override
            public void onPhotoClick(String path) {
                Toast.makeText(MainActivity.this, "" + path, Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(galleryAdapter);
        gallery_number.setText("Photos(" + images.size() + ")");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_READ_PERMISSION_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                loadImages();
                fn_video();
            }
            else {

                Toast.makeText(MainActivity.this, "Permission Denyed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
