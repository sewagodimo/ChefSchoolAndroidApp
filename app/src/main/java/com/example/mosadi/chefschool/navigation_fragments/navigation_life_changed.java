package com.example.mosadi.chefschool.navigation_fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.navigation_fragments.my_life_has_changed_fragments.message_better_job;
import com.example.mosadi.chefschool.navigation_fragments.my_life_has_changed_fragments.message_need_job;
import com.example.mosadi.chefschool.navigation_fragments.my_life_has_changed_fragments.message_other;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.SendMailTask;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_life_changed extends Fragment {
    Button address,other,betterjob,need_job,lost_job;
    FragmentTransaction ft;
    ActionBar bar;
    Fragment fragment;
    Profile user;
    AlertDialog.Builder builder ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.navigation_life_changed, container, false);
        bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
       user= ((MainActivity) this.getActivity()).getUser();
        bar.setTitle(R.string.my_life_has_changed);
        address = (Button) v.findViewById(R.id.changeAddress);
        need_job = (Button) v.findViewById(R.id.needjob);
        betterjob=  (Button) v.findViewById(R.id.betterjobbutton);
        other= (Button)v.findViewById(R.id.other);
        lost_job= (Button)v.findViewById(R.id.lost_job);

        lost_job.setOnClickListener(
                new View.OnClickListener() {
            public void onClick(View v) {
                // WHEN the user clicks that they want to get a new address
                builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);//change the theme each time
                builder.setTitle("Notifying the school that you have lost your job");

                // Setting Dialog Message
                builder.setMessage("Are you sure?");

                //queue.add(postRequest);


                // Setting Icon to Dialog
                builder.setIcon(R.drawable.logo);
                builder.setPositiveButton("Notify", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendEmail(); //whoop whoop

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       // DO nothing
                    }
                });
                builder.show();


            }
        });
        address.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        bar.setTitle("Upadate Address");
                        ft = getFragmentManager().beginTransaction();
                        fragment = new navigation_edit_address();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                });
        betterjob.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        bar.setTitle("I found a better Job");
                        ft = getFragmentManager().beginTransaction();
                        fragment = new message_better_job();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                });
        other.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        bar.setTitle("Something Else has happened");
                        ft = getFragmentManager().beginTransaction();
                        fragment = new message_other();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                    }
                });
        need_job.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address
                        bar.setTitle("I need a job");
                        ft = getFragmentManager().beginTransaction();
                        fragment = new message_need_job();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
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
        String emailSubject = user.getName() +" "+user.getSurname()+" needs a job";
        String emailBody = composeEmail();
        ((MainActivity)this.getActivity()).changedwork("");
        new SendMailTask(((MainActivity) getActivity())).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        //mail.createEmailMessage();
        //   mail.sendEmail();
        return " Email Sent";
    }

    public String composeEmail() {

        return "I lost my job \n"
                + "Student: " + user.getName() + " " + user.getSurname()
                +"\n Contact on: " + user.getPhone() +
                "\n Alternative Contact on: " + user.getEmail();

    }
}
