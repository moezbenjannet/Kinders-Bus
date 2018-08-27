package com.supming.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by Omar Bouhamed on 02/12/2017.
 */

public class Splsh extends AppCompatActivity {

    ImageView image;
    ImageView welc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.anim_translate);

        final Animation anim2 = AnimationUtils.loadAnimation(this,
                R.anim.blink);

        image = (ImageView) findViewById(R.id.icon);
        welc = (ImageView) findViewById(R.id.bb);

        new Thread(new Runnable() {
            @Override
            public void run() {
// set the drawable as progress drawable

                image.startAnimation(anim);
                welc.startAnimation(anim2);

                image.setVisibility(ImageView.INVISIBLE);
                //welc.setVisibility(ImageView.INVISIBLE);


            }


        }).start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Splsh.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }  },4450);

        }


    }
