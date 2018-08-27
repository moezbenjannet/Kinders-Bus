package com.supming.test.fragment_signin;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.supming.test.GridAdapter.MediaAdapter;
import com.supming.test.GridAdapter.WebAdapter;
import com.supming.test.R;

import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class Live_Signin extends Fragment {
    public interface SendData{
        public int getIntFromAct();
    }
    Live_Signin.SendData sendData;
    public int data =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (Live_Signin.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        data = sendData.getIntFromAct();
    }


    GridView gv ;
    public ArrayList<String> mlive= new ArrayList<>();
    private DatabaseReference mDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin_live,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("gartens");

        mDatabaseRef.child(""+data).child("live").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    String img = snapshot.getValue(String.class);
                    //Toast.makeText(getContext(),img, Toast.LENGTH_SHORT).show();
                    mlive.add(img);
                }
                gv.setAdapter(new WebAdapter(getContext(),mlive));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        gv = (GridView) v.findViewById(R.id.gridview_live);
        gv.setAdapter(new WebAdapter(this.getActivity(),mlive));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(),"ok bb :sdsd ", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
