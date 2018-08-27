package com.supming.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.supming.test.fragment_events.Description_Event;
import com.supming.test.fragment_events.Media_Event;
import com.supming.test.fragment_garten.Event_Main;
import com.supming.test.fragment_garten.FragmentGartenAdapter;
import com.supming.test.fragment_garten.HomeGarten_Main;
import com.supming.test.fragment_garten.Schedule_Main;
import com.supming.test.fragment_main.FragmentAdapter;

public class EventActivity extends AppCompatActivity implements Description_Event.SendData,Media_Event.SendData{
    TabLayout tabLayoutE ;
    Toolbar toolbarE;
    ViewPager viewPagerE ;
    ImageView btnretour;
    TextView toolbarheader ;
    int gid,eid,position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gid = getIntent().getIntExtra("id",1);
        eid = getIntent().getIntExtra("Eid",1);
        position = getIntent().getIntExtra("position",1);
        btnretour = (ImageView) findViewById(R.id.retour_button1) ;
        viewPagerE = (ViewPager) findViewById(R.id.viewPagerevent);
        toolbarE = (Toolbar) findViewById(R.id.toolbarEvent);
        toolbarheader = (TextView) findViewById(R.id.toolbar_header);

        toolbarheader.setText("Event");

        setSupportActionBar(toolbarE);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
        tabLayoutE = (TabLayout) findViewById(R.id.tablayoutevent);

        //pass data to description_event and media_event
        Description_Event description_event = new Description_Event();
        Media_Event media_event = new Media_Event();
        Bundle extras = new Bundle();
        extras.putInt("gid",gid);
        extras.putInt("eid",eid);
        extras.putInt("position",position);
        description_event.setArguments(extras);
        media_event.setArguments(extras);

        FragmentGartenAdapter fragmentAdapter = new FragmentGartenAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragments(new Description_Event(),"description");
        fragmentAdapter.addFragments(new Media_Event(),"media");
        viewPagerE.setAdapter(fragmentAdapter);
        tabLayoutE.setupWithViewPager(viewPagerE);
        tabLayoutE.setTabTextColors(R.color.colorAccent,R.color.colorAccent);
        btnretour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public int getIntFromAct() {
        return gid;
    }

    public int getposFromAct() {
        return position;
    }

    public int geteidFromAct() {
        return eid;
    }

}
