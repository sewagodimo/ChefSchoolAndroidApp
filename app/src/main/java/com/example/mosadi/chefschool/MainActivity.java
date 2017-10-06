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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mosadi.chefschool.navigation_fragments.navigation_edit_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_help_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_home_fragment;
import com.example.mosadi.chefschool.navigation_fragments.navigation_life_changed;
import com.example.mosadi.chefschool.navigation_fragments.navigation_meeting_request_fragment;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.StudentAccountContract;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{


    private Fragment fragment;
    private FragmentManager fragmentManager;
    private boolean updated;
    private int currentNav=0;//zero is home
    ActionBar bar;
    TextView dialog_message;
    FragmentTransaction ft;
    RequestQueue queue ;

  //  public static  String URL="http://10.0.0.14:8000/Nontlantla%20Felani/students/";

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
        queue = Volley.newRequestQueue(this);


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
    public void updateProfile(final String name, final String surname,final String image,final String email,final String phone, final String idno, final String classno)  {


            Map<String, String> params = new HashMap<>();
            params.put("title", "edit profile");
            params.put("id", user.getUserID());
            params.put("new_name",name+" "+surname);
            params.put("image",user.getImage());//the image is the password, I just dontlike using it explicitly
            params.put("name",user.getName()+" "+user.getSurname());
            params.put("contact",phone);
            params.put("other_contact",email);
            params.put("id_no",idno);
        params.put("class",classno);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,LoginActivity.postURL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("change Pass response -->> " + error.toString());
                        if (error.getMessage() == null) {
                            notifitcation("Server Error");
                        } else if (error.getMessage().contains("Connect")) {
                            notifitcation("Connection Error");
                        } else if (error.getMessage().contains("JSONException")) {
                            if (error.getMessage().contains("UNKNOWN REQUEST")) {
                                notifitcation("Unknown Request sent");
                            }
                            else{
                            username = name;
                            user.edit_profile(name, surname, image, email, phone,idno,classno, db);
                            LoginActivity.resetURL(name + " " + surname);//reset the URL
                            db.updateProfile(user);
                            System.out.println(user.profileString());
                            //user.setWork_status("not working");
                            Toast toast = Toast.makeText(getApplicationContext(), "Updated your details", Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now
                        }
                    }
                        else{
                            notifitcation(error.getMessage() );
                        }
                    }
                }

                );

        queue.getCache().clear();
       request.setRetryPolicy(new

                DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );

        queue.add(request);
        //reload(name+" "+surname,image);
        }
public void changedwork(final String new_job){
    Map<String, String> params = new HashMap<>();
    if(new_job.equalsIgnoreCase("")){
    params.put("title", "lost job");}
    else{
        params.put("title", "new job");
    }
    params.put("image",user.getImage());//the image is the password, I just dontlike using it explicitly
    params.put("name",user.getName()+" "+user.getSurname());
    params.put("new job",new_job);
    JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,LoginActivity.postURL, new JSONObject(params),
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    user.update_work(new_job,db);
                    Toast toast = Toast.makeText(getApplicationContext(), "Updated..", Toast.LENGTH_SHORT);
                    toast.show();
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for no
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("change Pass response -->> " + error.toString());
                    if(error.getMessage()==null){
                        notifitcation("Server Error" );
                    }
                    else if(error.getMessage().contains("Connect")){
                        notifitcation("Connection Error" );
                    }
                     else if (error.getMessage().contains("UNKNOWN REQUEST")) {
                        notifitcation("Unknown Request sent");
                    }
                    else if(error.getMessage().contains("JSONException"))
                    {


                        user.update_work(new_job,db);
                        Toast toast = Toast.makeText(getApplicationContext(), "Updated..", Toast.LENGTH_SHORT);
                        toast.show();
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now
                    }
                    else{
                        notifitcation("check your internet connection" );
                    }
                }
            }

    );

    queue.getCache().clear();
    request.setRetryPolicy(new

            DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
    );

    queue.add(request);

}



    public void updateAddress(final String co,final String pr,final String ci, final String sub){
        String address= co+","+pr+","+ci+","+sub;
        Map<String, String> params = new HashMap<>();
        params.put("title", "update address");
        params.put("image",user.getImage());//the image is the password, I just dontlike using it explicitly
        params.put("name",user.getName()+" "+user.getSurname());
        params.put("address",address);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,LoginActivity.postURL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        user.edit_address(co,pr,ci,sub,db);
                        //No nrrf
                        db.updateAddress(user);
                        System.out.println(user.addressString());
                        //user.setWork_status("not working");
                        Toast toast = Toast.makeText(getApplicationContext(), "Changed Address", Toast.LENGTH_SHORT);
                        toast.show();
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("change Pass response -->> " + error.toString());
                        if(error.getMessage()==null){
                            notifitcation("Server Error" );
                        }
                        else if(error.getMessage().contains("Connect")){
                            notifitcation("Connection Error" );
                        }
                        else if (error.getMessage().contains("UNKNOWN REQUEST")) {
                            notifitcation("Unknown Request sent");
                        }
                        else if(error.getMessage().contains("JSONException"))
                        {
                            //there is no appearent reason
                            user.edit_address(co,pr,ci,sub,db);
                            //No nrrf
                            db.updateAddress(user);
                            System.out.println(user.addressString());
                            //user.setWork_status("not working");
                            Toast toast = Toast.makeText(getApplicationContext(), "Changed Address", Toast.LENGTH_SHORT);
                            notifitcation(error.getMessage() );
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now
                        }
                        else{
                            notifitcation("check your internet connection" );
                        }
                    }
                }

        );

        queue.getCache().clear();
        request.setRetryPolicy(new

                DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );

        queue.add(request);
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
                helpSMS("I need a new uniform");
                notifitcation("Uniform request sent");
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

            }
        });
        builder.show();

    }



}
