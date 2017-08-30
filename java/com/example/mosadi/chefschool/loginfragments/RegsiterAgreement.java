package com.example.mosadi.chefschool.loginfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/08/30.
 */

public class RegsiterAgreement extends Fragment {
    Button agreebtn;
    Button contactbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        agreebtn =(Button) getView().findViewById(R.id.agreebutton);
       agreebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Got to the Register Fragment
                RegisterFragment register = new RegisterFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.agreement_container, register);
                fragmentTransaction.commit();
            }
        });
        contactbtn =(Button) getView().findViewById(R.id.contact_school_btn);
        contactbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // GO To the contact school fragment
            }
        });
        return inflater.inflate(R.layout.login_register_agreement, container, false);
    }

}
