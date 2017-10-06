package com.example.mosadi.chefschool.navigation_fragments.my_life_has_changed_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/09/08.
 */

public class message_unemployed extends Fragment{
    //For those who have lost their jobs
    Button address,other,betterjob;
    FragmentTransaction ft;
    ActionBar bar;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.navigation_life_changed, container, false);

        return v;}
}
