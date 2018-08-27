package com.supming.test.fragment_events;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.supming.test.EventsListAdapter.Events;
import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.R;
import com.supming.test.fragment_garten.Event_Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class Description_Event extends Fragment {
    public interface SendData{
        public int getIntFromAct();
        public int getposFromAct();
    }
    Description_Event.SendData sendData;
    public int gid =0;
    public int position =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (Description_Event.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        gid = sendData.getIntFromAct();
        position = sendData.getposFromAct();
    }

    private TextView title,date,time,place,description;
    private ImageView image;
    private ArrayList<Events> meventsList;
    private DatabaseHelper mDBHelper;
    Calendar c;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_description,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        c = Calendar.getInstance();
        meventsList = geteventslist(gid);
        description = (TextView) v.findViewById(R.id.eventdescription);
        place = (TextView) v.findViewById(R.id.eventplace);
        date = (TextView) v.findViewById(R.id.eventdate);
        title = (TextView) v.findViewById(R.id.eventname);
        time = (TextView) v.findViewById(R.id.eventtime);
        image = (ImageView) v.findViewById(R.id.eventimage);
        description.setText(getdescriptionevent(gid,position+1));
        time.setText(meventsList.get(position).geteventheure()+" : "+meventsList.get(position).geteventminute());
        place.setText(meventsList.get(position).geteventplace());
        date.setText(meventsList.get(position).geteventjour()+"/"+meventsList.get(position).geteventmois()+"/"+meventsList.get(position).geteventannee());
        title.setText(meventsList.get(position).geteventtitle());
        Bitmap bitmap = BitmapFactory.decodeByteArray(meventsList.get(position).geteventsimage() , 0 , meventsList.get(position).geteventsimage().length);
        image.setImageBitmap(bitmap);
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
    public ArrayList<Events> geteventslist(int gid){
        mDBHelper = new DatabaseHelper(this.getActivity());
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
        //Get product list in db when db exists
        return mDBHelper.getListEvents(gid,c);
    }
    public String getdescriptionevent(int gid,int eid){
        mDBHelper = new DatabaseHelper(this.getActivity());
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
        //Get product list in db when db exists
        return mDBHelper.getEventdescription(gid, eid);
    }
}
