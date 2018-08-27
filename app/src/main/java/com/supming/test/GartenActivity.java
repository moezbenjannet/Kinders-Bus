package com.supming.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.supming.test.fragment_garten.Event_Main;
import com.supming.test.fragment_garten.FragmentGartenAdapter;
import com.supming.test.fragment_garten.HomeGarten_Main;
import com.supming.test.fragment_garten.Schedule_Main;
import com.supming.test.fragment_main.Favorite_Main;
import com.supming.test.fragment_main.FragmentAdapter;
import com.supming.test.fragment_signin.Party_Signin;

public class GartenActivity extends AppCompatActivity implements Event_Main.SendData,HomeGarten_Main.SendData,Schedule_Main.SendData {

    TabLayout tabLayoutG ;
    Toolbar toolbarG;
    ViewPager viewPagerG ;
    TextView Gname;
    ImageView btnretour ,gartenlogo;
    int gid;
    byte[] logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garten);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Gname = (TextView) findViewById(R.id.gartenName);
        viewPagerG = (ViewPager) findViewById(R.id.viewPagerGarten);
        toolbarG = (Toolbar) findViewById(R.id.toolbarGarten);
        setSupportActionBar(toolbarG);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
        btnretour = (ImageView) findViewById(R.id.retour_button) ;
        gartenlogo = (ImageView) findViewById(R.id.gartenLogo) ;
        tabLayoutG = (TabLayout) findViewById(R.id.tablayoutGarten);
        //get data from gartenlistadapter
        Gname.setText(getIntent().getStringExtra("name"));
        gid = getIntent().getIntExtra("id",1);
        logo = getIntent().getByteArrayExtra("logo");
        //set garten logo
        Bitmap bitmap = BitmapFactory.decodeByteArray(logo , 0 , logo.length);
        gartenlogo.setImageBitmap(bitmap);
        //pass data to event_main

        Event_Main event_main = new Event_Main();
        HomeGarten_Main homeGarten_main = new HomeGarten_Main();
        Schedule_Main schedule_main = new Schedule_Main();

        Bundle extras = new Bundle();
        extras.putInt("gid",gid);
        schedule_main.setArguments(extras);
        event_main.setArguments(extras);
        homeGarten_main.setArguments(extras);

        FragmentGartenAdapter fragmentAdapter = new FragmentGartenAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragments(new HomeGarten_Main(),"home");
        fragmentAdapter.addFragments(new Event_Main(),"event");
        fragmentAdapter.addFragments(new Schedule_Main(),"schedule");
        viewPagerG.setAdapter(fragmentAdapter);
        tabLayoutG.setupWithViewPager(viewPagerG);
        tabLayoutG.setTabTextColors(R.color.colorAccent,R.color.colorAccent);

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
}
