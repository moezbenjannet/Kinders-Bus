package com.supming.test.fragment_garten;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.supming.test.EventsListAdapter.SwipeDailyAdapter;
import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.MainActivity;
import com.supming.test.R;
import com.supming.test.login_garten.Loginactivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class HomeGarten_Main extends Fragment {
    public interface SendData{
        public int getIntFromAct();

    }
    HomeGarten_Main.SendData sendData;
    public int data =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (HomeGarten_Main.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        data = sendData.getIntFromAct();
    }

    Button sign_in ;
    TextView description,contactus;
    DatabaseHelper mDBHelper ;
    SwipeDailyAdapter swipeDailyAdapter;
    ViewPager viewPager;
    private DatabaseReference mDatabaseRef;
    ProgressDialog progressDialog;

    public ArrayList<String> mimage = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_garten_home,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mDBHelper = new DatabaseHelper(this.getActivity());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Download");
        progressDialog.setMessage("Please wait loading image...");
        progressDialog.show();

        //Check exists database
        File database = getActivity().getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this.getActivity())) {
                Toast.makeText(this.getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getActivity(), "Problem has occured", Toast.LENGTH_SHORT).show();
            }
        }

        sign_in = (Button) v.findViewById(R.id.Sign_IN) ;
        description = (TextView) v.findViewById(R.id.garten_description);
        contactus = (TextView) v.findViewById(R.id.garten_contactus);
        viewPager = (ViewPager) v.findViewById(R.id.gartenviewpager);
        //swipeDailyAdapter = new SwipeDailyAdapter(getContext(),mimage);
        //viewPager.setAdapter(swipeDailyAdapter);

        description.setText(mDBHelper.getGartendescription(data));
        contactus.setText(mDBHelper.getGartencontact(data));

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), Loginactivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        //uploade
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("gartens");

        mDatabaseRef.child(""+data).child("daily photo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    String img = snapshot.getValue(String.class);

                    //Toast.makeText(getContext(),"ok bb : "+ data, Toast.LENGTH_SHORT).show();
                    mimage.add(img);
                }

                //Init adapter
                swipeDailyAdapter = new SwipeDailyAdapter(getContext(),mimage);
                viewPager.setAdapter(swipeDailyAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        return v;
    }

    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DB_PATH + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
