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
   private StudentAccountContract db;//=new StudentAccountContract(this);
    Profile user;
    String username="Passion"; //This will come from the login table

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = getSupportActionBar();
        bar.setTitle("Alumni App");
        bar.setHomeButtonEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        db=new StudentAccountContract(this);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.navigation_main);
       // db =
       // db.addProfile(new Profile("1", "Passion","Drive","image","ednecia@codedojo.com","+5553245","5","studying","09/04/95","SA","","",""));
        System.out.println("Insert");
        Log.d("Insert: ", "Inserting ..");
        List<Profile> cn= db.getAllProfile();
        user = cn.get(0);//all the user profiles
        //changeViewvalues();


         ft = fragmentManager.beginTransaction();
        fragment= new navigation_home_fragment();
        ft.replace(R.id.content, fragment);
        ft.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    public Profile getUser(){
        return user;
    }
    public void updateProfile(String name, String surname,String image,String email,String phone,String dob){
        username=name;
        user.edit_profile(name,surname,image,email,phone,dob,db);
        db.updateProfile(user);
        System.out.println(user.profileString());
    }
    public void updateAddress(String co,String pr,String ci, String sub){
        user.edit_address(co,pr,ci,sub,db);

    }


}
