package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.SendMailTask;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_meeting_request_fragment extends Fragment

{
    String reason="";
    int when=-1;
    Button send,cancel;
    Profile user;
    EditText rtext,wtext;
    ActionBar bar;
    Fragment fragment;
    FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.navigation_meetin_request, container, false);
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        rtext = (EditText) v.findViewById(R.id.rtext);//reasong for meeting
        wtext = (EditText) v.findViewById(R.id.wtext);
        //set the user
        user = ((MainActivity) this.getActivity()).getUser();
        //the buttons
        cancel = (Button) v.findViewById(R.id.clear_transport);
        send= (Button)v.findViewById(R.id.savebutton);
        bar.setTitle("Request a meeting");

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
                            rtext.setError("Why do you want to meet");
                            focusView = rtext;
                            focusView.requestFocus();
                        } else {

                            sendEmail();//so now we can make statements
                            bar.setTitle(R.string.my_life_has_changed);
                            ft = getFragmentManager().beginTransaction();
                            fragment = new navigation_life_changed();
                            //moving down
                            ft.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom);

                            ft.replace(R.id.content, fragment);
                            ft.commit();
                        }

                    }
                });

        return v;
    }


    String sendEmail()  {
        Log.i("SendMailActivity", "Send Button Clicked.");

        String fromEmail = "infinitystudentmail@gmail.com";
        String fromPassword = "students@infinity2017";
        String toEmails = "infinitystudentmail@gmail.com";
        List toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
        Log.i("SendMailActivity", "To List: " + toEmailList);
        String emailSubject = user.getName() +" "+user.getSurname()+" would like to request a meeting";
        String emailBody = composeEmail();
        new SendMailTask(((MainActivity) getActivity())).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        //mail.createEmailMessage();
        //   mail.sendEmail();
        return " Email Sent";
    }

    public String composeEmail() {

        return "I would like to request a meeting \n"
                + "Student: " + user.getName() + " " + user.getSurname()
                + "\nReason: " + rtext.getText().toString() +
                "\nWhen: " + wtext.getText().toString() +
                "\nContact on: " + user.getPhone() +
                "\nAlternative Contact on: " + user.getEmail();

    }
}


