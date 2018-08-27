package com.supming.test.fragment_main;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supming.test.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Info_Main extends Fragment {


    public Info_Main() {
        // Required empty public constructor
    }

    @Nullable
    @Override
        // Inflate the layout for this fragment
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_main_info,null);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return v;
    }

}
