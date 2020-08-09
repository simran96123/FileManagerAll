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
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button imagebtn, audio, video, document;
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

        setContentView(R.layout.activity_main);
        gallery_number = findViewById(R.id.galleryNumber);
        recyclerView = findViewById(R.id.recyclerView);//        image = findViewById(R.id.Image);
        imagebtn = findViewById(R.id.imageButton);
//        video = findViewById(R.id.Video);
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

        }

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
            }
            else {

                Toast.makeText(MainActivity.this, "Permission Denyed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
