package com.example.mosadi.chefschool.loginfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/08/31.
 */

public class RegisterConfirmation extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.login_confirm_registration, container, false);
    }
}
