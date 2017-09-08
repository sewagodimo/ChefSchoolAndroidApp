package com.example.mosadi.chefschool.navigation_fragments.help_me_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.navigation_fragments.navigation_help_fragment;
import com.example.mosadi.chefschool.userinformation.Profile;

/**
 * Created by Mosadi on 2017/09/08.
 */

public class help_transport_money extends Fragment {
    Button clear,send;
    EditText reason,amount,location,phone;
    Profile user;
    ActionBar bar;
    Fragment fragment;
    FragmentTransaction ft;
    String message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        user=((MainActivity)this.getActivity()).getUser();// so that we can also send their information
        View v= inflater.inflate(R.layout.help_transport_money, container, false);
        send= (Button)v.findViewById(R.id.send_transport_money);
        reason=(EditText) v.findViewById(R.id.edit_reason);
        amount=(EditText) v.findViewById(R.id.edit_reason);
        phone=(EditText) v.findViewById(R.id.edit_number);
        phone.setText(user.getPhone());
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        bar.setTitle("Transport Money");
        location=(EditText) v.findViewById(R.id.edit_reason);
        clear= (Button)v.findViewById(R.id.clear_transport);

        clear.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address

                        bar.setTitle(R.string.my_life_has_changed);
                        ft = getFragmentManager().beginTransaction();
                        fragment = new navigation_help_fragment();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                }
        );
        send.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address


                        reason.setError(null);
                        location.setError(null);
                        amount.setError(null);
                        View focusView = null;
                        if(TextUtils.isEmpty(reason.getText().toString())){
                            reason.setError("Please tell why do not have transport money");
                            focusView = reason;
                            focusView.requestFocus();
                        }
                        else if(TextUtils.isEmpty(location.getText().toString())){
                            location.setError("Please tell us where you are");
                            focusView = location;
                            focusView.requestFocus();
                        }
                        else if(TextUtils.isEmpty(amount.getText().toString())){
                            amount.setError("Please tell us where you are");
                            focusView = amount;
                            focusView.requestFocus();
                        }
                        else{
                            onClear();
                            sendSMS();
                            makeToast("Sending SMS...");
                        }

                    }
                }
        );

        return v;
    }
    public void makeToast(String text){
        Context context =getActivity().getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
    }

    public void onClear(){
        reason.setText("");
        amount.setText("");
        location.setText("");
    }
    public String sendSMS(){
        String sent= "SMS sent";

        return sent;

    }



}