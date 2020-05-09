package com.dawa.mobilehealth.splashscreen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    ImageView logo;
    TextView title, slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.img_splash);
        title = findViewById(R.id.tv1);
        slogan = findViewById(R.id.tv2);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.top_splash_animation);
        Animation titleanim = AnimationUtils.loadAnimation(this,R.anim.bottom_splash_animation);
        Animation slogananim = AnimationUtils.loadAnimation(this,R.anim.bottom_splash_animation);
        logo.startAnimation(myanim);
        title.startAnimation(titleanim);
        slogan.startAnimation(slogananim);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
