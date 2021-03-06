package com.example.mosadi.chefschool.navigation_fragments.help_me_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.navigation_fragments.navigation_help_fragment;
import com.example.mosadi.chefschool.userinformation.Profile;

/**
 * Created by Mosadi on 2017/09/08.
 */

public class contact_fragment extends Fragment {
    String message = "";
    EditText rtext, wtext;
    Profile user;
    ActionBar bar;
    Fragment fragment;
    FragmentTransaction ft;
    Button send, cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help_other, container, false);
        rtext = (EditText) v.findViewById(R.id.other_text);//reasong for meeting
        wtext = (EditText) v.findViewById(R.id.when_text);
        user = ((MainActivity) this.getActivity()).getUser();
        cancel = (Button) v.findViewById(R.id.clear_transport);
        send= (Button)v.findViewById(R.id.savebutton);
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        bar.setTitle("Please contact me");

        cancel.setOnClickListener(
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


                        //  reason.setError(null);
                        wtext.setError(null);
                        rtext.setError(null);
                        View focusView = null;
                        if (TextUtils.isEmpty(wtext.getText().toString())) {
                            wtext.setError("When do you want to meet");
                            focusView = wtext;
                            focusView.requestFocus();
                        } else if (TextUtils.isEmpty(rtext.getText().toString())) {
                            rtext.setError("Phone number missing");
                            focusView = rtext;
                            focusView.requestFocus();
                        } else {

                            ((MainActivity)getActivity()).notifitcation(sendSMS());//so now we can make statements
                            bar.setTitle(R.string.my_life_has_changed);
                            ft = getFragmentManager().beginTransaction();
                            fragment = new navigation_help_fragment();
                            fragment = new navigation_help_fragment();
                            //moving down
                            ft.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom);

                            ft.replace(R.id.content, fragment);
                            ft.commit();
                        }

                    }
                });

        return v;
    }

    public void onClear() {

        rtext.setText("");
        wtext.setText("");
    }

    public String sendSMS() {
        String sent = "SMS sent";
        message = composeMessage();

        String toPhoneNumber = "0764270487";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, message, null, null);

        } catch (Exception e) {
            sent = "SMS sending failed";

        }

        return sent;

    }

    public String composeMessage() {

        return "Contact me \n"
                + "Student: " + user.getName() + " " + user.getSurname()
                + "\nReason: " + rtext.getText().toString() +
                "\nWhen: " + wtext.getText().toString() +
                "\nContact on: " + user.getPhone() +
                "\nAlternative Contact on: " + user.getEmail();

    }
}

