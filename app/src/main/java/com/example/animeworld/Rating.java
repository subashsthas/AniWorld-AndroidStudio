package com.example.animeworld;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.os.Bundle;

public class Rating extends AppCompatActivity {
    RatingBar ratingDemo;
    Button ratingsubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingDemo=(RatingBar)findViewById(R.id.ratingdemo);
        ratingsubmitButton=(Button)findViewById(R.id.ratingsubmitButton);

        ratingsubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rvalue=ratingDemo.getRating();
                Toast.makeText(getApplicationContext(),"Rating:-"+rvalue,Toast.LENGTH_LONG).show();
            }
        });

    }
    }










