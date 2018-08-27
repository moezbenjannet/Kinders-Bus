package com.supming.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.supming.test.KinderGarten.DatabaseHelper;

public class Splash extends AppCompatActivity {


    DatabaseHelper myDb;
    private static int s = 2000;
    int counter =60 ;
    int progress=60;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView pourcnt = (TextView) findViewById(R.id.pourcentage);
        // Get the Drawable custom_progressbar
        final Drawable draw = getDrawable(R.drawable.bar_custom);
        final TextView welc = (TextView) findViewById(R.id.welcome) ;
        progressBar.setMax(100);
        final Animation anim = AnimationUtils.loadAnimation( this ,
                R.anim.blink);

        new Thread(new Runnable() {
            @Override
            public void run() {
// set the drawable as progress drawable
                progressBar.setProgressDrawable(draw);
                welc.startAnimation(anim);

                for (int i = 0; i < 100; i++) {
                    progress += 1;
                    progressBar.setProgress(progress);
                    if (progress == progressBar.getMax()) {
                        Intent homeIntent = new Intent(Splash.this, MainActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {

            public void run() {
                while (counter < 100 ) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    pourcnt.post(new Runnable() {

                        public void run() {
                            pourcnt.setText("" + counter + "#");
                            //pourcnt.setTypeface(null, Typeface.BOLD_ITALIC);

                        }

                    });
                    counter++;
                }

            }

        }).start();





    }




}
