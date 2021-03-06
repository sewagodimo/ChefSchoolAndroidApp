package com.example.mosadi.chefschool;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mosadi.chefschool.loginfragments.ContactSchool;
import com.example.mosadi.chefschool.loginfragments.ForgotPassword;
import com.example.mosadi.chefschool.loginfragments.RegisterAgreement;
import com.example.mosadi.chefschool.loginfragments.RegisterConfirmation;
import com.example.mosadi.chefschool.loginfragments.RegisterFragment;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.SendMailTask;
import com.example.mosadi.chefschool.userinformation.StudentAccountContract;
import com.example.mosadi.chefschool.webserver.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;



    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private StudentAccountContract db;//=new StudentAccountContract(this);
    Profile user;
    boolean loggedin=false;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    RequestQueue queue ;
    ActionBar bar;
    // json object response url
   // private String urlJsonObj = "http://10.0.0.14:8000/Nontlantla%20Felani/d309ff35fa8a4213de44d771e5cc341dictchefs2017/";
    public static final String TAG = AppController.class.getSimpleName();
    public static  String URL;

    public static String SERVER="http://196.42.116.229:8000/";
    public static final String TOKEN="d309ff35fa8a4213de44d771e5cc341dictchefs2017/";
    String addURL= SERVER+"passfhjbfhj4893y54/ICTchefsNew/"+TOKEN;
    public static String postURL=SERVER+"passfhjbfhj4893y54/ICTchefmanage/d309ff35fa8a4213de44d771e5cc341dictchefs2017/";
    // json array response urlf
    private String urlJsonArry = "http:192.168.1.5//10.0.0.14:8000/1234/Nontlantla%20felani/d309ff35fa8a4213de44d771e5cc341dictchefs2017/";
    private String jsonResponse;
    TextView textResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The Action Bar
        bar = getSupportActionBar();
         bar.setTitle("Alumni App");
        bar.setHomeButtonEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        //The Default View
        setContentView(R.layout.activity_login);

        textResponse=(TextView) findViewById(R.id.textResponse);
        db=new StudentAccountContract(this);
        queue = Volley.newRequestQueue(this);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        List<Profile> cn= db.getAllProfile();
        db.close();
        //user = cn.get(0);//all the user profiles
        if(cn.size()>0){
            //basically they have logged int
            user = cn.get(0);
            String name= user.getName();
            URL=SERVER+user.getImage()+"/"+name+"%20" +user.getSurname()+"/"+TOKEN;
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        //changeViewvalues();

    }





    /**
     * Callback received when a permissions request has been completed.
     */



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        bar.setDisplayHomeAsUpEnabled(false);
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//      THE BUTTONS METHODS
public void onForgotPassword(View view){
    bar.setTitle("Forgot Password");
    bar.setDisplayHomeAsUpEnabled(true);
    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();; //animate transition and all that jazz
    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
    ForgotPassword forgot = new ForgotPassword();
    fragmentTransaction.replace(android.R.id.content, forgot);
    fragmentTransaction.commit();

}
public void onRegisterUser( View v) throws IOException {
    AutoCompleteTextView nametxt = (AutoCompleteTextView) findViewById(R.id.register_name);
    AutoCompleteTextView surnametxt = (AutoCompleteTextView) findViewById(R.id.register_surname);
    AutoCompleteTextView contacttxt = (AutoCompleteTextView) findViewById(R.id.register_contact);

    EditText regpasswordtxt = (EditText) findViewById(R.id.look);
    EditText confirmpasswordtxt = (EditText) findViewById(R.id.confirm_look);
    //assume that all the values are ok
    nametxt.setError(null);
    surnametxt.setError(null);
    contacttxt.setError(null);
    regpasswordtxt.setError(null);
    confirmpasswordtxt.setError(null);
    //Convert the values to strings
    final String name = nametxt.getText().toString();
    final String surname = surnametxt.getText().toString();
    final String contact = contacttxt.getText().toString();
    final String password = regpasswordtxt.getText().toString();
    String cpassword = confirmpasswordtxt.getText().toString();
    //whhat to look at if things go wrong
    boolean cancel = false;//cancel the whole transaction
    View focusView = null;
    //check for a valid name
    if (TextUtils.isEmpty(name)) {
        nametxt.setError("Your name is required");
        focusView = nametxt;
        cancel = true;// return basically
    } else if (TextUtils.isEmpty(surname)) {
        surnametxt.setError("Your surname is required");
        focusView = surnametxt;
        cancel = true;// return basically
    } else if (TextUtils.isEmpty(contact)) {
        contacttxt.setError("Your contact is required");
        focusView = contacttxt;
        cancel = true;// return basically
    } else if (TextUtils.isEmpty(password)) {
        regpasswordtxt.setError("Password is required");
        focusView = regpasswordtxt;
        cancel = true;// return basically
    } else if (!TextUtils.equals(cpassword, password)) {
        confirmpasswordtxt.setError("Passwords do not match");
        focusView = confirmpasswordtxt;
        cancel = true;// return basically
    }
    if (cancel) {
        // There was an error; don't attempt login and focus the first
        focusView.requestFocus();
    } else {
        // Show a progress spinner, and kick off a background task to
        String emailString = "Name : " + name
                + "  Surname: " + surname
                + "  Contact: " + contact
                + "  Password: " + password
                + "  Please contact them to let them know if they have been approved";
        //the email staff
        String fromEmail = "infinitystudentmail@gmail.com";
        String fromPassword = "students@infinity2017";
        String toEmails = "infinitystudentmail@gmail.com";
        List toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
        Log.i("SendMailActivity", "To List: " + toEmailList);
        String emailSubject = "A student would like to register their account\n";
        String emailBody = emailString;
        registerUser(name,surname,password,contact);
        new SendMailTask(this).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        //mail.createEmailMessage();
        //   mail.sendEmail();

        //server stuff
        bar.setTitle("Processing Registration");
       // this.registerUser(name,surname,password,contact); //nazo

    }
}
    public void notifitcation(String message){
        //just send notification that the evwent have aoocured
        AlertDialog.Builder builder ;
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);//change the theme each time
        builder.setTitle(message);
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
}

public void getNewPassword(View v){
    //send an SMS to the school
    bar.setTitle("Forgot Password");
    bar.setDisplayHomeAsUpEnabled(true);
    Context context = getApplicationContext();
    Toast toast = Toast.makeText(context, "New password request sent", Toast.LENGTH_SHORT);
    toast.show();
    toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);//for now
   TextView name = (TextView)  findViewById(R.id.forgot_name);
    TextView errormsg=(TextView)findViewById(R.id.forgot_password_error_message);
    if(name.toString().isEmpty()){
        errormsg.setText("Please enter your username or email");
                                       }
    else{
        //errormsg.setText("New password request sent to Admin, you will be contacted shortly");
       // errormsg.setTextColor(Color.green(1));
                                       }
    FragmentManager fm = this.getSupportFragmentManager();
    for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
        fm.popBackStack();
    }
}
public void onLoginRegister(View view){
    bar.setTitle("Terms and Conditions");
    bar.setDisplayHomeAsUpEnabled(true);
    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();; //animate transition and all that jazz
    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
    RegisterAgreement agreement = new RegisterAgreement();
    fragmentTransaction.replace(android.R.id.content, agreement);
    fragmentTransaction.commit();
}
    public void onAgreedRegister(View v){
        bar.setTitle("Register");
        bar.setDisplayHomeAsUpEnabled(true);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();; //animate transition and all that jazz
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        RegisterFragment register = new RegisterFragment();
        fragmentTransaction.replace(android.R.id.content, register);
        fragmentTransaction.commit();
    }
    public void onContactSchool(View v){
        bar.setTitle("School Contact Information");
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();; //animate transition and all that jazz
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ContactSchool contact = new ContactSchool();
        fragmentTransaction.replace(android.R.id.content, contact);
        fragmentTransaction.commit();
    }

    public void onLogin(View v){
        //This is their login
        attemptLogin();
        db.close();
        if(loggedin) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(email) && !isNameValid(email)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Please your name and surname");
            focusView = mEmailView;
            cancel = true;
        }
        if(!email.contains(" "))
        {
            mEmailView.setError("Use a space to separate name and surname");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            makeJsonObjectRequest( email, password);

            showProgress(false);


        }
    }

    private boolean isNameValid(String email) {

        return email.contains(" ");

    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 1;
    }
    public static String pass="";
    public static void resetURL(String name){
       // name = name.replace(" ","%20");
        URL= SERVER+pass+"/"+name+"/"+TOKEN;
    }
    public static void resetURL(String name,String password){

        URL= SERVER+pass+"/"+name+"/"+TOKEN;
    }
     void makeJsonObjectRequest(String name, String password) {
        //showpDialog(true);
       //so that the url can see it
         URL=SERVER+password+"/"+name+"/"+TOKEN;//AND THAT MY FRIEND IS A URL
         pass= password;
        //URL = "http://192.168.1.5:8000/1234/Nontlantla%20felani/d309ff35fa8a4213de44d771e5cc341dictchefs2017/";

         System.out.println("inside the make Json");
         showProgress(true);//try something
         Map<String, String> params = new HashMap<>();
         params.put("title", "log in");
         params.put("name", name);
         params.put("image",password);
         JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                 postURL,  new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "what is going on"+response.toString());

                try {
                    // Parsing json object response

                    String fullname= response.getString("name");
                    String idno=response.getString("id_no");
                    String user_id = response.getString("student_id");
                   JSONArray contact =  response.getJSONArray("contact_details");
                    JSONArray student_stuff = response.getJSONArray("student_info");//GET THE ARRAY
                    JSONObject student_object = (JSONObject) student_stuff.get(0);//convert array to object
                    String class_no = student_object.getString("class_no");//get object contantsS
                    String student_grad = student_object.getString("grad_or_student");
                    JSONArray employment_array = response.getJSONArray("employment_info");//get array
                    JSONObject employmentObject =(JSONObject) employment_array.get(0);//convert to object
                    String work_status = employmentObject.getString("current_employment");//get value
                    if(student_grad.equalsIgnoreCase("student")){
                        work_status=student_grad;
                    }
                    String address= response.getString("address");
                    String phone=contact.get(0).toString();
                    String other_contact="";
                    if(contact.length()>1){
                       other_contact=contact.get(1).toString();
                    }
                    String name =(fullname.substring(0,fullname.indexOf(" ")));
                   String surname=(fullname.substring(fullname.indexOf(" ")+1));
                    String country="";
                    String province="";
                    String city="";
                    String surburg=" ";
                    if(address.contains(",")){
                    String[] addresslist= address.split(",");
                        if(addresslist.length>=1){
                            country=addresslist[0];
                        }
                        if(addresslist.length>=2){
                            province=addresslist[1];
                        }
                        if(addresslist.length>=3){
                            city=addresslist[2];

                        }
                        if(addresslist.length>=4){
                            surburg=addresslist[3];
                        }
                    }
                    else{ country=address;}


                    db.deleteAllProfiles();//remove any existing user by clearing the table
//public Profile(String id, String name,String surname,String image,String email,String phone, String classnr, String work_status,
// String dob,String country, String province,String city,String surburb){//when a user register

                       //already sliced
                    if(class_no.equalsIgnoreCase("0")){
                        class_no=""; //if they have the deafult class number
                    }

                        Profile profile = new Profile(user_id,name,surname,pass,other_contact,phone,class_no,work_status,idno,country,province,city,surburg);
                   // textResponse.setText(profile.profileString()+"\n"+profile.addressString());
                    db.addProfile(profile);//add a user to the table
                    loggedin=true;

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    notifitcation("There was a problem connecting you to the internet");
                }
                //showDialog(0);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d(TAG, "Error:Response Error:  " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Volley error"+
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Response error listener");
                error.printStackTrace();
                    notifitcation( "Username and password do not match");

                // hide the progress dialog
               // hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }






    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */




    public void registerUser(final String name, final String surname, final String password, final String phone) throws IOException {


        Map<String, String> params = new HashMap<>();
        params.put("title", "register");
        params.put("name",name+" "+surname);
        params.put("contact",phone);
        params.put("password",password);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,postURL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LoginActivity.resetURL(name+" "+surname, password);//so they can login
                        Toast toast = Toast.makeText(getApplicationContext(), "Created your account", Toast.LENGTH_SHORT);
                        toast.show();
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("change Pass response -->> " + error.toString());
                        if (error.getMessage() == null) {
                            notifitcation("Server Error, Could not create account");
                        } else if (error.getMessage().contains("Connect")) {
                            notifitcation("Connection Error, Could not create account");
                        } else if (error.getMessage().contains("JSONException")) {
                            if (error.getMessage().contains("UNKNOWN REQUEST")) {
                                notifitcation("Unknown Request sent");
                            }
                            else{
                            //there is no appearent reason
                            LoginActivity.resetURL(name+" "+surname, password);//so they can login
                            Toast toast = Toast.makeText(getApplicationContext(), "Created your account", Toast.LENGTH_SHORT);
                            toast.show();
                                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now
                                // bar.setDispayHomeAsUpEnabled(true);
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                ; //animate transition and all that jazz
                                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                                RegisterConfirmation agreement = new RegisterConfirmation();
                                fragmentTransaction.replace(android.R.id.content, agreement);
                                fragmentTransaction.commit();
                        }
                        }
                        else {
                            notifitcation(" Could not create account");
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

}

