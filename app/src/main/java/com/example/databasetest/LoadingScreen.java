package com.example.databasetest;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.load);
        ImageView img = findViewById(R.id.animationView);
        img.setBackgroundResource(R.drawable.loading);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameAnimation.start();
            }
        });

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                img.performClick();
            }
        }, 1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}