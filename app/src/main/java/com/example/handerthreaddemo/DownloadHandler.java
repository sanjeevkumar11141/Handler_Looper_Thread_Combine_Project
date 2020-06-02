package com.example.handerthreaddemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {
    private static final String TAG = "MyTag";

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

        downloadSong(msg.obj.toString());
    }

    public void downloadSong(String s){
        try {
            Thread.sleep(4000);
            Log.d(TAG, "downloading Song: "+s);
            Log.d(TAG, "download complete: "+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
