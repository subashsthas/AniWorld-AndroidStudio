package com.example.animeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity {
    VideoView videoView;
    ProgressBar progressBar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);



        MediaController mediaController = new MediaController(this);
        videoView = findViewById(R.id.vid);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/animeworld-97e75.appspot.com/o/Death%20Note%20-%20OFFICIAL%20TRAILER%20-%20YouTube.mkv?alt=media&token=0cfd7ff9-806e-435e-9409-487f2fd9402c");
        videoView.setVideoURI(uri);
        videoView.start();



        progressBar.setVisibility(View.VISIBLE);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                   int arg2) {
                        // TODO Auto-generated method stub
                        progressBar.setVisibility(View.GONE);
                        mp.start();
                    }
                });
            }
        });
    }
}