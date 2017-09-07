package com.example.mosadi.chefschool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.mosadi.chefschool.navigation_fragments.navigation_edit_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_help_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_home_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_life_changed;
import com.example.mosadi.chefschool.navigation_fragments.navigation_meeting_request_fragment;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.StudentAccountContract;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager fragmentManager;
    private boolean updated;
    private int currentNav=0;//zero is home
    ActionBar bar;
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(currentNav!=0) {
                        bar.setTitle(R.string.home);
                        ft = fragmentManager.beginTransaction();
                        fragment = new navigation_home_fragment();
                        //moving down
                            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav=0;
                    }
                    return true;
                case R.id.edit_profile:
                    if(currentNav!=1) {
                        bar.setTitle(R.string.edit_profile);
                        ft = fragmentManager.beginTransaction();
                        fragment = new navigation_edit_fragment();
                        if (currentNav > 1) {//moving down
                            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                        } else {
                            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        }
                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav = 1;
                    }
                    return true;
                case R.id.life_changed:
                    if(currentNav!=2) {
                        bar.setTitle(R.string.my_life_has_changed);
                        ft = getSupportFragmentManager().beginTransaction();
                        fragment = new navigation_life_changed();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        if (currentNav > 2) {//moving down
                            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                        } else {
                            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        }
                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav=2;
                    }
                    return true;
                case R.id.help_me:
                    if(currentNav!=3) {
                        bar.setTitle(R.string.help_me);
                        ft = getSupportFragmentManager().beginTransaction();
                        fragment = new navigation_help_fragment();
                        if (currentNav > 3) {//moving down
                            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                        } else {
                            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        }
                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav=3;
                    }
                    return true;
                case R.id.meeting_request:
                    if(currentNav!=4) {

                        bar.setTitle(R.string.request_meeting);
                        ft = getSupportFragmentManager().beginTransaction();
                        fragment = new navigation_meeting_request_fragment();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav=4;
                    }
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = getSupportActionBar();
        bar.setTitle("Alumni App");
        bar.setHomeButtonEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.navigation_main);
        StudentAccountContract db = new StudentAccountContract(this);
        System.out.println("Insert");
        Log.d("Insert: ", "Inserting ..");
        Profile profile =new Profile("1","Nosipho", "Brodie","22","amy@khumalo.com");
        db.addProfile(new Profile("1","Nosipho", "Brodie","22","amy@khumalo.com"));
        db.addProfile(new Profile("2","Nosipho", "Brodie","22","amy@khumalo.com"));
        db.addProfile(new Profile("3","Nosipho2", "Brodie2","22","00706050"));
        Log.d("Reading: ", "Reading all contacts..");

         ft = fragmentManager.beginTransaction();
        fragment= new navigation_home_fragment();
        ft.replace(R.id.content, fragment);
        ft.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Profile cn = db.getContact(1);
        List<Profile> contacts = db.getAllProfile();
        //String log = "Id: "+cn.getUserID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone() + " ,Email: " + cn.getEmail();
        // Writing Contacts to log
        //Log.d("Name: ", log);
       /* List<Profile> contacts = db.getAllProfile();//all the user profiles
        for (Profile cn : contacts) {
            String log = "Id: "+cn.getUserID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone() + " ,Email: " + cn.getEmail();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
        */

    }



}
