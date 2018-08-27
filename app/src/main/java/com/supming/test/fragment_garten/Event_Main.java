package com.supming.test.fragment_garten;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.supming.test.EventsListAdapter.Events;
import com.supming.test.EventsListAdapter.EventsAdapter;
import com.supming.test.GartenActivity;
import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.KinderGarten.Gartens;
import com.supming.test.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class Event_Main extends Fragment {
 public interface SendData{
     public int getIntFromAct();

    }
    SendData sendData;
    public int data =0;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        data = sendData.getIntFromAct();
    }

    ArrayList<Events> gartens =new ArrayList<Events>();
    ArrayList<Events> garten_list = new ArrayList<Events>();

    private ArrayList<Events> meventsList;
    private DatabaseHelper mDBHelper;

    public Event_Main(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_garten_event,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Calendar c = Calendar.getInstance();
        //Toast.makeText(this,""+c.getTime().getDate(),Toast.LENGTH_SHORT).show();
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
        meventsList = mDBHelper.getListEvents(data,c);
        //mDBHelper.showNotify();
        final EventsAdapter adapter = new EventsAdapter(this.getActivity(), meventsList ,data);
        ListView lv = (ListView) v.findViewById(R.id.listevent);
        lv.setAdapter(adapter);

        //nofication

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
