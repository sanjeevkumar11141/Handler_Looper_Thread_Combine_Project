package com.example.handerthreaddemo;

import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread {

    private static final String TAG = "MyTag";

    DownloadHandler downloadHandler;


    @Override
    public void run() {
        super.run();

        Looper.prepare();
        downloadHandler = new DownloadHandler();
        Looper.loop();

       /* Log.d(TAG, "Downloading starting:");
        for (String song: SongPlayList.songList) {
            downloadSong(song);
        }*/
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
