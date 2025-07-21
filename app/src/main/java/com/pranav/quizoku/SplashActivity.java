package com.pranav.quizoku;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 2500; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Bind views
        ImageView logo = findViewById(R.id.splash_logo);

        // Load and start animation
        Animation fadeMove = AnimationUtils.loadAnimation(this, R.anim.fade_in_move_up);
        logo.startAnimation(fadeMove);

        // Navigate to MainActivity after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);

            // Use ActivityOptions for modern animation transition
            Bundle options = ActivityOptions.makeCustomAnimation(
                    SplashActivity.this,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            ).toBundle();

            startActivity(intent, options);
            finish();
        }, SPLASH_DELAY);
    }
}
