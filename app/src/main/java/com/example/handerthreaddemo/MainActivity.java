package com.example.handerthreaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button button;
    Handler handler;
    DownloadThread downloadThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  runThread();
              //  downloadByOtherThread();
                runCodeHAndelerOrLooper();
            }
        });

       /* handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                String msg = message.getData().getString("MESSAGE_KEY");
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        handler = new Handler(getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                String st = msg.getData().getString("MESSAGE_KEY");
                Toast.makeText(MainActivity.this, st, Toast.LENGTH_SHORT).show();
            }
        };
        downloadThread = new DownloadThread();
        downloadThread.start();

    }

    private void runThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = new Message();;
                Bundle bundle = new Bundle();
                bundle.putString("MESSAGE_KEY","I want to send msg to handler from non ui thread");
                message.setData(bundle);
              boolean status =  handler.sendMessage(message);

                Log.d(TAG, "run: "+status);

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

   public void  downloadByOtherThread(){
        DownloadThread thread = new DownloadThread();
        thread.start();
    }


    public void runCodeHAndelerOrLooper(){
        for (String s : SongPlayList.songList){
           // Message message = Message.obtain();
            Message message = new Message();
            message.obj=s;
            downloadThread.downloadHandler.sendMessage(message);
        }
    }
}
