package com.example.mosadi.chefschool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mosadi.chefschool.navigation_fragments.navigation_edit_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_home_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_life_changed;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager fragmentManager;
    private boolean updated;
    private int currentNav=0;//zero is home
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    bar.setTitle(R.string.home);
                     ft = fragmentManager.beginTransaction();
                    fragment= new navigation_home_fragment();
                    ft.replace(R.id.content, fragment);
                    ft.commit();
                    return true;
                case R.id.edit_profile:
                    bar.setTitle(R.string.edit_profile);
                    ft= fragmentManager.beginTransaction();
                    fragment= new navigation_edit_fragment();
                    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    ft.replace(R.id.content, fragment);
                    ft.commit();
                    return true;
                case R.id.life_changed:
                    bar.setTitle(R.string.my_life_has_changed);
                     ft = getSupportFragmentManager().beginTransaction();
                    fragment= new navigation_life_changed();
                    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    ft.replace(R.id.content, fragment);
                    ft.commit();
                    return true;
                case R.id.help_me:

                    bar.setTitle(R.string.help_me);
                    return true;
                case R.id.meeting_request:

                    bar.setTitle(R.string.request_meeting);
                    return true;
            }
            return false;
        }

    };
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = getSupportActionBar();
        bar.setTitle("Alumni App");
        bar.setHomeButtonEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.navigation_main);
         ft = fragmentManager.beginTransaction();
        fragment= new navigation_home_fragment();
        ft.replace(R.id.content, fragment);
        ft.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
