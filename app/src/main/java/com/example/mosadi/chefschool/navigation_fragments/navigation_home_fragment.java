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
    TextView surname;
    TextView phone;
    TextView email;
    TextView work_status;
    TextView dob;
    TextView addressview, display_class;
    Profile user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.navigation_home, container, false);
        user=((MainActivity)this.getActivity()).getUser();
        name = (TextView) v.findViewById(R.id.display_name);
        surname = (TextView) v.findViewById(R.id.display_surname);
        phone = (TextView) v.findViewById(R.id.display_phone);
        email = (TextView) v.findViewById(R.id.display_email);
        work_status = (TextView) v.findViewById(R.id.display_employment);
        dob = (TextView) v.findViewById(R.id.display_dob);
        addressview=(TextView)v.findViewById(R.id.display_address);
        display_class=(TextView)v.findViewById(R.id.display_class);
        // Inflate the layout for this fragment
        //change th content of the view stuff
        changedisplayValues(user);
        name= (TextView) v.findViewById(R.id.display_name);

        return v;
    }

    public void changedisplayValues(Profile user) {
        name.setText(user.getName());
        surname.setText(user.getSurname());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
        work_status.setText(user.getWork_status());

        String idno = user.getDob();
        if (idno.length() >= 6){
            idno = idno.substring(4, 6) + " /" + idno.substring(2, 4) + " /" + idno.substring(0, 2);
    }
        dob.setText(idno);
        String text= user.getCountry()+"\n"+user.getProvince()+"\n"+user.getCity()+"\n"+user.getSurburb();
        addressview.setText(text);
        addressview.setText(text);
        display_class.setText(user.getClass_number());
    }

}
