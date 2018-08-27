package com.supming.test.fragment_garten;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.supming.test.EventsListAdapter.Events;
import com.supming.test.EventsListAdapter.Schedule;
import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule_Main extends Fragment {
    public interface SendData{
        public int getIntFromAct();

    }
    Schedule_Main.SendData sendData;
    public int data =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (Schedule_Main.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        data = sendData.getIntFromAct();
    }

    private Schedule schedule;
    private DatabaseHelper mDBHelper;
    private TextView monday,tuesday,wednesday,thurday,friday,saturday;


    public Schedule_Main() {
        // Required empty public constructor
    }

    @Nullable
    @Override
        // Inflate the layout for this fragment
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_garten_schedule,null);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            schedule = getScheduleListe();
            monday = (TextView) v.findViewById(R.id.mondayedit);
            tuesday = (TextView) v.findViewById(R.id.tuesdayedit);
            wednesday = (TextView) v.findViewById(R.id.wednesdayedit);
            thurday = (TextView) v.findViewById(R.id.thursdayedit);
            friday = (TextView) v.findViewById(R.id.fridayedit);
            saturday = (TextView) v.findViewById(R.id.saturdayedit);

            monday.setText(schedule.getSmonday());
            tuesday.setText(schedule.getStuesday());
            wednesday.setText(schedule.getSwednesday());
            thurday.setText(schedule.getSthursday());
            friday.setText(schedule.getSfriday());
            saturday.setText(schedule.getSsaturday());

            return v;
    }

    private Schedule getScheduleListe(){
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
        return mDBHelper.getSchedule(data);
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
