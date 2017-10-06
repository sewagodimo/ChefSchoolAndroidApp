package com.example.mosadi.chefschool.loginfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mosadi.chefschool.LoginActivity;
import com.example.mosadi.chefschool.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mosadi on 2017/08/30.
 */

public class ForgotPassword extends Fragment {
    EditText name,phone;
    Button send;
    RequestQueue queue ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //((LoginActivity) getActivity()).setActionBarTitle(" Forgot your password");
     View v= inflater.inflate(R.layout.login_forgotpassword, container, false);
        name= (EditText) v.findViewById(R.id.forgot_name);
        phone=(EditText) v.findViewById(R.id.forgot_phone);
        send=(Button)v.findViewById(R.id.request_button);
        queue = Volley.newRequestQueue(getActivity());
        send.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // WHEN the user clicks that they want to get a new address


                        //  reason.setError(null);
                        name.setError(null);
                        phone.setError(null);
                        View focusView = null;
                        if(TextUtils.isEmpty(name.getText().toString()) || name.getText().toString().contains(" ")){
                            name.setError("Name and surname separated by space");
                            focusView = name;
                            focusView.requestFocus();
                        }
                       if(TextUtils.isEmpty(phone.getText().toString())){
                            phone.setError("Phone number missing");
                            focusView = phone;
                            focusView.requestFocus();
                        }

                        else{
                           //send the request with the student information

                           Map<String, String> params = new HashMap<>();
                           params.put("title", "forgot password");
                           params.put("name", name.getText().toString());
                           params.put("contact",phone.getText().toString());
                           JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, LoginActivity.postURL, new JSONObject(params),
                                   new Response.Listener<JSONArray>() {
                                       @Override
                                       public void onResponse(JSONArray response) {

                                           //user.setWork_status("not working");
                                           Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Successfully edited your detail", Toast.LENGTH_SHORT);
                                           toast.show();
                                           toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now

                                       }
                                   },
                                   new Response.ErrorListener() {
                                       @Override
                                       public void onErrorResponse(VolleyError error) {
                                           System.out.println("change Pass response -->> " + error.toString());
                                           if (error.getMessage() == null) {
                                               (((LoginActivity) getActivity())).notifitcation("Server Error");
                                           } else if (error.getMessage().contains("Connect")) {
                                               (((LoginActivity) getActivity())).notifitcation("Connection Error");
                                           } else if (error.getMessage().contains("JSONException")) {
                                               if (error.getMessage().contains("UNKNOWN REQUEST")) {
                                                   (((LoginActivity) getActivity())).notifitcation("Unknown Request sent");
                                               }
                                               if (error.getMessage().contains("MISMTACH")) {
                                                   (((LoginActivity) getActivity())).notifitcation("Unknown User sending a request");
                                               }
                                               else{
                                                   String message= error.getMessage();
                                                   String password =message.substring(message.indexOf("[", message.indexOf("]")));

                                                   Toast toast = Toast.makeText(getActivity().getApplicationContext(), "you password is: "+password, Toast.LENGTH_SHORT);
                                                   toast.show();
                                                   toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);//for now
                                               }
                                           }
                                           else{
                                               (((LoginActivity) getActivity())).notifitcation(error.getMessage() );
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
                           //
                        }

                    }
                }
        );
        return v;
    }
    public String sendSMS(String password){
        String sent= "Mesage sent succefully";

        //the progress bar

        String toPhoneNumber = phone.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, uniformSMS(password), null, null);


        } catch (Exception e) {
            sent="SMS sending failed";


        }

        return sent;

    }
    public String uniformSMS(String password){
        String sent= "SMS sent";
        String mymessage ="I need a new uniform\n"
                +"Student: "+name.getText().toString()+
                "password: "+password
                +"\nContact on: "+phone.getText().toString();

        String toPhoneNumber =  "0764270487";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, mymessage, null, null);

        } catch (Exception e) {
            sent="SMS sending failed";

        }

        return sent;

    }

}
