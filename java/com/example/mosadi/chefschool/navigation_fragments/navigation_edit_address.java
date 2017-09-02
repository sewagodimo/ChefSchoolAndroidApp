package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_edit_address extends Fragment {
    Button cancel,saveaddress;
    FragmentTransaction ft;
    ActionBar bar;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.navigation_edit_address, container, false);
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        cancel = (Button) v.findViewById(R.id.cancel_address_editing);
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        bar.setTitle(R.string.my_life_has_changed);
                        ft = getFragmentManager().beginTransaction();
                        fragment = new navigation_life_changed();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                });

        return v;
    }
}
