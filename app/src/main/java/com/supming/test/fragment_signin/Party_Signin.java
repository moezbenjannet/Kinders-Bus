package com.supming.test.fragment_signin;

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
import android.widget.ListView;
import android.widget.Toast;

import com.supming.test.EventsListAdapter.Events;
import com.supming.test.EventsListAdapter.EventsAdapter;
import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.PartyListAdapter.Party;
import com.supming.test.PartyListAdapter.PartyAdapter;
import com.supming.test.R;
import com.supming.test.fragment_garten.Event_Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class Party_Signin extends Fragment {
    public interface SendData{
        public int getIntFromAct();
    }
    Party_Signin.SendData sendData;
    public int data ;
    @Override
    //get data from gartenactivity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            sendData = (Party_Signin.SendData) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement SendData interface");
        }
        data = sendData.getIntFromAct();
    }

    private ArrayList<Party> meventsList;
    private DatabaseHelper mDBHelper;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin_party,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        //Toast.makeText(this.getActivity(), "Welcome " + data, Toast.LENGTH_SHORT).show();
        meventsList = mDBHelper.getListParty(data);
        final PartyAdapter adapter = new PartyAdapter(this.getActivity(), meventsList);
        ListView lv = (ListView) v.findViewById(R.id.listparty);
        lv.setAdapter(adapter);
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
