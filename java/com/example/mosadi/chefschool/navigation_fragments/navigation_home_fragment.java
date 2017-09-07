package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.userinformation.Profile;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_home_fragment extends Fragment{
    TextView name;
    Profile user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.navigation_home, container, false);
        user=((MainActivity)this.getActivity()).getUser();
        name = (TextView) v.findViewById(R.id.display_name);
        // Inflate the layout for this fragment
        //change th content of the view stuff
        changedisplayValues(user);
        name= (TextView) v.findViewById(R.id.display_name);

        return v;
    }
    public void changedisplayValues(Profile user){
        name.setText(user.getName());

    }

}
