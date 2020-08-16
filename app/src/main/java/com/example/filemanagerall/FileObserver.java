package com.example.filemanagerall;

import androidx.annotation.Nullable;

public class FileObserver extends android.os.FileObserver {

    public String absolutePath;
    public FileObserver(String path) {
        super(path , FileObserver.DELETE);
        absolutePath = path;
    }

    @Override
    public void onEvent(int i, @Nullable String s) {
        if(s == null)
        {
            return ;
        }

      //  if ((android.os.FileObserver.DELETE & event)!=0 ) {
//            //for testing copy file
//			FileUtils.copyFile(absolutePath + "/" + path);
//           // FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is deleted\n";
//        }


    }
}
