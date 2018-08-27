package com.supming.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.supming.test.fragment_garten.FragmentGartenAdapter;
import com.supming.test.fragment_signin.Assignment_Signin;
import com.supming.test.fragment_signin.Live_Signin;
import com.supming.test.fragment_signin.Party_Signin;
import com.supming.test.login_garten.DashBoard;

public class SigninActivity extends AppCompatActivity implements Party_Signin.SendData ,Assignment_Signin.SendData,Live_Signin.SendData{
    TabLayout tabLayoutS ;
    Toolbar toolbarS;
    ViewPager viewPagerS ;
    ImageView btnretour;
    ProgressDialog progressDialog ;
    int data ;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //menu
        //setHasOptionsMenu(true);
        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading list image...");
        progressDialog.show();
        progressDialog.dismiss();

        btnretour = (ImageView) findViewById(R.id.retour_button2) ;
        viewPagerS = (ViewPager) findViewById(R.id.viewPagersignin);
        toolbarS = (Toolbar) findViewById(R.id.toolbarSignin);
        setSupportActionBar(toolbarS);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
        tabLayoutS = (TabLayout) findViewById(R.id.tablayoutsignin);
        data = getIntent().getIntExtra("data",1);
        Party_Signin party_signin = new Party_Signin();

        Bundle extras = new Bundle();
        extras.putInt("gid",data);
        party_signin.setArguments(extras);

        FragmentGartenAdapter fragmentAdapter = new FragmentGartenAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragments(new Party_Signin(),"Party");
        fragmentAdapter.addFragments(new Live_Signin(),"live");
        fragmentAdapter.addFragments(new Assignment_Signin(),"assignment");
        viewPagerS.setAdapter(fragmentAdapter);
        tabLayoutS.setupWithViewPager(viewPagerS);

        btnretour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayoutS.setTabTextColors(R.color.colorAccent,R.color.colorAccent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_deconnecter)
            logoutUser();
        else if (id==R.id.action_changepass)
            startActivity(new Intent(this,DashBoard.class));

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        if(auth.getCurrentUser() == null)
        {
            finish();
        }
    }

    @Override
    public int getIntFromAct() {
        return data;
    }
}
