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

import com.example.mosadi.chefschool.navigation_fragments.navigation_home_fragment;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    bar.setTitle(R.string.home);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    fragment= new navigation_home_fragment();
                    ft.replace(R.id.content, fragment);
                    ft.commit();
                    return true;
                case R.id.edit_profile:

                    bar.setTitle(R.string.edit_profile);
                    return true;
                case R.id.life_changed:

                    bar.setTitle(R.string.my_life_has_changed);
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



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
