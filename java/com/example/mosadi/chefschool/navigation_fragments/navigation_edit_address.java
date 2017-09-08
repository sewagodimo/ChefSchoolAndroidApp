package com.example.mosadi.chefschool.navigation_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.userinformation.Profile;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_edit_address extends Fragment {
    Button cancel,saveaddress;
    FragmentTransaction ft;
    ActionBar bar;
    Fragment fragment;
    EditText co,ci,pr,sub;
    //String country,city,prov,suburb;
    Profile user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.navigation_edit_address, container, false);
        saveaddress= (Button)v.findViewById(R.id.saveaddress);
        user=((MainActivity)this.getActivity()).getUser();
        initialise(v);
        saveaddress.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        makeToast("Saved changes");
                       onUpdate();
                    }
                });
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
    public void makeToast(String text){
        Context context =getActivity().getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
    }
    public void initialise(View v){
        //initialise text views
        co=(EditText) v.findViewById(R.id.edit_country);
        ci=(EditText) v.findViewById(R.id.edit_city);
        pr=(EditText) v.findViewById(R.id.edit_province);
        sub=(EditText) v.findViewById(R.id.edit_surburb);
        //set values based on what is in the user object
        co.setText(user.getCountry());
        ci.setText(user.getCity());
        pr.setText(user.getProvince());
        sub.setText(user.getSurburb());
    }
    public void onUpdate(){
        ((MainActivity)this.getActivity()).updateAddress(co.getText().toString(),
                pr.getText().toString(),
                ci.getText().toString()
        ,sub.getText().toString());
    }


}
