package com.example.filemanagerall;

public class Environment {
    public static boolean is_external_storage_available() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
