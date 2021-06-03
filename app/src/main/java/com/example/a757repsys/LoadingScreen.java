package com.example.a757repsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class LoadingScreen extends AppCompatActivity {

    ImageView brgyLogo;
    ProgressBar prgsbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        brgyLogo = findViewById(R.id.imgViewBarangayLogo);
        prgsbar = findViewById(R.id.prgsBar);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this,LoginScreen.class));
                finish();

            }
        },2000);


    }
}