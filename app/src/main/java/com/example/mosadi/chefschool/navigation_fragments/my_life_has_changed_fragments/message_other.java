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

public class message_other extends Fragment {

    EditText new_job,position,manager,contact;
    Profile user;
    ActionBar bar;
    Fragment fragment;
    FragmentTransaction ft;
    Button send, cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_job, container, false);
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        new_job= (EditText) v.findViewById(R.id.new_job);//reasong for meeting
        position= (EditText) v.findViewById(R.id.position);//reasong for meeting
        manager = (EditText) v.findViewById(R.id.manager);
        contact = (EditText) v.findViewById(R.id.contact);
        //set the user
        user = ((MainActivity) this.getActivity()).getUser();
        //the buttons
        cancel = (Button) v.findViewById(R.id.cancel);
        send= (Button)v.findViewById(R.id.send);
        bar.setTitle("I have a new new job");

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
                        manager.setError(null);
                        position.setError(null);
                        View focusView = null;
                        if (TextUtils.isEmpty(new_job.getText().toString())) {
                            new_job.setError("Where is your new job");
                            focusView = new_job;
                            focusView.requestFocus();
                        } else if (TextUtils.isEmpty(manager.getText().toString())) {
                            manager.setError("Who is the manager where you work");
                            focusView =manager;
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
        String emailSubject = user.getName() +" "+user.getSurname()+" got a new job";
        String emailBody = composeEmail();

        new SendMailTask(((MainActivity) getActivity())).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        //mail.createEmailMessage();
        //   mail.sendEmail();
        return " Email Sent";
    }

    public String composeEmail() {
        ((MainActivity)this.getActivity()).changedwork(new_job.getText().toString());
        return "I have found a new JOB! \n"
                + "Student: " + user.getName() + " " + user.getSurname()
                + "\nCompany: " + new_job.getText().toString() +
                "\nPosition: " + position.getText().toString() +
                "\nMananger: " + manager.getText().toString() +
                "\nContact-Manager: " + contact.getText().toString() +
                "\nContact on: " + user.getPhone() +
                "\nAlternative Contact on: " + user.getEmail();

    }
}

