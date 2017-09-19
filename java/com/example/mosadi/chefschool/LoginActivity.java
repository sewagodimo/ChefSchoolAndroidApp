package com.example.mosadi.chefschool;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mosadi.chefschool.loginfragments.ContactSchool;
import com.example.mosadi.chefschool.loginfragments.ForgotPassword;
import com.example.mosadi.chefschool.loginfragments.RegisterAgreement;
import com.example.mosadi.chefschool.loginfragments.RegisterConfirmation;
import com.example.mosadi.chefschool.loginfragments.RegisterFragment;
import com.example.mosadi.chefschool.userinformation.Profile;
import com.example.mosadi.chefschool.userinformation.StudentAccountContract;
import com.example.mosadi.chefschool.webserver.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private StudentAccountContract db;//=new StudentAccountContract(this);
    Profile user;
    String username="student";
    String password="123infinityschool";
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    ActionBar bar;
    // json object response url
    private String urlJsonObj = "http://10.0.0.12:8000/Nontlantla%20Felani/students/";
    public static final String TAG = AppController.class.getSimpleName();
    // json array response url
    private String urlJsonArry = "http://localhost:8000/Nontlantla%20Felani/students/";
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
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

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


     /*   Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
*/
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

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
public void onRegisterUser( View v){
    //fetch the values from the view
    AutoCompleteTextView nametxt =(AutoCompleteTextView) findViewById(R.id.register_name);
    AutoCompleteTextView surnametxt=(AutoCompleteTextView) findViewById(R.id.register_surname);
    AutoCompleteTextView contacttxt=(AutoCompleteTextView) findViewById(R.id.register_contact);;
    EditText regpasswordtxt = (EditText) findViewById(R.id.look);
    EditText confirmpasswordtxt = (EditText) findViewById(R.id.confirm_look);
    //assume that all the values are ok
    nametxt.setError(null);
    surnametxt.setError(null);
    contacttxt.setError(null);
    regpasswordtxt.setError(null);
    confirmpasswordtxt.setError(null);
    //Convert the values to strings
    String name = nametxt.getText().toString();
    String surname = surnametxt.getText().toString();
    String contact = contacttxt.getText().toString();
    String password= regpasswordtxt.getText().toString();
    String cpassword= confirmpasswordtxt.getText().toString();
    //whhat to look at if things go wrong
    boolean cancel = false;//cancel the whole transaction
    View focusView = null;
    //check for a valid name
    if(TextUtils.isEmpty(name)){
        nametxt.setError("Your name is required");
        focusView = nametxt;
        cancel=true;// return basically
    }
    else  if(TextUtils.isEmpty(surname)){
        surnametxt.setError("Your surname is required");
        focusView= surnametxt;
        cancel=true;// return basically
    }
    else  if(TextUtils.isEmpty(contact)){
        contacttxt.setError("Your contact is required");
        focusView=contacttxt;
        cancel=true;// return basically
    }
    else  if(TextUtils.isEmpty(password)){
        regpasswordtxt.setError("Password is required");
        focusView= regpasswordtxt;
        cancel=true;// return basically
    }
    else  if(!TextUtils.equals(cpassword,password)){
        confirmpasswordtxt.setError("Passwords do not match");
        focusView= confirmpasswordtxt;
        cancel=true;// return basically
    }
    if (cancel) {
        // There was an error; don't attempt login and focus the first
        focusView.requestFocus();
    } else {
        // Show a progress spinner, and kick off a background task to
        // Register user
        //showProgress(true);
      //  mAuthTask = new UserRegisterTask(email, password);
       // mAuthTask.execute((Void) null);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Processing Regsitration", Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);//for now
        bar.setTitle("Processing Registration");
       // bar.setDisplayHomeAsUpEnabled(true);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();; //animate transition and all that jazz
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        RegisterConfirmation agreement = new RegisterConfirmation();
        fragmentTransaction.replace(android.R.id.content, agreement);
        fragmentTransaction.commit();
    }



}
public void getNewPassword(View v){
    bar.setTitle("Forgot Password");
    bar.setDisplayHomeAsUpEnabled(true);
    Context context = getApplicationContext();
    Toast toast = Toast.makeText(context, "New password request sent", Toast.LENGTH_SHORT);
    toast.show();
    toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);//for now
   TextView name = (TextView)  findViewById(R.id.name_request);
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
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // startActivity(intent);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

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

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
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
            makeJsonObjectRequest();
            showProgress(false);

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        if (email.contains("@")){
            return email.contains(".");
        }
        //check it its gigits only
        return (Pattern.matches("[0-9]+", email) == true && email.length()>=9 );

    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }
     void makeJsonObjectRequest() {
        //showpDialog(true);
         System.out.println("inside the make Json");
         JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                 urlJsonObj, "", new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "what is going on"+response.toString());

                try {
                    // Parsing json object response
                    String fullname= response.getString("name");
                    String idno=response.getString("id_no");
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
                    String phone="";
                    String other_contact="";
                    //String home = phone.getString("home");
                    //Logic for dealing with too many contact
                    for(int i=0;i<contact.length();i++){
                        if (contact.get(i).toString().contains("@")){
                           // if(other_contact.length()>1) { //there is already another email
                                other_contact = contact.get(i).toString();
                           // }
                        }

                        else{
                            if(phone.length()>2) {
                                phone = contact.get(i).toString();
                            }
                        }
                        if(phone.length()>2 && other_contact.length()>2){
                            i=contact.length();//basically break if the contact his too many contact
                        }

                    }

                    List<Profile> cn= db.getAllProfile();
                    user = cn.get(0);
                    Profile profile = new Profile();
                    profile.setUserID("0");

                    profile.setName(fullname.substring(0,fullname.indexOf(" ")));
                    profile.setSurname(fullname.substring(fullname.indexOf(" ")+1));


                    jsonResponse = "Home: " + address + "\n\n";
                    jsonResponse += "Name: " + fullname + "\n\n";
                    jsonResponse += "id_no: " + idno + "\n\n";
                    jsonResponse += "contact: " + phone + "\n\n";
                    jsonResponse += "contact: " + other_contact + "\n\n";
                    jsonResponse += "Email: " + contact.toString() + "\n\n";
                    jsonResponse += "work: " + work_status + "\n\n";
                    jsonResponse += "class: " + class_no + "\n\n";

                    System.out.println(jsonResponse);
                  textResponse.setText(jsonResponse);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

