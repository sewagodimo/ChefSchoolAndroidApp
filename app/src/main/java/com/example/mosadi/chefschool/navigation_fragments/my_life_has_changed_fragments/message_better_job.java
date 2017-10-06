package com.example.mosadi.chefschool.navigation_fragments.my_life_has_changed_fragments;

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
import com.example.mosadi.chefschool.navigation_fragments.navigation_life_changed;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.SendMailTask;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mosadi on 2017/09/08.
 */

public class message_better_job extends Fragment {
    String message = "";
    EditText old_job,new_job,offer;
    Profile user;
    ActionBar bar;
    Fragment fragment;
    FragmentTransaction ft;
    Button send, cancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.better_job_fragment, container, false);
        bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        offer = (EditText) v.findViewById(R.id.new_offer);//reasong for meeting
        new_job = (EditText) v.findViewById(R.id.new_job);
        old_job = (EditText) v.findViewById(R.id.old_job);

        //set the user
        user = ((MainActivity) this.getActivity()).getUser();
        old_job.setText(user.getWork_status());
        //the buttons
        cancel = (Button) v.findViewById(R.id.cancel);
        send = (Button) v.findViewById(R.id.savebutton);
        bar.setTitle("Contact us");
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
                        new_job.setError(null);
                        old_job.setError(null);
                        offer.setError(null);
                        View focusView = null;
                        if (TextUtils.isEmpty(offer.getText().toString())) {
                            offer.setError("required field");
                            focusView = offer;
                            focusView.requestFocus();
                        } else if (TextUtils.isEmpty(old_job.getText().toString())) {
                            old_job.setError("required field");
                            focusView = old_job;
                            focusView.requestFocus();
                        }
                        else {

                           sendEmail();//so now we can make statements
                           // onClear();
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
        String emailSubject = user.getName() +" "+user.getSurname()+" found a better job";
        String emailBody = composeEmail();
       new SendMailTask  (((MainActivity) getActivity())).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        //mail.createEmailMessage();
     //   mail.sendEmail();
        ((MainActivity)getActivity()).notifitcation("Notification sent");
        return " Email Sent";
    }
    public String composeEmail(){
        return "Dear Melinda\nI hope this email finds you well\n\n"
                +"\n\nMy name is " +user.getName()+" "+user.getSurname()
                +"\nI am proud to inform you that I have found a new job"
                +"\n I graduated in the class of "+user.getClass_number()
                +"\nI am currently working at " +user.getWork_status()
                +"\nI got an offer from " +new_job.getText().toString()
                +"\nThey are offering me " +offer.getText().toString()
                +"\nPlease contact me  on " +user.getPhone() +" or "+user.getEmail()
                +"\n\n\n\nKind Regards"
                ;

    }


}
