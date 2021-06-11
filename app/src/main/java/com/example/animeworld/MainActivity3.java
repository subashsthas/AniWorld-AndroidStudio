package com.example.animeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity3 extends AppCompatActivity {
    VideoView videoView1;
    ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        MediaController mediaController = new MediaController(this);
        videoView1 = (VideoView)findViewById(R.id.vid);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        videoView1.setMediaController(mediaController);
        mediaController.setAnchorView(videoView1);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/animeworld-97e75.appspot.com/o/Attack%20on%20Titan%20Final%20Season%20Trailer%20-%20Official%20PV%E3%80%8CEnglish%20Sub%E3%80%8D.mp4?alt=media&token=1186344c-6ed4-44f0-9be6-9a209777b7fd");
        videoView1.setVideoURI(uri);
        videoView1.start();


        progressBar.setVisibility(View.VISIBLE);
        videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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




