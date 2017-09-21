package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_meeting_request_fragment extends Fragment

{
    String reason="";
    int when=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.navigation_meetin_request, container, false);

        // Inflate the layout for this fragment
        return v;
    }




}
