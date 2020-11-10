package com.example.grossify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashActivity extends Activity {
    private static int SPLASH_SCREEN = 5000;
    //Variables
    Animation logo_anim, bottom_anim;
    ImageView image;
    TextView logo;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_window1);

        //Animation
        logo_anim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.logo_bottom_anim);
        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.grossify);
        slogan = findViewById(R.id.textView3);

        image.setAnimation(logo_anim);
        logo.setAnimation(bottom_anim);
        slogan.setAnimation(bottom_anim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
