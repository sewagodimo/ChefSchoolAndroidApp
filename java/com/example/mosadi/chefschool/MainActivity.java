package com.example.mosadi.chefschool;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView dialog_message;
    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (currentNav != 0) {
                        bar.setTitle(R.string.home);
                        ft = fragmentManager.beginTransaction();
                        fragment = new navigation_home_fragment();
                        //moving down
                        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);

                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav = 0;
                    }
                    return true;
                case R.id.edit_profile:
                    if (currentNav != 1) {
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
                    if (currentNav != 2) {
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
                        currentNav = 2;
                    }
                    return true;
                case R.id.help_me:
                    if (currentNav != 3) {
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
                        currentNav = 3;
                    }
                    return true;
                case R.id.meeting_request:
                    if (currentNav != 4) {

                        bar.setTitle(R.string.request_meeting);
                        ft = getSupportFragmentManager().beginTransaction();
                        fragment = new navigation_meeting_request_fragment();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                        ft.replace(R.id.content, fragment);
                        ft.commit();
                        currentNav = 4;
                    }
                    return true;
            }
            return false;
        }

    };
   private StudentAccountContract db;//=new StudentAccountContract(this);
    Profile user;
    String username=""; //This will come from the login table
    DialogInterface.OnClickListener dialogClickListener;
    AlertDialog.Builder builder ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = getSupportActionBar();
        bar.setTitle("Alumni App");
        bar.setHomeButtonEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        db = new StudentAccountContract(this);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.navigation_main);
        // db =
        // db.addProfile(new Profile("1", "Passion","Drive","image","ednecia@codedojo.com","+5553245","5","studying","09/04/95","SA","","",""));
        System.out.println("Insert");
        Log.d("Insert: ", "Inserting ..");
        List<Profile> cn = db.getAllProfile();
        dialog_message = (TextView) findViewById(R.id.dialog_message);
        user = cn.get(0);//all the user profiles
        //changeViewvalues();


        ft = fragmentManager.beginTransaction();
        fragment = new navigation_home_fragment();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout_button:
                builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);//change the theme each time
                builder.setTitle("You are about to log out");

                // Setting Dialog Message
                builder.setMessage("Are you sure");

                // Setting Icon to Dialog
                builder.setIcon(R.drawable.logo);
                builder.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        Toast.makeText(getApplicationContext(), "Logging out",
                                Toast.LENGTH_SHORT).show();
                        logout();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                        //   Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return true;
            case R.id.about:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void logout(){
        db.deleteAllProfiles();//remove all existing users
        db.close();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
public void dialog_method(String message, final String method){
    builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);//change the theme each time
    builder.setTitle(message);

    builder.setMessage("Are your contact details up to date?");

    // Setting Icon to Dialog
    builder.setIcon(R.drawable.logo);
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            if(method.equalsIgnoreCase("uniform")){
                helpSMS("I need transport money");
            }
            else    if(method.equalsIgnoreCase("contact")){
                helpSMS("Please contact me");
            }
        }


    });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

    builder.show();
}
    public void helpSMS(String headline){
        //String sent= "SMS sent";
       String message =headline+"\n"
                +"Student: "+user.getName()+" "+user.getSurname()+
                "\nContact on: "+user.getPhone()+
                "\nAlternative Contact on: "+user.getEmail();

        String toPhoneNumber =  "0764270487";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, message, null, null);

        } catch (Exception e) {
            makeToast("SMS sending failed");

        }

        makeToast("SMS sent");

    }
    public void makeToast(String text){
        Context context =this.getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
    }
    public void notifitcation(String message){
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);//change the theme each time
        builder.setTitle(message);

        // Setting Dialog Message
       // builder.setMessage("Are you sure");

        // Setting Icon to Dialog
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (currentNav != 0) {
                    bar.setTitle(R.string.home);
                    ft = fragmentManager.beginTransaction();
                    fragment = new navigation_home_fragment();
                    //moving down
                    ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);

                    ft.replace(R.id.content, fragment);
                    ft.commit();
                    currentNav = 0;
                }
            }
        });
        builder.show();

    }



}
