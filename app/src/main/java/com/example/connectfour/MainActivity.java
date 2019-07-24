package com.example.connectfour;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    ImageView connect;
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        connect=(ImageView)findViewById(R.id.connect_text);
        animation= AnimationUtils.loadAnimation(this, R.anim.scale);
       connect.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, GameMenuActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 2000);
    }
}
