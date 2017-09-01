package com.example.mosadi.chefschool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    mTextMessage.setText(R.string.home);
                    return true;
                case R.id.edit_profile:
                    mTextMessage.setText(R.string.edit_profile);
                    return true;
                case R.id.life_changed:
                    mTextMessage.setText(R.string.my_life_has_changed);
                    return true;
                case R.id.help_me:
                    mTextMessage.setText(R.string.help_me);
                    return true;
                case R.id.meeting_request:
                    mTextMessage.setText(R.string.request_meeting);
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
        setContentView(R.layout.navigation_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
