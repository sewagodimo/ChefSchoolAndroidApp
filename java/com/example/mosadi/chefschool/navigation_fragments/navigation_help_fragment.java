package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.navigation_fragments.help_me_fragments.help_transport_money;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_help_fragment extends Fragment {
    Button transport,other,unform;
    FragmentTransaction ft;
    ActionBar bar;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.navigation_help_me, container, false);
        transport=(Button) v.findViewById(R.id.transport_money);
        transport.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        //  bar.setTitle("");
                        ft = getFragmentManager().beginTransaction();
                        fragment = new help_transport_money();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                });




        return v;
    }
}
