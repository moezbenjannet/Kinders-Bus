package com.supming.test.fragment_main;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.supming.test.KinderGarten.DatabaseHelper;
//import com.supming.test.KinderGarten.DbMain;
import com.supming.test.KinderGarten.GartenListAdapter;
import com.supming.test.KinderGarten.Gartens;
import com.supming.test.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 16/11/2017.
 */

public class Home_fragment extends Fragment implements SearchView.OnQueryTextListener {


    private ArrayList<Gartens> mgartensList;
    private DatabaseHelper mDBHelper;
    private GartenListAdapter adapter ;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_home, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setHasOptionsMenu(true);
        mgartensList = getMgartensList();
        upDate(v);
    return v ;
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

    public void upDate(View view){
        adapter = new GartenListAdapter(this.getActivity(), mgartensList );
        lv = (ListView) view.findViewById(R.id.list1);
        lv.setAdapter(adapter);
    }

    public ArrayList<Gartens> getMgartensList(){
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
       return mDBHelper.getListGartens();
    }

    //search bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
// Do something when collapsed
                        adapter.setFilter(mgartensList);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
// Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final ArrayList<Gartens>  filteredModelList = filter(mgartensList, newText);
        adapter.setFilter(filteredModelList);
        lv.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        final ArrayList<Gartens>  filteredModelList = filter(mgartensList, query);
        adapter.setFilter(filteredModelList);
        lv.setAdapter(adapter);
        return true;
    }

    private ArrayList<Gartens>  filter(ArrayList<Gartens> models, String query) {
        query = query.toLowerCase();
        ArrayList<Gartens>  filteredModelList = new ArrayList<>();
        for (Gartens model : models) {
            final String name = model.getName().toLowerCase();
            final String city = model.getCity().toLowerCase();
            if (name.contains(query) || city.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}


