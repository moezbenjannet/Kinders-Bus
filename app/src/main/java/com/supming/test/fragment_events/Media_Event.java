package com.supming.test.fragment_events;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.supming.test.EventsListAdapter.SwipeDailyAdapter;
import com.supming.test.GridAdapter.MediaAdapter;
import com.supming.test.R;

import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class Media_Event extends Fragment{
    public interface SendData{
        public int getIntFromAct();
        public int getposFromAct();
        public int geteidFromAct();
    }
    Media_Event.SendData sendData;
    public int gid =0;
    public int eid =0;
    public int position =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (Media_Event.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        gid = sendData.getIntFromAct();
        eid = sendData.geteidFromAct();
        position = sendData.getposFromAct();
    }

    private GridView gv;
    private ViewPager viewPager;
    int x=0;
    SwipeDailyAdapter swipeDailyAdapter;
    private DatabaseReference mDatabaseRef;
    ProgressDialog progressDialog ;
    public ArrayList<String> mimage=new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_media,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait loading list image...");
        progressDialog.show();
        gv = (GridView) v.findViewById(R.id.gridview_media);

        //viewPager = (ViewPager) v.findViewById(R.id.mediaviewpager);
        //uploade
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("gartens");

        mDatabaseRef.child(""+gid).child("events").child(""+eid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    String img = snapshot.getValue(String.class);
                    progressDialog.dismiss();
                    //Toast.makeText(getContext(),"ok bb : "+ gid, Toast.LENGTH_SHORT).show();
                    mimage.add(img);
                }

                //Init adapter
                //swipeDailyAdapter = new SwipeDailyAdapter(getContext(),mimage);
                //viewPager.setAdapter(swipeDailyAdapter);
                gv.setAdapter(new MediaAdapter(getContext(),mimage));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
        //swipeDailyAdapter = new SwipeDailyAdapter(getContext(),mimage);
        //viewPager.setAdapter(swipeDailyAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    ImageView imageView = new ImageView(getActivity());
                    Picasso.with(getContext()).load(mimage.get(position)).into(imageView);
                    imageView.setMinimumHeight(720);
                imageView.setMinimumHeight(1024);
                    builder.setView(imageView);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                alertDialog.getWindow().setLayout(1024, 720);
            }
        });
        return v;
    }

}
