package com.example.filemanagerall;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class ImagesGallery {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<String> listofImages(Context context)
    {
        Uri uri;
        Cursor cursur;
        int colum_index_data , column_index_folder_name;
        ArrayList<String> listOfAllimages = new ArrayList<>();
        String ablosutePathofImage;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA ,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        String orderBy = MediaStore.Video.Media.DATE_TAKEN ;

        cursur = context.getContentResolver().query(uri , projection , null , null  );
        colum_index_data = cursur.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);


        while (cursur.moveToNext())
        {
            ablosutePathofImage = cursur.getString(colum_index_data);
            listOfAllimages.add(ablosutePathofImage);
        }
        return  listOfAllimages;

    }
}
